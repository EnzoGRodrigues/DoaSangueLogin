package org.acme.controller;

import static io.restassured.RestAssured.*;

import org.acme.enums.Role;
import org.acme.model.CadastroPF;
import org.acme.repository.UsuarioRepository;
import org.acme.service.AuthService;
import org.acme.utils.GeraTokenJWT;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.json.Json;
import jakarta.json.JsonObject;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
public class AutenticacaoControllerTest {

    @Mock
    AuthService authService;

    @InjectMock
    UsuarioRepository usuarioRepository;

    @Mock
    GeraTokenJWT geraTokenJWT;

    @Test
    public void testLogin() { //testando o login 

    CadastroPF cadastroPF = new CadastroPF("Leonardo", "enzo@teste", "enzogabrielrodriguesdasilva", "ruma uva",
    Role.USUARIO, "12345678910");
    usuarioRepository.persist(cadastroPF);
    when(usuarioRepository.findByCpf(cadastroPF.getCpf())).thenReturn(cadastroPF);
    JsonObject loginUsuario = Json.createObjectBuilder()
    .add("documento", cadastroPF.getCpf())
    .add("senha", cadastroPF.getSenha())
    .build();
    given()
    .contentType(MediaType.APPLICATION_JSON)
    .body(loginUsuario.toString())
    .when().post("http://localhost:8080/auth/login")
    .then()
    .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void testIsCPF() { //testando método de validação do cpf
        AutenticacaoController controller = new AutenticacaoController();
        
        // Test case: valid CPF
        String cpf = "12345678910";
        assertTrue(controller.isCPF(cpf));
        
        // Test case: invalid CPF
        cpf = "123456789";
        assertFalse(controller.isCPF(cpf));
    }

    @Test
    public void testIsCNPJ() { //testando método de validação do cnpj
        AutenticacaoController controller = new AutenticacaoController();
        
        // Test case: valid CNPJ
        String cnpj = "12345678910111";
        assertTrue(controller.isCNPJ(cnpj));
        
        // Test case: invalid CNPJ
        cnpj = "1234567891";
        assertFalse(controller.isCNPJ(cnpj));
    }
}