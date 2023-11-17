package org.acme.controller;

import org.acme.model.AutenticacaoModel;
import org.acme.service.AuthService;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutenticacaoController {
    private final AuthService authService;

    public AutenticacaoController(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/login")
    public Response login(AutenticacaoModel autenticacaoModel) {
        String tokeString = authService.validaAutenticacao(autenticacaoModel.getDocumento(), autenticacaoModel.getSenha());
        return Response.status(Response.Status.OK).entity(tokeString).build();
    }
}
