package org.acme.repository;

import org.acme.model.InstituicaoModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InstituicaoRepository implements PanacheRepository<InstituicaoModel>{

    public InstituicaoModel findByCnpj(String cnpj) {
        return find("cnpj = ?1", cnpj).firstResult();
    }

    
}
