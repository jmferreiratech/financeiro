package com.algaworks.financeiro.controller;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.Lancamentos;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Lancamentos lancamentosRepository;

    private List<Lancamento> lancamentos;

    public void consultar() {
        this.lancamentos = lancamentosRepository.todos();
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }
}
