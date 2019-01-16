package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Evento;

import java.sql.*;
import java.util.ArrayList;
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
            ps.setTimestamp(3, new Timestamp(evento.getData().getTime()));
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

        Evento evento = new Evento();
        String sql = "SELECT * FROM evento WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                evento.setNome(rs.getString("nome"));
                evento.setEndereco(rs.getString("endereco"));
                evento.setData(new java.util.Date(rs.getDate("dataEvent").getTime()));
                evento.setFkAgenda(rs.getLong("fkAgenda"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evento;
    }

    @Override
    public List<Evento> findAll() {

        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM evento";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Evento evento = new Evento();
                evento.setNome(rs.getString("nome"));
                evento.setEndereco(rs.getString("endereco"));
                evento.setData(new java.util.Date(rs.getDate("dataEvent").getTime()));
                evento.setFkAgenda(rs.getLong("fkAgenda"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    @Override
    public boolean update(Evento evento) {
        boolean result = false;

        String sql = "UPDATE evento SET nome = ?,endereco = ?,dataEvent = ?, fkAgenda = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, evento.getNome());
            ps.setString(2,evento.getEndereco());
            ps.setTimestamp(3, new Timestamp(evento.getData().getTime()));
            ps.setLong(4, evento.getFkAgenda());
            ps.setLong(5,evento.getId());

            if(ps.executeUpdate() != 0)
                result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Evento evento) {

        List<Evento> eventos = new ArrayList<>();
        String sql = "DROP evento WHERE id = ?";

        boolean result = false;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,evento.getId());
            if(ps.executeUpdate() != 0)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
