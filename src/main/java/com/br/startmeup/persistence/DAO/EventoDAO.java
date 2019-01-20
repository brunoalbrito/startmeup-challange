package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.interfaces.IEventoDAO;
import com.br.startmeup.models.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO implements IEventoDAO<Evento> {

    private  Connection connection;

    public EventoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Evento evento) {

        boolean result = false;

        String sql = "INSERT INTO startmeup.Evento(titulo, dataInicio, dataFim,usuario_id)VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, evento.getTitulo());
            ps.setTimestamp(2,new Timestamp(evento.getDataInicio().getTime()));
            ps.setTimestamp(3, new Timestamp(evento.getDataFim().getTime()));
            ps.setLong(4, evento.getFkUsuario());

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
        String sql = "SELECT * FROM startmeup.Evento WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                evento.setId(rs.getLong("id"));
                evento.setTitulo(rs.getString("titulo"));
                evento.setDataInicio(new java.util.Date(rs.getTimestamp("dataInicio").getTime()));
                evento.setDataFim(new java.util.Date(rs.getTimestamp("dataFim").getTime()));
                evento.setFkUsuario(rs.getLong("usuario_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evento;
    }

    @Override
    public List<Evento> findAll() {

        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM startmeup.Evento";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getLong("id"));
                evento.setTitulo(rs.getString("titulo"));
                evento.setDataInicio(new java.util.Date(rs.getTimestamp("dataInicio").getTime()));
                evento.setDataFim(new java.util.Date(rs.getTimestamp("dataFim").getTime()));
                evento.setFkUsuario(rs.getLong("usuario_id"));
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

        String sql = "UPDATE startmeup.Evento SET titulo = ?,dataInicio = ?, dataFim = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, evento.getTitulo());
            ps.setTimestamp(2, new Timestamp(evento.getDataInicio().getTime()));
            ps.setTimestamp(3, new Timestamp(evento.getDataFim().getTime()));
            ps.setLong(4,evento.getId());

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
        String sql = "DELETE FROM startmeup.Evento WHERE id =?";

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

    @Override
    public List<Evento> findByUserId(long id) {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM startmeup.Evento WHERE fkUsuario = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getLong("id"));
                evento.setTitulo(rs.getString("titulo"));
                evento.setDataInicio(new java.util.Date(rs.getTimestamp("dataInicio").getTime()));
                evento.setDataFim(new java.util.Date(rs.getTimestamp("dataFim").getTime()));
                evento.setFkUsuario(rs.getLong("usuario_id"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
}
