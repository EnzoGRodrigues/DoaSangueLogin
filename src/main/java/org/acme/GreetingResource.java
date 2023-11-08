package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";

        
    }
    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += numeros[i] * (10 - i);
        }

        int resto = soma % 11;
        int digito1 = resto < 2 ? 0 : 11 - resto;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += numeros[i] * (11 - i);
        }

        resto = soma % 11;
        int digito2 = resto < 2 ? 0 : 11 - resto;

        return numeros[9] == digito1 && numeros[10] == digito2;
    }
}
