package com.foursales.desafio.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cartoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

    @GeneratedValue
    @Id
    private int id;
    private String numero;
    private String ano;
    private String mes;
    private String cvv;

}
