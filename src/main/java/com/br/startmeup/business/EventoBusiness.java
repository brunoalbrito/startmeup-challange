package com.br.startmeup.business;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.controllers.EventoController;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.DAO.EventoDAO;

import java.sql.Connection;
import java.util.List;

public class EventoBusiness {

    private GenericDAO<Evento> genericDAO;

    public EventoBusiness(GenericDAO<Evento> genericDAO) {
        this.genericDAO = genericDAO;
    }

    public ObjectResponse<List<Evento>> findAllEventos(){
        return null;
    }

    public ObjectResponse<Evento> findByIdEvento(){
        return null;
    }

    public ObjectResponse<Boolean> createEvento(Evento evento){

        ObjectResponse<Boolean> response = new ObjectResponse<>();
        boolean result = genericDAO.create(evento);
        if(result){
            response.setMessage("Evento inserido com sucesso");
            return response;
        }

        response.setMessage("Falha ao inserir evento");

        return response;
    }

    public ObjectResponse<Boolean> updateEvento(){
        return null;
    }

    public ObjectResponse<Boolean> deleteEvento(){
        return null;
    }
}
