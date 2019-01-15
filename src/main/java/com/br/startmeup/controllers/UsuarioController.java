package com.br.startmeup.controllers;


import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.JpaEntityManager;
import com.br.startmeup.persistence.connection.SingletonConnection;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("hello")
public class UsuarioController extends Application {

    @GET
    public String get(){
        System.out.println("Saiu aqui");

        EntityManager entityManager = new JpaEntityManager().getEntityManager();

        Connection connection = SingletonConnection.getInstance().getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                return rs.getString("nome");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }


        return "funcionou";
    }
}
