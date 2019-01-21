package com.br.startmeup.persistence.JPA;

import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.models.Evento;
import com.br.startmeup.models.Usuario;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventoJpaDAO implements IEventoDAO<Evento> {


    private EntityManager em;

    public EventoJpaDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Evento> findByUserId(long id) {
        List<Evento> eventos = em.createQuery("FROM " + Evento.class.getName()).getResultList();

        List<Evento> _eventos = new ArrayList<>();

        for (Evento evento : eventos
        ) {
            if (evento.getUsuario().getId() == id)
                _eventos.add(evento);
        }

        return _eventos;
    }

    @Override
    public boolean create(Evento evento) {
        try{
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            return true;
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Evento findById(long id) {
        return em.find(Evento.class, id);
    }

    @Override
    public List<Evento> findAll() {
        return em.createQuery("FROM " + Evento.class.getName()).getResultList();
    }

    @Override
    public boolean update(Evento evento) {
        boolean verifica = false;
        try{
            em.getTransaction().begin();
            em.merge(evento);
            em.getTransaction().commit();
            verifica = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return verifica;
    }

    @Override
    public boolean delete(Evento evento) {
        boolean verifica = false;
        try{
            em.getTransaction().begin();
            evento = em.find(Evento.class, evento.getId());
            em.remove(evento);
            em.getTransaction().commit();
            verifica = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return verifica;
    }
}
