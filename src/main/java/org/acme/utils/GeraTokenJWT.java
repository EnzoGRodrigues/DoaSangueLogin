package org.acme.utils;

import java.util.Arrays;
import java.util.HashSet;

import io.smallrye.jwt.build.Jwt;

public class GeraTokenJWT {

    public String gerarTokenJWT(String documento, String tipoDocumento) {

        if (tipoDocumento.equals("CPF")) {
            return Jwt.issuer("http://localhost:8080")
                    .upn(documento)
                    .groups(new HashSet<>(Arrays.asList("USUARIO")))
                    .expiresAt(System.currentTimeMillis() + 3600)
                    .sign();
        } else if (tipoDocumento.equals("CNPJ")) {
            return Jwt.issuer("http://localhost:8080")
                    .upn(documento)
                    .groups(new HashSet<>(Arrays.asList("INSTITUICAO")))
                    .expiresAt(System.currentTimeMillis() + 3600)
                    .sign();
        }
        return null;
    }
}
