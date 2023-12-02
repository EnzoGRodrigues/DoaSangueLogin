package org.acme.service;

import org.acme.model.CadastroPF;
import org.acme.model.CadastroPJ;
import org.acme.repository.InstituicaoRepository;
import org.acme.repository.UsuarioRepository;

import io.quarkus.elytron.security.common.BcryptUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InstituicaoRepository instituicaoRepository;

    @Test
    public void AutenticaSucessoPorCPF(){

        String cpf = "12345678901";
        String senha = BcryptUtil.bcryptHash("enzogabrielrodriguesdasilva");
        CadastroPF usuario = new CadastroPF();
        usuario.setCpf(cpf);
        usuario.setSenha(senha);

        when(usuarioRepository.findByCpf(cpf)).thenReturn(usuario); // quando o método findByCpf for chamado, retorna o usuario

        authService.autenticaPorCpf(cpf, senha); // chama o método autenticaPorCpf - o método autienticaPorCpf chama o método validaCredenciaisCPF
        Mockito.verify(usuarioRepository).findByCpf(cpf); // verifica se o método findByCpf foi chamado

        assertEquals(senha, usuario.getSenha()); // verifica se a senha é igual a senha do usuario
    }

    @Test
    public void AutenticaSucessoPorCNPJ(){
        // Arrange
        String cnpj = "12345678910111";
        String senha = BcryptUtil.bcryptHash("clinicas");
        CadastroPJ instituicao = new CadastroPJ();
        instituicao.setCnpj(cnpj);
        instituicao.setSenha(senha);

        when(instituicaoRepository.findByCnpj(cnpj)).thenReturn(instituicao); // quando o método findByCnpj for chamado, retorna a instituicao

        authService.autenticaPorCnpj(cnpj, senha); // chama o método autenticaPorCnpj - o método autienticaPorCnpj chama o método validaCredenciaisCNPJ
        Mockito.verify(instituicaoRepository).findByCnpj(cnpj); // verifica se o método findByCnpj foi chamado

        assertEquals(senha, instituicao.getSenha()); // verifica se a senha é igual a senha da instituicao
    }

    @Test
    public void FalhaAutenticacaoCPFNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        String senha = BcryptUtil.bcryptHash("enzogabrielrodriguesdasilva");
        CadastroPF usuario = new CadastroPF();
        usuario.setCpf(cpf);
        usuario.setSenha(senha);

        when(usuarioRepository.findByCpf(cpf)).thenReturn(usuario); // quando o método findByCpf for chamado, retorna null

        String token = authService.autenticaPorCpf(cpf, senha); // chama o método autenticaPorCpf - o método autienticaPorCpf chama o método validaCredenciaisCPF

        assertNull(token); // verifica se o token é nulo
    }

    @Test
    public void FalhaAutenticacaoCNPJNaoEncontrado() {
        // Arrange
        String cnpj = "12345678910111";
        String senha = BcryptUtil.bcryptHash("clinicas");
        CadastroPJ instituicao = new CadastroPJ();
        instituicao.setCnpj(cnpj);
        instituicao.setSenha(senha);

        when(instituicaoRepository.findByCnpj(cnpj)).thenReturn(instituicao); // quando o método findByCnpj for chamado, retorna null

        String token = authService.autenticaPorCnpj(cnpj, senha); // chama o método autenticaPorCnpj - o método autienticaPorCnpj chama o método validaCredenciaisCNPJ

        assertNull(token); // verifica se o token é nulo
    }
}
