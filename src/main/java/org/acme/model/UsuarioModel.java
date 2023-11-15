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
public class UsuarioModel extends PessoaModel{

    @NotEmpty
    @NotBlank
    @NonNull
    private String cpf;

    public UsuarioModel(String nome, String email, String senha, Role role, String cpf) {
        super(nome, email, senha, role);
        this.cpf = cpf;
    }

    public UsuarioModel(String nome, String email, String senha, String cpf) {
        super(nome, email, senha, Role.USUARIO);
        this.cpf = cpf;
    }
}
