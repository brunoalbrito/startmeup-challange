package com.br.startmeup.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String nome;

    private String email;

    private String senha;

    @OneToMany(targetEntity = Evento.class)
    private List<Evento> eventos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setAgendas(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", eventos=" + eventos +
                '}';
    }
}
