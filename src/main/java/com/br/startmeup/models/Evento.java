package com.br.startmeup.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;

    private Date dataInicio;

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
