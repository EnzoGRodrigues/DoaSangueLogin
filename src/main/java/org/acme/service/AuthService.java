package org.acme.service;

import java.util.logging.Logger;

import org.acme.model.AutenticacaoModel;
import org.acme.repository.AutenticacaoRepository;
import org.acme.repository.InstituicaoRepository;
import org.acme.repository.UsuarioRepository;
import org.acme.utils.GeraTokenJWT;
import org.acme.utils.Seguranca;

import jakarta.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.InvalidKeyException;

public class AuthService {

    public static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    private GeraTokenJWT geraTokenJWT;

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

    private String autenticaPorCnpj(String cnpj, String senha) {
        if (validaCredenciaisCNPJ(cnpj, senha)) {
            return geraTokenJWT.gerarTokenJWT(cnpj, "CNPJ");
        }
        return null;
    }

    // ...

    private boolean validaCredenciaisCPF(String cpf, String senha) {
        try {
            String pass = usuarioRepository.findByCpf(cpf).getSenha();
            if (Seguranca.verifyBCryptPassword(senha, pass)) {
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            // TO:DO execoes aqui
            e.printStackTrace();
        }
        return false;
    }

    private boolean validaCredenciaisCNPJ(String cnpj, String senha) {
        try {
            String pass = instituicaoRepository.findByCnpj(cnpj).getSenha();
            if (Seguranca.verifyBCryptPassword(senha, pass)) {
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            // TO:DO execoes aqui
            e.printStackTrace();
        }
        return false;
    }

    private boolean isCPF(String documento) {
        // TODO: Implement CPF validation logic
        return documento.length() == 11;
    }

    private boolean isCNPJ(String documento) {
        // TODO: Implement CNPJ validation logic
        return documento.length() == 14;
    }

    public String validaAutenticacao(String documento, String senha) {
        String token = null;
        String tipoDocumento = null;
        AutenticacaoModel auth = null;
        AutenticacaoRepository authRepository = null;

        if (isCPF(documento)) {
            auth = authRepository.findByCpf(documento);
            tipoDocumento = "CPF";
        } else if (isCNPJ(documento)) {
            auth = authRepository.findByCnpj(documento);
            tipoDocumento = "CNPJ";
        }

        if (auth != null) {
            if (auth.getSenha().equals(senha)) {
                token = geraTokenJWT.gerarTokenJWT(documento, tipoDocumento);
            }
        }

        return token;
    }
}
