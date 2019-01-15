package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO implements GenericDAO<Agenda>{

    private Connection connection;

    public AgendaDAO(Connection connection){
        this.connection = connection;
    }


    @Override
    public boolean create(Agenda agenda) {

        String sql = "INSERT INTO agenda(nome, fkUsuario,descricao)VALUES(?, ?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, agenda.getNome());
            ps.setLong(2, agenda.getIdUsuario());
            ps.setString(3, agenda.getDescricao());


            if(ps.executeUpdate() != 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Agenda findById(long id) {
        Agenda agenda = new Agenda();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM agenda WHERE id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                agenda.setId(rs.getLong("id"));
                agenda.setNome(rs.getString("nome"));
                agenda.setDescricao(rs.getString("descricao"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return agenda;
    }

    @Override
    public List<Agenda> findAll() {
        List<Agenda> agendas = new ArrayList<>();

        String sql = "SELECT * FROM agenda";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Agenda agenda = new Agenda();
                agenda.setId(rs.getLong("id"));
                agenda.setNome(rs.getString("nome"));
                agenda.setDescricao(rs.getString("descricao"));
                agendas.add(agenda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendas;
    }

    @Override
    public boolean update(Agenda agenda) {
        String sql = "UPDATE agenda SET nome=?, descricao=? WHERE id =?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, agenda.getNome());
            ps.setString(2, agenda.getDescricao());
            ps.setLong(3, agenda.getId());

            if(ps.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Agenda agenda) {

        String sql = "DELETE FROM agenda WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, agenda.getId());
            if(ps.executeUpdate() != 0){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}