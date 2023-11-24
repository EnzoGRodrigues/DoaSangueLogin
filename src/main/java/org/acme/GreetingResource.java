package org.acme;

import org.acme.enums.Role;
import org.acme.model.InstituicaoModel;
import org.acme.model.UsuarioModel;
import org.acme.repository.InstituicaoRepository;
import org.acme.repository.UsuarioRepository;
import org.jboss.jandex.Main;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


import java.util.logging.Level;
import java.util.logging.Logger;
import io.quarkus.elytron.security.common.BcryptUtil;


@Path("/carga")
public class GreetingResource {

    @Inject
    InstituicaoRepository instituicaoRepository;

    @Inject
    UsuarioRepository usuarioRepository;
    @GET
    @Transactional
    public void carga() {
        Logger logger = Logger.getLogger(Main.class.getName());

        String senha = BcryptUtil.bcryptHash("enzogabrielrodriguesdasilva");
        UsuarioModel usuario = new UsuarioModel("Leonardo"," enzo@teste", senha, "rua teste", Role.USUARIO, "12345678910");
        logger.log(Level.INFO,"Inserindo usuario: {0}", usuario);
        usuarioRepository.persist(usuario);

        String senhaHash = BcryptUtil.bcryptHash("clinicas");
        InstituicaoModel instituicao = new InstituicaoModel("Clinicas","clinicas@teste",senhaHash,"rua teste",Role.INSTITUICAO,"12345678910111");
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao);
        instituicaoRepository.persist(instituicao);

        String senhaHash2 = BcryptUtil.bcryptHash("hps");
        InstituicaoModel instituicao2 = new InstituicaoModel("hps","hps@teste",senhaHash2,"rua teste",Role.INSTITUICAO,"12345678910112");
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao2);
        instituicaoRepository.persist(instituicao2);
    }
}
