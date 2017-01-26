package com.algaworks.financeiro.converter;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.Lancamentos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Lancamento.class)
public class LancamentosConverter implements Converter {

    @Inject
    private Lancamentos lancamentos;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        Lancamento retorno = null;
        if (value != null && !"".equals(value)) {
            retorno = this.lancamentos.porId(new Long(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value != null) {
            Lancamento lancamento = (Lancamento) value;
            return lancamento.getId() == null ? null : lancamento.getId().toString();
        }
        return null;
    }
}
