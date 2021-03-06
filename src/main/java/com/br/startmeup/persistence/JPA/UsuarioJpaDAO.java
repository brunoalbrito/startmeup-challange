package com.br.startmeup.persistence.JPA;

import com.br.startmeup.interfaces.IUsuarioDAO;
import com.br.startmeup.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioJpaDAO implements IUsuarioDAO<Usuario> {

    private EntityManager em;

    public UsuarioJpaDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public Usuario findByEmail(String email) {
        List<Usuario> usuarios = em.createQuery("FROM " + Usuario.class.getName()).getResultList();

        Usuario usuario = usuarios
                .stream()
                .filter((_usuario) -> _usuario.getEmail().equals(email)).findFirst().orElse(null);
        return usuario;
    }

    @Override
    public Usuario create(Usuario usuario) {
        try{
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario findById(long id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("FROM " + Usuario.class.getName()).getResultList();
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean verifica = false;
        try{
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
            verifica = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return verifica;
    }

    @Override
    public boolean delete(Usuario usuario) {
        boolean verifica = false;
        try{
            em.getTransaction().begin();
            usuario = em.find(Usuario.class, usuario.getId());
            em.remove(usuario);
            em.getTransaction().commit();
            verifica = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return verifica;
    }
}
