package org.acme.repository;

import org.acme.model.CadastroPJ;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InstituicaoRepository implements PanacheRepository<CadastroPJ>{

    public CadastroPJ findByCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }
}
