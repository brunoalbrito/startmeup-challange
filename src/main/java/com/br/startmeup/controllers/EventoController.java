package com.br.startmeup.controllers;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.business.EventoBusiness;
import com.br.startmeup.helper.DateHandler;
import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.JPA.EventoJpaDAO;
import com.br.startmeup.persistence.connection.JpaEntityManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("eventos")
public class EventoController {

    private IEventoDAO<Evento> genericDAO;
    private EventoBusiness eventoBusiness;
    private EntityManager em;
    private Gson gson;

    public EventoController() {
        em = JpaEntityManager.getInstance().createEntityManager();
        this.genericDAO = new EventoJpaDAO(em);
        this.eventoBusiness = new EventoBusiness(genericDAO);
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() {

        ObjectResponse<List<Evento>> response = eventoBusiness.findAllEventos();

        if (response.isStatus() == StatusEnum.OK) {
            String json = gson.toJson(response.getObject());

            return json;
        }
        return response.getMessage();
    }

    @GET
    @Path("/{id}")
    public String findById(@PathParam("id") long id) {
        ObjectResponse<Evento> response = eventoBusiness.findByIdEvento(id);
        if(response.isStatus().equals(StatusEnum.OK)){
            String json = gson.toJson(response.getObject());
            return json;
        }
        return response.getMessage();
    }

    @GET
    public String findByIdUsuario(@QueryParam("idUsuario") long id) {
        ObjectResponse<List<Evento>> response = eventoBusiness.findByIdUsuario(id);
        if(response.isStatus().equals(StatusEnum.OK)){
            String json = gson.toJson(response.getObject());
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
        evento.setDataInicio((DateHandler.parseStringtoDate(dataInicio)));
        evento.setDataFim(DateHandler.parseStringtoDate(dataFim));
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        evento.setUsuario(usuario);

        ObjectResponse<Evento> response =
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
        evento.setDataInicio((DateHandler.parseStringtoDate(dataInicio)));
        evento.setDataFim(DateHandler.parseStringtoDate(dataFim));


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
