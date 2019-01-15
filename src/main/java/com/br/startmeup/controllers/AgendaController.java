package com.br.startmeup.controllers;


import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.business.AgendaBusiness;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Agenda;
import com.br.startmeup.persistence.DAO.AgendaDAO;
import com.br.startmeup.persistence.connection.SingletonConnection;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("agendas")
public class AgendaController {

    private GenericDAO<Agenda> genericDAO;

    private AgendaBusiness agendaBusiness;

    public  AgendaController(){
        this.genericDAO = new AgendaDAO(SingletonConnection.
                getInstance().getConnection());
        agendaBusiness = new AgendaBusiness(genericDAO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        ObjectResponse<List<Agenda>> response = agendaBusiness.getAll();

        if(response.isStatus() == StatusEnum.OK){
            String json = new Gson().toJson(response.getObject());
            return  json;
        }
        return response.getMessage();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getById(@PathParam("id") long id){
        ObjectResponse<Agenda> response =
                agendaBusiness.findByIdAgenda(id);
        if (response.isStatus() == StatusEnum.OK) {
            String json = new Gson().toJson(response.getObject());
            return json;
        }

        return response.getMessage();
    }

    @POST
    public String create(@FormParam("nome")String nome,
                         @FormParam("descricao")String descricao,
                         @FormParam("idUsuario") long idUsuario){
        String resultado = null;

        Agenda agenda = new Agenda();
        agenda.setNome(nome);
        agenda.setDescricao(descricao);
        agenda.setIdUsuario(idUsuario);

        ObjectResponse<Boolean> response = agendaBusiness.createAgenda(agenda);

        return response.getMessage();
    }

}
