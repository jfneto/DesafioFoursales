package com.foursales.desafio.api.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cpf;

    @OneToMany
    private List<Cartao> cartaoes;

}
