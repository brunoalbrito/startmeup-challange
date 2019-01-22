package com.br.startmeup.models;

import com.br.startmeup.DTO.ObjectResponse;
import com.br.startmeup.Enum.StatusEvento;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private long id;

    @Expose
    private String nome;

    @Expose
    private Date dataInicio;

    @Expose
    private Date dataFim;

    @OneToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Expose
    private StatusEvento statusEvento;

    @Expose
    private int prioridade;

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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusEvento getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(StatusEvento statusEvento) {
        this.statusEvento = statusEvento;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", usuario=" + usuario +
                ", statusEvento=" + statusEvento +
                ", prioridade=" + prioridade +
                '}';
    }
}
