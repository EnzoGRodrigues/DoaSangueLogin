package org.acme.service;

import org.acme.model.CadastroPF;
import org.acme.repository.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class UsuarioService {

    public static final Logger LOGGER = Logger.getLogger(UsuarioService.class.getName());

    @Inject
    UsuarioRepository usuarioRepository;

    public List<CadastroPF> listarUsuarios() {
        return usuarioRepository.listAll();
    }

    public CadastroPF buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public CadastroPF buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
    
}
