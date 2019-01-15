package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Usuario;

import java.util.List;

public class UsuarioDAO implements GenericDAO<Usuario> {
    @Override
    public boolean create(Usuario usuario) {
        return false;
    }

    @Override
    public Usuario findById(long id) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public boolean update(Usuario usuario) {
        return false;
    }

    @Override
    public boolean delete(Usuario usuario) {
        return false;
    }
}
