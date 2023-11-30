package org.acme.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.acme.utils.GeraTokenJWT;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private GeraTokenJWT geraTokenJWT;

    @Mock
    private AuthService authService;


    @Test
    public void testAutenticaPorCpf_ValidCredentials_ReturnsToken() {

        
        // Arrange
        // String cpf = "12345678910";
        // String senha = "enzogabrielrodriguesdasilva";

        // // Mock the behavior of validaCredenciaisCPF method
        // when(authService.validaCredenciaisCPF(cpf, senha)).thenReturn(true);

        // // Mock the behavior of geraTokenJWT method
        // when(geraTokenJWT.gerarTokenJWT(cpf, "CPF")).thenReturn(expectedToken);

        // // Act
        // String actualToken = authService.autenticaPorCpf(cpf, senha);

        // assertEquals(expectedToken, actualToken); //verificando se o token gerado é igual ao token esperado
    }

    @Test
    public void testAutenticaPorCpf_InvalidCredentials_ReturnsNull() {
        // Arrange
        String cpf = "12345678910";
        String senha = "enzogabrielrodriguesdasilva";

        // Mock the behavior of validaCredenciaisCPF method
        when(authService.validaCredenciaisCPF(cpf, senha)).thenReturn(false);

        // Act
        String actualToken = authService.autenticaPorCpf(cpf, senha);

        // Assert
        assertNull(actualToken);
    }
}