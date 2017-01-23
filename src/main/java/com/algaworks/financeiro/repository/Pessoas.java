package com.algaworks.financeiro.repository;

import com.algaworks.financeiro.model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class Pessoas implements Serializable {
    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    public Pessoas(EntityManager manager) {
        this.manager = manager;
    }

    public Pessoa porId(Long id) {
        return manager.find(Pessoa.class, id);
    }

    public List<Pessoa> todas() {
        TypedQuery<Pessoa> query = manager.createQuery("from Pessoa", Pessoa.class);
        return query.getResultList();
    }
}
