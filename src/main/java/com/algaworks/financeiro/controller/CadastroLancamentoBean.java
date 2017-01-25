package com.algaworks.financeiro.controller;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroLancamentos cadastro;
    @Inject
    private Pessoas pessoas;

    private Lancamento lancamento = new Lancamento();
    private List<Pessoa> todasPessoas;

    public void prepararCadastro() {
        this.todasPessoas = pessoas.todas();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            cadastro.salvar(this.lancamento);

            this.lancamento = new Lancamento();
            context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public List<Pessoa> getTodasPessoas() {
        return this.todasPessoas;
    }

    public TipoLancamento[] getTiposLancamentos() {
        return TipoLancamento.values();
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
        if (this.lancamento.getDataPagamento() == null) {
            this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
        }
    }
}
