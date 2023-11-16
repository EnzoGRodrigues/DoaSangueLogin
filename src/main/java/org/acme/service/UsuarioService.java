package org.acme.service;

import org.acme.model.UsuarioModel;
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

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.listAll();
    }

    public UsuarioModel buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
    
}
