package com.br.startmeup.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private long id;

    @Expose
    private String titulo;

    @Expose
    private Date dataInicio;

    @Expose
    private Date dataFim;

    @OneToOne
    private Usuario usuario;

    @Transient
    private long fkUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public long getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(long fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", usuario=" + usuario +
                '}';
    }
}
