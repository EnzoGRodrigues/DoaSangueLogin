package org.acme.model;
import org.acme.enums.Role;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroPJ extends PessoaModel {

    @NotBlank
    @NotEmpty
    private String cnpj;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public CadastroPJ(String nome, String email, String senha, String endereco, Role role, String cnpj) {
        super(nome, email, senha, endereco);
        this.cnpj = cnpj;
        this.role = role;
    }

    // public InstituicaoModel(String nome, String email, String senha, String cnpj) {
    //     super(nome, email, senha, Role.INSTITUICAO);
    //     this.cnpj = cnpj;
    // }
}
