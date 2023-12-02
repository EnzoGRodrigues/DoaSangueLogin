// package org.acme.controller;

// import static io.restassured.RestAssured.given;


// import org.junit.jupiter.api.Test;

// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.http.ContentType;
// import io.restassured.response.Response;


// @QuarkusTest

// public class AuthControllerTest {

//     @Test
//     void testandoAPILogin(){ //teste de api login - precisa estar com o banco mysql rodando
// //        LoginDTO loginDTO = new LoginDTO("12345678910", "enzogabrielrodriguesdasilva");
//         Response response = given()
//         .contentType(ContentType.JSON)
//         .body("{\"documento\":\"12345678910\",\"senha\":\"enzogabrielrodriguesdasilva\"}")
//         .when().post("/auth/login");

//         response.then().statusCode(200);
// //        response.then().body("token", Matchers.notNullValue());
//         response.then()
//                 .log()
//                 .all();
//     }

// }