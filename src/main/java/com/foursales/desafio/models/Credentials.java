package com.foursales.desafio.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Credentials {
    private String email;
    private String password;
}
