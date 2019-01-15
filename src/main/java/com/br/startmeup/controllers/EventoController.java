package com.br.startmeup.controllers;

import com.br.startmeup.business.EventoBusiness;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.DAO.EventoDAO;
import com.br.startmeup.persistence.connection.SingletonConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("eventos")
public class EventoController {

    private GenericDAO<Evento> genericDAO;
    private EventoBusiness eventoBusiness;

    public EventoController(){
        this.genericDAO = new EventoDAO(SingletonConnection.getInstance().getConnection());
        this.eventoBusiness = new EventoBusiness(genericDAO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll(){
        return null;
    }

    @GET
    @Path("/{id}")
    public String findById(){
        return  null;
    }

    @POST
    public String create(){
        return null;
    }

    @PUT
    public String update(){
        return  null;

    }

    @DELETE
    public String delete(){
        return null;
    }
}
