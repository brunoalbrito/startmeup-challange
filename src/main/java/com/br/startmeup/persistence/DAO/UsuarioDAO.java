package com.br.startmeup.persistence.DAO;

import com.br.startmeup.interfaces.GenericDAO;
import com.br.startmeup.interfaces.IUsuarioDAO;
import com.br.startmeup.models.Usuario;
import com.br.startmeup.persistence.connection.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO<Usuario> {

    private Connection connection;

    public UsuarioDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Usuario usuario) {

        String  sql = "INSERT INTO startmeup.Usuario(nome,email,senha)VALUES(?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            if(ps.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Usuario findById(long id) {

        Usuario usuario = new Usuario();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM startmeup.Usuario WHERE id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM startmeup.Usuario");
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
        String sql = "UPDATE startmeup.Usuario SET nome=?, email=?, senha=? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setLong(4, usuario.getId());

            if(ps.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Usuario usuario) {
        String sql = "DELETE FROM startmeup.Usuario WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, usuario.getId());
            if(ps.executeUpdate() != 0){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = new Usuario();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM startmeup.Usuario WHERE email = ?");
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
