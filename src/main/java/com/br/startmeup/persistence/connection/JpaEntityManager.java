package com.br.startmeup.persistence.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManager {

    private static EntityManagerFactory factory;
    private EntityManager em;

    public static EntityManagerFactory getInstance(){
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("startmeup");
        }
        return factory;
    }

    public EntityManager getEntityManager() {
        if(em == null){
            em = factory.createEntityManager();
        }
        return em;
    }
}
