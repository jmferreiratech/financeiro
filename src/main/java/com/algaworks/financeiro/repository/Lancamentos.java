package com.algaworks.financeiro.repository;

import com.algaworks.financeiro.model.Lancamento;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class Lancamentos implements Serializable {
    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    public Lancamentos(EntityManager manager) {
        this.manager = manager;
    }

    public List<Lancamento> todos() {
        TypedQuery<Lancamento> query = manager.createQuery("from Lancamento", Lancamento.class);
        return query.getResultList();
    }
}
