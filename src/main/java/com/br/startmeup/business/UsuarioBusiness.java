package com.br.startmeup.business;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Usuario;

import java.util.List;

public class UsuarioBusiness {

    private GenericDAO<Usuario> genericDAO;

    public UsuarioBusiness(GenericDAO<Usuario> genericDAO){
        this.genericDAO = genericDAO;
    }

    public List<Usuario> findAll(){
        return  genericDAO.findAll();
    }
}
