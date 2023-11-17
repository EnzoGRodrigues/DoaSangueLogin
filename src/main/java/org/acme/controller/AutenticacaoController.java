package org.acme.controller;

import org.acme.dto.LoginDTO;
import org.acme.service.AuthService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AutenticacaoController {
    private final AuthService authService;

    public AutenticacaoController(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) throws Exception {
        if (loginDTO.documento() == null || loginDTO.senha() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Documento e senha são obrigatórios.").build();
        }
        String token = null;

        if (isCPF(loginDTO.documento())) {
            token = authService.autenticaPorCpf(loginDTO.documento(), loginDTO.senha());
        } else if (isCNPJ(loginDTO.documento())) {
            token = authService.autenticaPorCpf(loginDTO.documento(), loginDTO.senha());
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Documento inválido.").build();
        }

        if (token != null) {
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Documento ou senha inválidos. Autenticacao falhou").build();
        }
    }

    private boolean isCPF(String documento) {
        return documento.length() == 11;
    }

    private boolean isCNPJ(String documento) {
        return documento.length() == 14;
    }
}
