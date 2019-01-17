package com.br.startmeup.controllers;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.business.EventoBusiness;
import com.br.startmeup.helper.DataHandler;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.DAO.EventoDAO;
import com.br.startmeup.persistence.connection.SingletonConnection;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("eventos")
public class EventoController {

    private GenericDAO<Evento> genericDAO;
    private EventoBusiness eventoBusiness;

    public EventoController() {
        this.genericDAO = new EventoDAO(SingletonConnection.getInstance().getConnection());
        this.eventoBusiness = new EventoBusiness(genericDAO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() {

        ObjectResponse<List<Evento>> response = eventoBusiness.findAllEventos();

        if (response.isStatus() == StatusEnum.OK) {
            String json = new Gson().toJson(response.getObject());

            return json;
        }
        return response.getMessage();
    }

    @GET
    @Path("/{id}")
    public String findById(@PathParam("id") long id) {
        ObjectResponse<Evento> response = eventoBusiness.findByIdEvento(id);
        if(response.isStatus().equals(StatusEnum.OK)){
            String json = new Gson().toJson(response.getObject());
            return json;
        }
        return response.getMessage();
    }

    @POST
    public String create(@FormParam("titulo") String titulo,
                         @FormParam("endereco") String endereco,
                         @FormParam("dataInicio") String dataInicio,
                         @FormParam("dataFim") String dataFim,
                         @FormParam("idUsuario") long idUsuario) {

        Evento evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDataInicio((DataHandler.parseStringtoDate(dataInicio)));
        evento.setDataFim(DataHandler.parseStringtoDate(dataFim));
        evento.setFkUsuario(idUsuario);

        ObjectResponse<Boolean> response =
                eventoBusiness.createEvento(evento);

        return response.getMessage();
    }

    @PUT
    public String update(@FormParam("id") long id,
                         @FormParam("titulo") String titulo,
                         @FormParam("dataInicio") String dataInicio,
                         @FormParam("dataFim") String dataFim) {

        Evento evento = new Evento();
        evento.setId(id);
        evento.setTitulo(titulo);
        evento.setDataInicio((DataHandler.parseStringtoDate(dataInicio)));
        evento.setDataFim(DataHandler.parseStringtoDate(dataFim));


        ObjectResponse<Boolean> response =
                eventoBusiness.updateEvento(evento);

        return response.getMessage();

    }

    @DELETE
    public String delete(@FormParam("id")long id) {
        Evento evento = new Evento();
        evento.setId(id);

        ObjectResponse<Boolean> response =
                eventoBusiness.deleteEvento(evento);

        return response.getMessage();
    }
}
