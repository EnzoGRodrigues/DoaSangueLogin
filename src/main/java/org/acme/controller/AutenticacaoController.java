package org.acme.controller;

import org.acme.model.AutenticacaoModel;
import org.acme.service.AutenticacaoService;


import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutenticacaoController {
    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @POST
    @Path("/login")
    public Response login(AutenticacaoModel autenticacaoModel) {
        String token = autenticacaoService.validaAutenticacao(autenticacaoModel.getDocumento(), autenticacaoModel.getSenha());
        return Response.status(Response.Status.OK).entity(token).build();
    }
}
