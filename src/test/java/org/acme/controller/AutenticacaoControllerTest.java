package org.acme.controller;


import org.acme.repository.UsuarioRepository;
import org.acme.service.AuthService;
import org.acme.utils.GeraTokenJWT;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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