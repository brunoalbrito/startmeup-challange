package com.br.startmeup.interfaces;

public interface IUsuarioDAO<T> extends GenericDAO<T> {
    T findByEmail(String email);
}
