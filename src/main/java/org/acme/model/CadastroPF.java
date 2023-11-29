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
public class CadastroPF extends PessoaModel{

    @NotEmpty
    @NotBlank
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Role role;

    public CadastroPF(String nome, String email, String senha, String endereco, Role role, String cpf) {
        super(nome, email, senha, endereco);
        this.cpf = cpf;
        this.role = role;
    }
}
