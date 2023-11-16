package org.acme.service;

import org.acme.model.AutenticacaoModel;
import org.acme.model.UsuarioModel;
import org.acme.repository.AutenticacaoRepository;
import org.acme.repository.UsuarioRepository;
import org.acme.utils.GeraTokenJWT;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutenticacaoService {

    private AutenticacaoRepository authRepository;
    private UsuarioRepository usuarioRepository;
    private GeraTokenJWT geraTokenJWT;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public AutenticacaoService(AutenticacaoRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String validaAutenticacao(String documento, String senha) {
        String token = null;
        String tipoDocumento = null;
        // AutenticacaoModel auth = null;
        UsuarioModel usuario = null;

        if (isCPF(documento)) {
            // Autenticar com CPF
            usuario = usuarioRepository.findByCpf(documento);
            tipoDocumento = "CPF";
        } else if (isCNPJ(documento)) {
            // Autenticar com CNPJ
            auth = authRepository.findByCnpj(documento);
            tipoDocumento = "CNPJ";
        }

        if (usuario != null) { //TO DO: Comparar senha com hash
            if (usuario.getSenha().equals(senha)) {
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
