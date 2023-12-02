package org.acme.controller;

import static io.restassured.RestAssured.given;

import org.acme.dto.LoginDTO;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


@QuarkusTest
public class AuthControllerTest {

        @Test
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
        }

//     @Test
//     void testandoAPILogin(){ //teste de api login - precisa estar com o banco mysql rodando
//         LoginDTO loginDTO = new LoginDTO("12345678910", "enzogabrielrodriguesdasilva");
//         Response response = given()
//         .contentType(ContentType.JSON)
//         .body(loginDTO)
//         .when().post("/auth/login");

//         response.then().statusCode(200);
//         response.then().body("token", Matchers.notNullValue());
//         response.then()
//                 .log()
//                 .all();
//     }

}