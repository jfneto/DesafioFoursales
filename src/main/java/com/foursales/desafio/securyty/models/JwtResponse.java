package com.foursales.desafio.securyty.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -2415080745364466823L;

    @Getter
    private final String jwtToken;


}