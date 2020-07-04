package com.foursales.desafio.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "candidatos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {

    @GeneratedValue
    @Id
    private int id;

    private String nome;

    private String email;

    private String telefone;

    @OneToMany
    private List<Cartao> cartaoes;

}
