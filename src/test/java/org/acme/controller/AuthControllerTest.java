package org.acme.controller;

import static io.restassured.RestAssured.given;



import org.acme.dto.LoginDTO;

import org.hamcrest.*;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


@QuarkusTest

public class AuthControllerTest {

    @Test
    void testlogin(){ //teste de api login
        LoginDTO loginDTO = new LoginDTO("12345678910", "enzogabrielrodriguesdasilva");
        Response response = given()
        .contentType(ContentType.JSON)
        .body(loginDTO)
        .when().post("/auth/login");

        response.then().statusCode(200);
        response.then().body("token", Matchers.notNullValue());
        response.then()
                .log()
                .all();
    }

}