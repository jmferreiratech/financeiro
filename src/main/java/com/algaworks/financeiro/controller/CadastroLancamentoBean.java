package com.algaworks.financeiro.controller;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;
import com.algaworks.financeiro.util.JpaUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Lancamento lancamento = new Lancamento();
    private List<Pessoa> todasPessoas;

    public void prepararCadastro() {
        EntityManager manager = JpaUtil.getEntityManager();
        try {
            Pessoas pessoas = new Pessoas(manager);
            this.todasPessoas = pessoas.todas();
        } finally {
            manager.close();
        }
    }

    public void salvar() {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction trx = manager.getTransaction();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            trx.begin();
            CadastroLancamentos cadastro = new CadastroLancamentos(new Lancamentos(manager));
            cadastro.salvar(this.lancamento);

            this.lancamento = new Lancamento();
            context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
            trx.commit();
        } catch (NegocioException e) {
            trx.rollback();

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        } finally {
            manager.close();
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
}
