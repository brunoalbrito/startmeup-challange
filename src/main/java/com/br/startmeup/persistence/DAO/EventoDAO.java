package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Evento;

import java.sql.Connection;
import java.util.List;

public class EventoDAO implements GenericDAO<Evento> {

    private  Connection connection;

    public EventoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Evento evento) {
        return false;
    }

    @Override
    public Evento findById(long id) {
        return null;
    }

    @Override
    public List<Evento> findAll() {
        return null;
    }

    @Override
    public boolean update(Evento evento) {
        return false;
    }

    @Override
    public boolean delete(Evento evento) {
        return false;
    }
}
