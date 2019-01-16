package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Evento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EventoDAO implements GenericDAO<Evento> {

    private  Connection connection;

    public EventoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Evento evento) {

        boolean result = false;

        String sql = "INSERT INTO evento(nome,endereco,dataEvent, fkAgenda)VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, evento.getNome());
            ps.setString(2,evento.getEndereco());
            ps.setDate(3, new Date(evento.getData().getTime()));
            ps.setLong(4, evento.getFkAgenda());

            if(ps.executeUpdate() != 0)
                result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
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
