package org.acme.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.acme.dto.LoginDTO;
import org.acme.service.AuthService;
import org.jboss.jandex.Main;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AutenticacaoController {
    
    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) throws Exception {
        Logger logger = Logger.getLogger(Main.class.getName());
        if (loginDTO.documento() == null || loginDTO.senha() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Documento e senha são obrigatórios.").build();
        }
        String token = null;
        logger.info("Documento: " + loginDTO.documento() + " Senha: " + loginDTO.senha());
        if (isCPF(loginDTO.documento())) {
            token = authService.autenticaPorCpf(loginDTO.documento(), loginDTO.senha());
        } else if (isCNPJ(loginDTO.documento())) {
            token = authService.autenticaPorCnpj(loginDTO.documento(), loginDTO.senha());
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Documento inválido.").build();
        }

        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return Response.ok(response).build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Documento ou senha inválidos. Autenticacao falhou");
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(response)
                    .build();
        }
    }

    private boolean isCPF(String documento) {
        return documento.length() == 11;
    }

    private boolean isCNPJ(String documento) {
        return documento.length() == 14;
    }
}
