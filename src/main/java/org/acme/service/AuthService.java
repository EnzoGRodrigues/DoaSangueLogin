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

    public String autenticaPorCpf(String cpf, String senha) {
        if (validaCredenciaisCPF(cpf, senha)) {
            logger.info("Senha valida authService - método autenticaPorCpf");
            return geraTokenJWT.gerarTokenJWT(cpf, "CPF");
        }
        return null;
    }

    public String autenticaPorCnpj(String cnpj, String senha) {
        if (validaCredenciaisCNPJ(cnpj, senha)) {
            logger.info("Senha valida authService - método autenticaPorCnpj");
            return geraTokenJWT.gerarTokenJWT(cnpj, "CNPJ");
        }
        return null;
    }

    private boolean validaCredenciaisCPF(String cpf, String senha) {
        try {
            String pass = usuarioRepository.findByCpf(cpf).getSenha();
            if (Seguranca.verifyBCryptPassword(pass, senha)) {
                logger.info("Senha valida authService - método validaCredenciaisCPF");
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            // TO:DO execoes aqui
            logger.log(Level.WARNING, "Senha nao validou authService - método validaCredenciaisCPF");
            e.printStackTrace();
        }
        return false;
    }


    private boolean validaCredenciaisCNPJ(String cnpj, String senha) {
        try {
            CadastroPJ instituicao = instituicaoRepository.findByCnpj(cnpj);
            if (instituicao != null) {
                logger.info("Senha valida authService - método validaCredenciaisCNPJ");
                String pass = instituicao.getSenha();
                if (Seguranca.verifyBCryptPassword(pass, senha)) {
                    return true;
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            logger.log(Level.WARNING, "Senha nao validou authService - método validaCredenciaisCNPJ");
            e.printStackTrace();
        }
        return false;
    }
}
