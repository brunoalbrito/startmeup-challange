package com.br.startmeup.business;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.controllers.EventoController;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class EventoBusiness {

    private IEventoDAO<Evento> genericDAO;

    public EventoBusiness(IEventoDAO<Evento> genericDAO) {
        this.genericDAO = genericDAO;
    }

    public ObjectResponse<List<Evento>> findAllEventos(){

        ObjectResponse<List<Evento>> response = new ObjectResponse<>();
        response.setObject(genericDAO.findAll());
        if(response.getObject() !=  null){
            response.setStatus(StatusEnum.OK);
            return response;
        }

        response.setMessage("Nenhum eventos cadastrado");


        return response;
    }

    public ObjectResponse<Evento> findByIdEvento(long id){

        ObjectResponse<Evento> response = new ObjectResponse<>();
        response.setObject(genericDAO.findById(id));

        if(response.getObject() != null){
            response.setStatus(StatusEnum.OK);
            return  response;
        }
        response.setStatus(StatusEnum.VAZIO);
        response.setMessage("Evento não encontrado");

        return response;
    }

    public ObjectResponse<Evento> createEvento(Evento evento){

        ObjectResponse<Evento> response = new ObjectResponse<>();
        Evento result = genericDAO.create(evento);
        if(Objects.isNull(result.getId())){
            response.setObject(result);
            response.setMessage("Evento inserido com sucesso");
            return response;
        }

        response.setMessage("Falha ao inserir evento");

        return response;
    }

    public ObjectResponse<Boolean> updateEvento(Evento evento){
        ObjectResponse<Boolean> response = new ObjectResponse<>();
        boolean result = genericDAO.update(evento);
        if(result){
            response.setMessage("Evento atualizado com sucesso");
            return response;
        }

        response.setMessage("Falha ao atualizar evento");

        return response;
    }

    public ObjectResponse<Boolean> deleteEvento(Evento evento){
        ObjectResponse<Boolean> response = new ObjectResponse<>();
        boolean result = genericDAO.delete(evento);
        if(result){
            response.setMessage("Evento removido com sucesso");
            return response;
        }

        response.setMessage("Falha ao remover evento");

        return response;
    }

    public ObjectResponse<List<Evento>> findByIdUsuario(long id) {
        ObjectResponse<List<Evento>> response = new ObjectResponse<>();
        response.setObject(genericDAO.findByUserId(id));

        if(response.getObject() != null){
            response.setStatus(StatusEnum.OK);
            return  response;
        }
        response.setStatus(StatusEnum.VAZIO);
        response.setMessage("Evento não encontrado");

        return response;
    }
}
