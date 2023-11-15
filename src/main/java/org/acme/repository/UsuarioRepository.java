package org.acme.repository;

import org.acme.model.UsuarioModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UsuarioRepository implements PanacheRepository<UsuarioModel>{
    public UsuarioModel findByCpf(String cpf){
        return find("cpf",cpf).firstResult();
    }
    
}
