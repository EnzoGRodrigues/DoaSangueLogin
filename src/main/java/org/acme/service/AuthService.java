package org.acme.service;
import org.acme.model.CadastroPJ;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.acme.repository.InstituicaoRepository;
import org.acme.repository.UsuarioRepository;
import org.acme.utils.GeraTokenJWT;
import org.acme.utils.Seguranca;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.InvalidKeyException;

@ApplicationScoped
public class AuthService {

    public static final Logger logger = Logger.getLogger(AuthService.class.getName());

    private GeraTokenJWT geraTokenJWT = new GeraTokenJWT();

    @Inject
    InstituicaoRepository instituicaoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    public String autenticaPorCpf(String cpf, String senha) { ///método que autentica o cpf
        if (validaCredenciaisCPF(cpf, senha)) { ///chama o método validaCredenciaisCPF
            logger.info("Senha valida authService - método autenticaPorCpf");
            return geraTokenJWT.gerarTokenJWT(cpf, "CPF"); ///se a senha for valida, gera o token
        }
        return null;
    }

    public String autenticaPorCnpj(String cnpj, String senha) { ///método que autentica o cnpj
        if (validaCredenciaisCNPJ(cnpj, senha)) { ///chama o método validaCredenciaisCNPJ
            logger.info("Senha valida authService - método autenticaPorCnpj");
            return geraTokenJWT.gerarTokenJWT(cnpj, "CNPJ"); ///se a senha for valida, gera o token
        }
        return null;
    }

    public boolean validaCredenciaisCPF(String cpf, String senha) { ///método que valida as credenciais do cpf
        try {
            String pass = usuarioRepository.findByCpf(cpf).getSenha(); ///busca o cpf no banco de dados
            if (Seguranca.verifyBCryptPassword(pass, senha)) { ///verifica se a senha é valida
                logger.info("Senha valida authService - método validaCredenciaisCPF");
                return true; ///se a senha for valida, retorna true
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            // TO:DO execoes aqui
            logger.log(Level.WARNING, "Senha nao validou authService - método validaCredenciaisCPF");
            e.printStackTrace();
        }
        return false;
    }


    public boolean validaCredenciaisCNPJ(String cnpj, String senha) { ///método que valida as credenciais do cnpj
        try {
            CadastroPJ instituicao = instituicaoRepository.findByCnpj(cnpj); ///busca o cnpj no banco de dados
            if (instituicao != null) { ///verifica se o cnpj existe
                logger.info("Senha valida authService - método validaCredenciaisCNPJ");
                String pass = instituicao.getSenha(); ///busca a senha do cnpj no banco de dados
                if (Seguranca.verifyBCryptPassword(pass, senha)) { ///verifica se a senha é valida
                    return true; ///se a senha for valida, retorna true
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            logger.log(Level.WARNING, "Senha nao validou authService - método validaCredenciaisCNPJ");
            e.printStackTrace();
        }
        return false;
    }
}
