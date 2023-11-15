package org.acme.service;

import java.util.List;
import java.util.logging.Logger;

import org.acme.model.InstituicaoModel;
import org.acme.repository.InstituicaoRepository;

import jakarta.inject.Inject;

public class InstituicaoService {

    public static final Logger LOGGER = Logger.getLogger(InstituicaoService.class.getName());

    @Inject
    InstituicaoRepository instituicaoRepository;

    public List<InstituicaoModel> listarInstituicoes() {
        return instituicaoRepository.listAll();
    }

    public InstituicaoModel buscarInstituicaoPorId(Long id) {
        return instituicaoRepository.findById(id);
    }

    public InstituicaoModel buscarInstituicaoPorCnpj(String cnpj) {
        return instituicaoRepository.findByCnpj(cnpj);
    }
    
}
