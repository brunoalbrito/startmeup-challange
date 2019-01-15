package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Agenda;

import java.util.List;

public class AgendaDAO implements GenericDAO<Agenda>{
    @Override
    public boolean create(Agenda agenda) {
        return false;
    }

    @Override
    public Agenda findById(long id) {
        return null;
    }

    @Override
    public List<Agenda> findAll() {
        return null;
    }

    @Override
    public boolean update(Agenda agenda) {
        return false;
    }

    @Override
    public boolean delete(Agenda agenda) {
        return false;
    }
}