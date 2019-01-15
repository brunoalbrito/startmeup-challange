package com.br.startmeup.business;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEnum;
import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Usuario;

import java.util.List;

public class UsuarioBusiness {

    private GenericDAO<Usuario> genericDAO;

    public UsuarioBusiness(GenericDAO<Usuario> genericDAO) {
        this.genericDAO = genericDAO;
    }

    public ObjectResponse<List<Usuario>> findAllUsuarios() {

        List<Usuario> usuarios = genericDAO.findAll();
        ObjectResponse<List<Usuario>> response = new ObjectResponse<>();

        if (!usuarios.isEmpty()) {
            response.setStatus(StatusEnum.OK);
            response.setObject(usuarios);
            return response;
        }
        response.setStatus(StatusEnum.VAZIO);
        response.setMessage("A lista está vazia");

        return response;
    }

    public ObjectResponse<Usuario> findByIdUsuario(long id) {


        Usuario usuario = genericDAO.findById(id);
        ObjectResponse<Usuario> response = new ObjectResponse<>();

        if (usuario != null) {
            response.setStatus(StatusEnum.OK);
            response.setObject(usuario);
            return response;
        }
        response.setStatus(StatusEnum.VAZIO);
        response.setMessage("Usuario não encontrado");

        return response;
    }

    public ObjectResponse<Boolean> create(Usuario usuario){

        ObjectResponse<Boolean> response = new ObjectResponse<>();

        boolean status = genericDAO.create(usuario);

        if(status){
            response.setMessage("Usuario criado com sucesso");
            response.setStatus(StatusEnum.OK);
            return response;
        }

        response.setMessage("Falha ao criar Usuario");
        response.setStatus(StatusEnum.FALHA);

        return response;
    }

    public ObjectResponse<Boolean> update(Usuario usuario){

        ObjectResponse<Boolean> response = new ObjectResponse<>();

        boolean result = genericDAO.update(usuario);

        if(result){
            response.setStatus(StatusEnum.OK);
            response.setMessage("Usuario alterado com sucesso");
            return response;
        }

        response.setStatus(StatusEnum.FALHA);
        response.setMessage("Falha ao atualizar usuario");

        return response;
    }
}
