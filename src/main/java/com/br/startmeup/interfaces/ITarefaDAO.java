package com.br.startmeup.interfaces;

import com.br.startmeup.models.Tarefa;

import java.util.List;

public interface ITarefaDAO<T> extends GenericDAO<T> {
    List<Tarefa> findByUserId(long id);
}
