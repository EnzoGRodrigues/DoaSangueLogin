package org.acme.controller;

import static io.restassured.RestAssured.given;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
public class AuthControllerTest {


    //Teste 3 - Testando o login retornando 400 quando não for fornecido o documento e a senha
    @Test
    public void DocumentoESenhaNaoFornecidos() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{}")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(400);
    }


    //Teste 4 - Testando o login retornando 400 quando o documento for invalido
    @Test
    public void DocumentoInvalido() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{ \"documento\": \"123456789012345\", \"senha\": \"senha123\" }")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(400);
    }

}