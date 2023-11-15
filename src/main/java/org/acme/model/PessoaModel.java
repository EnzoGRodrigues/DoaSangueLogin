package org.acme.model;

import org.acme.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa") // nome da tabela no banco de dados tem que ser igual no serviço de cadastro
@Entity
public class PessoaModel { //preciso criar ter classe usuarioRepository e instituicaoRepository para dar um get no cpf ou cnpj.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public PessoaModel(String nome, String email, String senha, Role role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }
}
