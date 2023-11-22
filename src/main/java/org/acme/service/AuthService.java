package org.acme.service;

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

    public static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    private GeraTokenJWT geraTokenJWT = new GeraTokenJWT();

    @Inject
    InstituicaoRepository instituicaoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    public String autenticaPorCpf(String cpf, String senha) {
        if (validaCredenciaisCPF(cpf, senha)) {
            return geraTokenJWT.gerarTokenJWT(cpf, "CPF");
        }
        return null;
    }

    public String autenticaPorCnpj(String cnpj, String senha) {
        if (validaCredenciaisCNPJ(cnpj, senha)) {
            return geraTokenJWT.gerarTokenJWT(cnpj, "CNPJ");
        }
        return null;
    }

    private boolean validaCredenciaisCPF(String cpf, String senha) {
        try {
            String pass = usuarioRepository.findByCpf(cpf).getSenha();
            if (Seguranca.verifyBCryptPassword(pass, senha)) {
                LOGGER.info("Senha valida authService");
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            // TO:DO execoes aqui
            LOGGER.info("Senha nao validou authService");
            e.printStackTrace();
        }
        return false;
    }

    private boolean validaCredenciaisCNPJ(String cnpj, String senha) {
        try {
            String pass = instituicaoRepository.findByCnpj(cnpj).getSenha();
            if (Seguranca.verifyBCryptPassword(pass, senha)) {
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            // TO:DO execoes aqui
            e.printStackTrace();
        }
        return false;
    }
}
