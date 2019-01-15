package com.br.startmeup.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String nome;

    private String descricao;

    private long IdUsuario;

    @OneToMany
    private List<Evento> eventos;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public long getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        IdUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", IdUsuario=" + IdUsuario +
                ", eventos=" + eventos +
                '}';
    }
}
