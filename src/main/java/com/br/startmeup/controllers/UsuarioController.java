package com.br.startmeup.controllers;


import com.br.startmeup.business.UsuarioBusiness;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.DAO.UsuarioDAO;
import com.br.startmeup.persistence.connection.SingletonConnection;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("usuarios")
public class UsuarioController extends Application {

    private GenericDAO<Usuario> genericDAO;

    public UsuarioController(){
        genericDAO = new UsuarioDAO(SingletonConnection.getInstance().getConnection());
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){

        List<Usuario> usuarios = new UsuarioBusiness(genericDAO).findAll();


        String json = new Gson().toJson(usuarios);

        return json;
    }
}
