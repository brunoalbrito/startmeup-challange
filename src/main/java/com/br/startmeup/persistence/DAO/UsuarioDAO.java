package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.connection.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements GenericDAO<Usuario> {

    private Connection connection;

    public UsuarioDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Usuario usuario) {
        return false;
    }

    @Override
    public Usuario findById(long id) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public boolean update(Usuario usuario) {
        return false;
    }

    @Override
    public boolean delete(Usuario usuario) {
        return false;
    }
}