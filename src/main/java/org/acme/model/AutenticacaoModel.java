package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoModel {

    private String documento;
    private String senha;    
    
}
