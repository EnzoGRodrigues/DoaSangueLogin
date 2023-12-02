//package org.acme.controller;
//
//import io.quarkus.test.InjectMock;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.json.Json;
//import jakarta.json.JsonObject;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import org.acme.enums.Role;
//import org.acme.model.CadastroPF;
//import org.acme.repository.UsuarioRepository;
//import org.acme.service.AuthService;
//import org.acme.utils.GeraTokenJWT;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import static io.restassured.RestAssured.given;
//import static org.mockito.Mockito.when;
//
//@QuarkusTest
//public class TesteAutentica2 {
//    @Mock
//    AuthService authService;
//
//    @InjectMock
//    UsuarioRepository usuarioRepository;
//
//    @Mock
//    GeraTokenJWT geraTokenJWT;
//
//    @Test
//    public void testLogin() { //testando o login - rodar esse teste com o projeto up
//
//        CadastroPF cadastroPF = new CadastroPF("Leonardo", "enzo@teste", "enzogabrielrodriguesdasilva", "ruma uva",
//                Role.USUARIO, "12345678910");
//        usuarioRepository.persist(cadastroPF);
//        when(usuarioRepository.findByCpf(cadastroPF.getCpf())).thenReturn(cadastroPF);
//        JsonObject loginUsuario = Json.createObjectBuilder()
//                .add("documento", cadastroPF.getCpf())
//                .add("senha", cadastroPF.getSenha())
//                .build();
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(loginUsuario.toString())
//                .when().post("http://localhost:8080/auth/login")
//                .then()
//                .statusCode(Response.Status.OK.getStatusCode());
//    }
//}
