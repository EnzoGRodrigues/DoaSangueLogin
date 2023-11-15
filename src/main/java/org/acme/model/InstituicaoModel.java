package org.acme.model;
import org.acme.enums.Role;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoModel extends PessoaModel {

    @NotBlank
    @NotEmpty
    @NonNull
    private String cnpj;
    
    public InstituicaoModel(String nome, String email, String senha, Role role, String cnpj) {
        super(nome, email, senha, role);
        this.cnpj = cnpj;
    }

    public InstituicaoModel(String nome, String email, String senha, String cnpj) {
        super(nome, email, senha, Role.INSTITUICAO);
        this.cnpj = cnpj;
    }
}
