package org.acme.service;

import org.acme.controller.AutenticacaoController;
import org.acme.model.AutenticacaoModel;
import org.acme.repository.AutenticacaoRepository;
import org.acme.utils.GeraTokenJWT;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutenticacaoService {

    private AutenticacaoRepository authRepository;
    private GeraTokenJWT geraTokenJWT;

    public AutenticacaoService(AutenticacaoRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String validaAutenticacao(String documento, String senha) {
        String token = null;
        String tipoDocumento = null;
        AutenticacaoModel auth = null;

        if (isCPF(documento)) {
            // Autenticar com CPF
            auth = authRepository.findByCpf(documento);
            tipoDocumento = "CPF";
        } else if (isCNPJ(documento)) {
            // Autenticar com CNPJ
            auth = authRepository.findByCnpj(documento);
            tipoDocumento = "CNPJ";
        }

        if (auth != null) { //TO DO: Comparar senha com hash
            if (auth.getSenha().equals(senha)) {
                token = geraTokenJWT.gerarTokenJWT(documento, tipoDocumento);
            }
        }
        return token;
    }

    boolean isCPF(String documento) {
        return documento.length() == 11;
    }

    boolean isCNPJ(String documento) {
        return documento.length() == 14;
    }
}
