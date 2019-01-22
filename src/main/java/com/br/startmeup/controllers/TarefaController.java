package com.br.startmeup.controllers;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.Enum.StatusEvento;
import com.br.startmeup.business.TarefaBusiness;
import com.br.startmeup.helper.DateHandler;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.interfaces.ITarefaDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Tarefa;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.JPA.TarafaJpaDAO;
import com.br.startmeup.persistence.connection.JpaEntityManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tarefas")
public class TarefaController {
    private ITarefaDAO<Tarefa> genericDAO;
    private TarefaBusiness tarefaBusiness;
    private EntityManager em;
    private Gson gson;

    public TarefaController() {
        em = JpaEntityManager.getInstance().createEntityManager();
        this.genericDAO = new TarafaJpaDAO(em);
        this.tarefaBusiness = new TarefaBusiness(genericDAO);
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() {

        ObjectResponse<List<Tarefa>> response = tarefaBusiness.findAllTarefas();

        if (response.isStatus() == StatusEnum.OK) {
            String json = gson.toJson(response.getObject());

            return json;
        }
        return response.getMessage();
    }

    @GET
    @Path("/{id}")
    public String findById(@PathParam("id") long id) {
        ObjectResponse<Tarefa> response = tarefaBusiness.findByIdEvento(id);
        if (response.isStatus().equals(StatusEnum.OK)) {
            String json = gson.toJson(response.getObject());
            return json;
        }
        return response.getMessage();
    }

    @GET
    public String findByIdUsuario(@QueryParam("idUsuario") long id) {
        ObjectResponse<List<Tarefa>> response = tarefaBusiness.findByIdUsuario(id);
        if (response.isStatus().equals(StatusEnum.OK)) {
            String json = gson.toJson(response.getObject());
            return json;
        }
        return response.getMessage();
    }

    @POST
    public Response create(@FormParam("nome") String nome,
                           @FormParam("dataInicio") String dataInicio,
                           @FormParam("dataFim") String dataFim,
                           @FormParam("statusTarefa") String statusTarefa,
                           @FormParam("idUsuario") long idUsuario,
                           @FormParam("priodirade")int prioridade) {

        Response response = Response.status(Response.Status.BAD_REQUEST).build();

        try {
            Tarefa tarefa = new Tarefa();
            tarefa.setNome(nome);
            tarefa.setDataInicio((DateHandler.parseStringtoDate(dataInicio)));
            tarefa.setDataFim(DateHandler.parseStringtoDate(dataFim));
            tarefa.setStatusEvento(StatusEvento.valueOf(statusTarefa));
            tarefa.setPrioridade(prioridade);

            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);
            tarefa.setUsuario(usuario);

            ObjectResponse<Tarefa> objectResponse =
                    tarefaBusiness.createTarefa(tarefa);
            if (objectResponse.isStatus() == StatusEnum.OK) {
                String json = gson.toJson(objectResponse.getObject());
                response = Response.ok(json, MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception ex) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @PUT
    public String update(@FormParam("id") long id,
                         @FormParam("nome") String nome,
                         @FormParam("dataInicio") String dataInicio,
                         @FormParam("dataFim") String dataFim,
                         @FormParam("statusTarefa") String statusTarefa,
                         @FormParam("priodirade")int prioridade) {

        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setNome(nome);
        tarefa.setDataInicio((DateHandler.parseStringtoDate(dataInicio)));
        tarefa.setDataFim(DateHandler.parseStringtoDate(dataFim));
        tarefa.setStatusEvento(StatusEvento.valueOf(statusTarefa));
        tarefa.setPrioridade(prioridade);

        ObjectResponse<Boolean> response =
                tarefaBusiness.updateTarefa(tarefa);

        return response.getMessage();

    }

    @DELETE
    public String delete(@FormParam("id") long id) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);

        ObjectResponse<Boolean> response =
                tarefaBusiness.deleteTarefa(tarefa);

        return response.getMessage();
    }
}
