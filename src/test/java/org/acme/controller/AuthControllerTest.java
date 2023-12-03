package org.acme.controller;

import static io.restassured.RestAssured.given;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
public class AuthControllerTest {

     /*@Test
     public void deveRetornarStatus200ComTokenValido() {
         // Define os dados de login
         LoginDTO loginDTO = new LoginDTO("12345678910", "enzogabrielrodriguesdasilva");

         // Realiza a chamada HTTP
         Response response = given()
             .contentType(ContentType.JSON)
             .body(loginDTO)
             .when()
             .post("/auth/login");

         // Verifica o status da resposta
         response.then().statusCode(200);

         // Verifica a presença do token na resposta
         response.then().body("token", notNullValue());
    }*/

    @Test
    public void DocumentoESenhaNaoFornecidos() { //retorna 400 quando não for fornecido o documento e a senha
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{}")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(400);
    }

    //    @Test
//    public void deveRetornarTokenQuandoDocumentoESenhaForemValidos() throws Exception {
//        LoginDTO loginDTO = new LoginDTO("12345678910", "enzogabrielrodriguesdasilva");
//
//            given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(loginDTO)
//                .when()
//                .post("/auth/login")
//                .then()
//                .statusCode(200);
////                .body("token", is("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"));
//    }
    @Test
    public void DocumentoInvalido() { // retorna 400 quando o documento for invalido
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{ \"documento\": \"123456789012345\", \"senha\": \"senha123\" }")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(400);
    }

//    @Test
//    public void deveRetornarUnauthorizedQuandoDocumentoOuSenhaForemInvalidos() {
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("{ \"documento\": \"12345678901\", \"senha\": \"senhaerrada\" }")
//                .when()
//                .post("/auth/login")
//                .then()
//                .statusCode(401);
//    }
}