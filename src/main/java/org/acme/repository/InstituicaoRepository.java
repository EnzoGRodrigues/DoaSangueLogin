package org.acme.repository;

import org.acme.model.InstituicaoModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class InstituicaoRepository implements PanacheRepository<InstituicaoModel>{

    public InstituicaoModel findByCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }

    
}
