package com.br.startmeup.persistence.JPA;

import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.models.Evento;

import javax.persistence.EntityManager;
import java.util.List;

public class EventoJpaDAO implements IEventoDAO<Evento> {


    private EntityManager em;

    public EventoJpaDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Evento> findByUserId(long id) {
        return null;
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
