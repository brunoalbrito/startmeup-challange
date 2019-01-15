package com.br.startmeup.controllers;


import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.business.UsuarioBusiness;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.DAO.UsuarioDAO;
import com.br.startmeup.persistence.connection.SingletonConnection;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("usuarios")
public class UsuarioController extends Application {

    private GenericDAO<Usuario> genericDAO;

    private UsuarioBusiness usuarioBusiness;

    public UsuarioController() {
        this.genericDAO =
                new UsuarioDAO(SingletonConnection.getInstance().getConnection());
        this.usuarioBusiness = new UsuarioBusiness(genericDAO);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {

        ObjectResponse<List<Usuario>> response =
                usuarioBusiness.findAllUsuarios();
        if (response.isStatus() == StatusEnum.OK) {
            String json = new Gson().toJson(response.getObject());
            return json;
        }
        return response.getMessage();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getById(@PathParam("id") long id) {

        ObjectResponse<Usuario> response =
                usuarioBusiness.findByIdUsuario(id);
        if (response.isStatus() == StatusEnum.OK) {
            String json = new Gson().toJson(response.getObject());
            return json;
        }

        return response.getMessage();
    }

    @POST
    public String create(@FormParam("nome") String nome,
                         @FormParam("email") String email,
                         @FormParam("senha") String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        ObjectResponse<Boolean> response = usuarioBusiness.create(usuario);
        if(response.isStatus() == StatusEnum.OK){
            return response.getMessage();
        }

        return response.getMessage();
    }
}

