package org.acme.repository;

import org.acme.model.AutenticacaoModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutenticacaoRepository implements PanacheRepository<AutenticacaoModel> {

    public AutenticacaoModel findByCnpj(String cnpj) {
        String sql = "SELECT * FROM pessoa WHERE cnpj = ?1";
        return find(sql, cnpj).firstResult();
    }

    // public AutenticacaoModel findByCpf(String cpf) {
    //     String sql = "SELECT * FROM pessoa WHERE cpf = ?1";
    //     return find(sql, cpf).firstResult();
    // }

    
    

}
