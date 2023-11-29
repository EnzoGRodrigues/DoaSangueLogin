// package org.acme.service;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;

// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import static org.mockito.Mockito.*;
// import org.mockito.junit.jupiter.MockitoExtension;

// import io.quarkus.elytron.security.common.BcryptUtil;

// import org.acme.model.CadastroPF;
// import org.acme.model.CadastroPJ;
// import org.acme.repository.InstituicaoRepository;
// import org.acme.repository.UsuarioRepository;
// import org.acme.enums.Role;

// @ExtendWith(MockitoExtension.class)
// public class AuthServiceTest {

//     @Mock
//     private InstituicaoRepository instituicaoRepository;

//     @Mock
//     private UsuarioRepository usuarioRepository;

//     @InjectMocks
//     private AuthService authService;

//     // @Test
//     // public void testAutenticaPorCnpj_ValidCredentials_ReturnsToken() {
//     //     // Arrange
//     //     String cnpj = "123456789";
//     //     String senhaHash = BcryptUtil.bcryptHash("variassenhasjuntas");
//     //     when(instituicaoRepository.findByCnpj(cnpj)).thenReturn(new CadastroPJ("hps","hps@teste",senhaHash,"rua teste",Role.INSTITUICAO,"12345678910112"));

//     //     // Act
//     //     String token = authService.autenticaPorCnpj(cnpj, senhaHash);

//     //     // Assert
//     //     Assertions.assertNotNull(token);
//     // }

//     // @Test
//     // public void testAutenticaPorCnpj_InvalidCredentials_ReturnsNull() {
//     //     // Arrange
//     //     String cnpj = "123456789";
//     //     String senhaHash2 = BcryptUtil.bcryptHash("variassenhasjuntas");
//     //     when(instituicaoRepository.findByCnpj(cnpj)).thenReturn(new CadastroPJ("hps","hps@teste",senhaHash2,"rua teste",Role.INSTITUICAO,"12345678910112"));

//     //     // Act
//     //     String token = authService.autenticaPorCnpj(cnpj, senhaHash2);

//     //     // Assert
//     //     Assertions.assertNull(token);
//     // }
// }
// // Existing code...

// @Test
// public void testAutenticaPorCpf_ValidCredentials_ReturnsToken() {
//     // Arrange
//     String cpf = "123456789";
//     String senha = "variassenhasjuntas";
//     when(authService.validaCredenciaisCPF(cpf, senha)).thenReturn(true);
//     when(geraTokenJWT.gerarTokenJWT(cpf, "CPF")).thenReturn("token");

//     // Act
//     String token = authService.autenticaPorCpf(cpf, senha);

//     // Assert
//     Assertions.assertEquals("token", token);
// }

// @Test
// public void testAutenticaPorCpf_InvalidCredentials_ReturnsNull() {
//     // Arrange
//     String cpf = "123456789";
//     String senha = "variassenhasjuntas";
//     when(authService.validaCredenciaisCPF(cpf, senha)).thenReturn(false);

//     // Act
//     String token = authService.autenticaPorCpf(cpf, senha);

//     // Assert
//     Assertions.assertNull(token);
// }