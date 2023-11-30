//package org.acme.service;
//
//import io.quarkus.elytron.security.common.BcryptUtil;
//import org.acme.enums.Role;
//import org.acme.model.CadastroPF;
//import org.acme.repository.InstituicaoRepository;
//import org.acme.repository.UsuarioRepository;
//import org.acme.utils.Seguranca;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import org.acme.utils.GeraTokenJWT;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//
//
//@ExtendWith(MockitoExtension.class)
//public class AuthServiceTest {
//
//    @Mock
//    private GeraTokenJWT geraTokenJWT;
//
//    @InjectMocks
//    private AuthService authService;
//
//
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//
//    @Test
//    public void testAutenticaPorCpf_ValidCredentials_ReturnsToken() {
//
//         String cpf = "12345678910";
//         String senha = "enzogabrielrodriguesdasilva";
//         String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkxMCIsIm5hbWUiOiJFbnpvIEdhYnJpZWwgUm9kcmlndWVzIERhc2lsdmEiLCJyb2xlIjoiUk9MRV9DUEYiLCJpYXQiOjE2MjU5NjY0NzksImV4cCI6MTYy";
//
//         // Mock the behavior of validaCredenciaisCPF method
//         when(authService.validaCredenciaisCPF(cpf, senha)).thenReturn(true);
////         assertTrue(authService.validaCredenciaisCPF(cpf, senha));
////         assertEquals(expectedToken, geraTokenJWT.gerarTokenJWT(cpf, "CPF"));
//
//         // Simule o comportamento do método geraTokenJWT
////         when(geraTokenJWT.gerarTokenJWT(cpf, "CPF")).thenReturn(expectedToken);
//
//         // Act
////         String actualToken = authService.autenticaPorCpf(cpf, senha);
//
////         assertEquals(expectedToken, actualToken); //verificando se o token gerado é igual ao token esperado
//    }
//
//    @Test
//    public void testAutenticaPorCpf() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
//        // Mocking data
//        String cpf = "12345678910";
//        String senha = BcryptUtil.bcryptHash("enzogabrielrodriguesdasilva");
//
//        // Mocking behavior
//        when(usuarioRepository.findByCpf(cpf)).thenReturn(new CadastroPF("Enzo Rodrigues", "enzo@gmail.com", senha, "av rio verde", Role.USUARIO, cpf ));
//        when(Seguranca.verifyBCryptPassword(senha, "enzogabrielrodriguesdasilva")).thenReturn(true);
//        when(geraTokenJWT.gerarTokenJWT(cpf, "CPF")).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkxMCIsIm5hbWUiOiJFbnpvIEdhYnJpZWwgUm9kcmlndWVzIERhc2lsdmEiLCJyb2xlIjoiUk9MRV9DUEYiLCJpYXQiOjE2MjU5NjY0NzksImV4cCI6MTYy");
//
//        // Testing the method
//        String token = authService.autenticaPorCpf(cpf, senha);
//
//        // Verifying the results
//        assertNotNull(token);
//        assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkxMCIsIm5hbWUiOiJFbnpvIEdhYnJpZWwgUm9kcmlndWVzIERhc2lsdmEiLCJyb2xlIjoiUk9MRV9DUEYiLCJpYXQiOjE2MjU5NjY0NzksImV4cCI6MTYy", token);
//    }
//}