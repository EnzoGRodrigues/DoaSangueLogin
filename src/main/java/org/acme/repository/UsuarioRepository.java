package org.acme.repository;

import org.acme.model.CadastroPF;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<CadastroPF>{
    public CadastroPF findByCpf(String cpf){
        return find("cpf",cpf).firstResult();
    }
    
}
