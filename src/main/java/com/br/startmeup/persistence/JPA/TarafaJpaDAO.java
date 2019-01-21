package com.br.startmeup.persistence.JPA;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Tarefa;

import javax.persistence.EntityManager;
import java.util.List;

public class TarafaJpaDAO implements GenericDAO<Tarefa> {

    private EntityManager em;

    public TarafaJpaDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public Tarefa create(Tarefa tarefa) {
        try{
            em.getTransaction().begin();
            em.persist(tarefa);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
        return tarefa;
    }

    @Override
    public Tarefa findById(long id) {
        return em.find(Tarefa.class, id);
    }

    @Override
    public List<Tarefa> findAll() {
        return em.createQuery("FROM " + Tarefa.class.getName()).getResultList();
    }

    @Override
    public boolean update(Tarefa tarefa) {
        boolean verifica = false;
        try{
            em.getTransaction().begin();
            em.merge(tarefa);
            em.getTransaction().commit();
            verifica = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return verifica;
    }

    @Override
    public boolean delete(Tarefa tarefa) {
        boolean verifica = false;
        try{
            em.getTransaction().begin();
            tarefa = em.find(Tarefa.class, tarefa.getId());
            em.remove(tarefa);
            em.getTransaction().commit();
            verifica = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return verifica;
    }
}
