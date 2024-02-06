package org.acme.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.acme.dto.LoginDTO;
import org.acme.service.AuthService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AutenticacaoController {
    
    @Inject
    AuthService authService;
    private static final Logger logger = Logger.getLogger(AutenticacaoController.class.getName());
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) throws Exception { ///método que faz o login
        if (loginDTO.documento() == null || loginDTO.senha() == null) { ///verifica se o documento e a senha estão preenchidos
            return Response.status(Response.Status.BAD_REQUEST).entity("Documento e senha são obrigatórios.").build(); ///verifica se o documento e a senha estão preenchidos
        }
        String token = null;
        logger.info("Autenticando usuário...");
        if (isCPF(loginDTO.documento())) { ///verifica se o documento é cpf ou cnpj
            token = authService.autenticaPorCpf(loginDTO.documento(), loginDTO.senha()); ///se for cpf, chama o método autenticaPorCpf
        } else if (isCNPJ(loginDTO.documento())) { ///se for cnpj, chama o método autenticaPorCnpj
            token = authService.autenticaPorCnpj(loginDTO.documento(), loginDTO.senha()); ///se for cnpj, chama o método autenticaPorCnpj
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Documento inválido.").build(); ///se não for nenhum dos dois, retorna um erro
        }
        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            logger.info("Usuário autenticado com sucesso.");
            return Response.ok(response).build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Documento ou senha inválidos. Autenticacao falhou");
            logger.info("Documento ou senha inválidos. Autenticação falhou.");
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(response)
                    .build();
        }
    }

    public boolean isCPF(String documento) { ///faz a validação do cpf visando o tamanho do documento
        return documento.length() == 11;
    }

    public boolean isCNPJ(String documento) { ///faz a validação do cnpj visando o tamanho do documento
        return documento.length() == 14;
    }
}
