package com.br.startmeup.controllers;


import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.business.UsuarioBusiness;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.interfaces.IUsuarioDAO;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.DAO.UsuarioDAO;
import com.br.startmeup.persistence.JPA.UsuarioJpaDAO;
import com.br.startmeup.persistence.connection.JpaEntityManager;
import com.br.startmeup.persistence.connection.SingletonConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("usuarios")
public class UsuarioController extends Application {

    private IUsuarioDAO<Usuario> genericDAO;

    private EntityManager em;

    private UsuarioBusiness usuarioBusiness;

    public UsuarioController() {
        this.em = JpaEntityManager.getInstance().createEntityManager();
        this.genericDAO =
                new UsuarioJpaDAO(em);
        this.usuarioBusiness = new UsuarioBusiness(genericDAO);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        try{
            ObjectResponse<List<Usuario>> response =
                    usuarioBusiness.findAllUsuarios();
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            if (response.isStatus() == StatusEnum.OK) {
                String json = gson.toJson(response.getObject());
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getById(@PathParam("id") long id) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        ObjectResponse<Usuario> response =
                usuarioBusiness.findByIdUsuario(id);
        if (response.isStatus() == StatusEnum.OK) {
            String json = gson.toJson(response.getObject());
            return json;
        }

        return response.getMessage();
    }

    @GET
    @Produces
    public Response getByEmail(@QueryParam("email")String email){

        try {
            ObjectResponse<Usuario> response = usuarioBusiness.findByEmailUsuario(email);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            if(response.isStatus().equals(StatusEnum.OK)){
                String json = gson.toJson(response.getObject());
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response create(@FormParam("nome") String nome,
                           @FormParam("email") String email,
                           @FormParam("senha") String senha) {
        try{
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            ObjectResponse<Boolean> response = usuarioBusiness.create(usuario);

            if(response.isStatus() == StatusEnum.OK){
                return Response.ok(response, MediaType.APPLICATION_JSON).build();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    public Response update(@FormParam("id") long id,
                         @FormParam("nome") String nome,
                         @FormParam("email") String email,
                         @FormParam("senha") String senha) {

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        try{
            ObjectResponse<Boolean> response = usuarioBusiness.updateUsuario(usuario);
            if(response.isStatus() == StatusEnum.OK){
                return Response.ok(response.getMessage(), MediaType.APPLICATION_JSON).build();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    public Response delete(@FormParam("id")long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);

        try{
            ObjectResponse<Boolean> response = usuarioBusiness.deleteUsuario(usuario);

            if(response.isStatus() == StatusEnum.OK)
                return Response.status(Response.Status.OK).build();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return Response.ok(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}

