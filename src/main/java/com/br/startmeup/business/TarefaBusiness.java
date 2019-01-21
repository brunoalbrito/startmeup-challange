package com.br.startmeup.business;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Tarefa;

import java.util.List;
import java.util.Objects;

public class TarefaBusiness {

    private GenericDAO<Tarefa> genericDAO;

    public TarefaBusiness(GenericDAO<Tarefa> genericDAO) {
        this.genericDAO = genericDAO;
    }

    public ObjectResponse<Tarefa> findByIdEvento(long id) {

        ObjectResponse<Tarefa> response = new ObjectResponse<>();
        response.setObject(genericDAO.findById(id));
        if(!Objects.isNull(response.getObject())){
            response.setStatus(StatusEnum.OK);
            return  response;
        }
        response.setStatus(StatusEnum.VAZIO);

        return response;
    }

    public ObjectResponse<List<Evento>> findByIdUsuario(long id) {
        return null;
    }

    public ObjectResponse<Tarefa> createTarefa(Tarefa tarefa) {

        ObjectResponse<Tarefa> response = new ObjectResponse<>();
        response.setObject(genericDAO.create(tarefa));
        if(!Objects.isNull(response.getObject().getId())){
            response.setStatus(StatusEnum.OK);
            response.setMessage("Tarefa inserido com sucesso");
            return response;
        }
        response.setStatus(StatusEnum.FALHA);

        return response;
    }

    public ObjectResponse<List<Tarefa>> findAllTarefas() {
        ObjectResponse<List<Tarefa>> response = new ObjectResponse<>();
        response.setObject(genericDAO.findAll());
        if(!Objects.isNull(response.getObject())){
            response.setStatus(StatusEnum.OK);
            return response;
        }
        response.setStatus(StatusEnum.VAZIO);

        return response;
    }

    public ObjectResponse<Boolean> updateTarefa(Tarefa tarefa) {

        ObjectResponse<Boolean> response = new ObjectResponse<>();
        response.setObject(false);
        response.setObject(genericDAO.update(tarefa));
        if(response.getObject()){
            return response;
        }

        return response;
    }

    public ObjectResponse<Boolean> deleteTarefa(Tarefa tarefa) {
        ObjectResponse<Boolean> response = new ObjectResponse<>();
        response.setObject(false);
        response.setObject(genericDAO.delete(tarefa));
        if(response.getObject()){
            return response;
        }
        return response;
    }
}
