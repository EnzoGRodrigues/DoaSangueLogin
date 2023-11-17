package org.acme.repository;

import org.acme.model.UsuarioModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<UsuarioModel>{
    public UsuarioModel findByCpf(String cpf){
        return find("cpf = ?1",cpf).firstResult();
    }
    
}
