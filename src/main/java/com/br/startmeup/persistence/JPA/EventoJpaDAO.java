package com.br.startmeup.persistence.JPA;

import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.models.Evento;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class EventoJpaDAO implements IEventoDAO<Evento> {


    private EntityManager em;

    public EventoJpaDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Evento> findByUserId(long id) {
        List<Evento> eventos = em.createQuery("FROM "+ Evento.class).getResultList();

        return eventos
                .stream()
                .filter((evento) -> evento.getUsuario().getId() == id)
                .collect(Collectors.toList());
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
