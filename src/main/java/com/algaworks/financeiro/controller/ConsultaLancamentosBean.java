package com.algaworks.financeiro.controller;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.util.JpaUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Lancamento> lancamentos;

    public void consultar() {
        EntityManager manager = JpaUtil.getEntityManager();
        Lancamentos lancamentos = new Lancamentos(manager);
        this.lancamentos = lancamentos.todos();
        manager.close();
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }
}
