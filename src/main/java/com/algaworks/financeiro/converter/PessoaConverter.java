package com.algaworks.financeiro.converter;

import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.util.JpaUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Pessoa retorno = null;
        EntityManager manager = JpaUtil.getEntityManager();
        try {
            if (value != null && !"".equals(value)) {
                Pessoas pessoas = new Pessoas(manager);
                retorno = pessoas.porId(new Long(value));
            }
            return retorno;
        } finally {
            manager.close();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Pessoa) value).getId().toString();
        }
        return null;
    }
}
