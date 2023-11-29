package org.acme.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cadastro") // nome da tabela no banco de dados tem que ser igual no serviço de cadastro
@Entity
public class PessoaModel { //preciso criar ter classe usuarioRepository e instituicaoRepository para dar um get no cpf ou cnpj.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    
    public PessoaModel(String nome, String email, String senha, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }
}
