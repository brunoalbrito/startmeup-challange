package com.br.startmeup.business;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Agenda;
import com.br.startmeup.persistence.DAO.AgendaDAO;

import java.sql.Connection;
import java.util.List;

public class AgendaBusiness {

    private GenericDAO<Agenda> genericDAO;

    public AgendaBusiness(GenericDAO<Agenda> genericDAO){
        this.genericDAO = genericDAO;
    }

    public ObjectResponse<List<Agenda>> getAll(){
        ObjectResponse response = new ObjectResponse();

        response.setObject(genericDAO.findAll());

        if(response.getObject() != null){
            response.setStatus(StatusEnum.OK);
            return response;
        }

        response.setStatus(StatusEnum.VAZIO);
        response.setMessage("Falha ao buscar agenda");

        return response;
    }

    public ObjectResponse<Agenda> findByIdAgenda(long id) {
        ObjectResponse<Agenda> response = new ObjectResponse<>();

        response.setObject(genericDAO.findById(id));

        if(response.getObject() != null){
            response.setStatus(StatusEnum.OK);
            return response;
        }

        response.setStatus(StatusEnum.VAZIO);
        response.setMessage("Nenhuma agenda com o código informado");
        return response;
    }

    public ObjectResponse<Boolean> createAgenda(Agenda agenda) {

        ObjectResponse response = new ObjectResponse();
        boolean status = genericDAO.create(agenda);

        if(status){
            response.setMessage("Agenda criada com sucesso");
            response.setStatus(StatusEnum.OK);
            return response;
        }
        response.setMessage("Falha ao inserir agenda");
        response.setStatus(StatusEnum.FALHA);
        return response;
    }

    public ObjectResponse<Boolean> updateAgenda(Agenda agenda) {

        ObjectResponse<Boolean> response = new ObjectResponse<>();
        boolean result = genericDAO.update(agenda);
        if(result){
            response.setMessage("Agenda atualizada com sucesso");
        }else{
            response.setMessage("Falha ao atualizar agenda");
        }
        return response;
    }

    public ObjectResponse<Boolean> deleteAgenda(Agenda agenda){
        ObjectResponse<Boolean> response = new ObjectResponse<>();
        boolean result = genericDAO.delete(agenda);
        if(result){
            response.setMessage("Agenda excluida com sucesso");
        }else{
            response.setMessage("Falha ao excluir agenda");
        }
        return response;
    }
}
