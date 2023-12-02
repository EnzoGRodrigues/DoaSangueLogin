package org.acme.service;

import java.util.List;
import java.util.logging.Logger;

import org.acme.model.CadastroPJ;
import org.acme.repository.InstituicaoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InstituicaoService {

    public static final Logger LOGGER = Logger.getLogger(InstituicaoService.class.getName());

    @Inject
    InstituicaoRepository instituicaoRepository;

    public List<CadastroPJ> listarInstituicoes() {
        return instituicaoRepository.listAll();
    }

    public CadastroPJ buscarInstituicaoPorId(Long id) {
        return instituicaoRepository.findById(id);
    }

    public CadastroPJ buscarInstituicaoPorCnpj(String cnpj) {
        return instituicaoRepository.findByCnpj(cnpj);
    }
    
}
