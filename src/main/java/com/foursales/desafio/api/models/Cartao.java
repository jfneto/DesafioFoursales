package com.foursales.desafio.api.models;

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
    @Column(name = "mes_ano")
    private String mesAno;
    private String cvv;
    private String nome;

}
