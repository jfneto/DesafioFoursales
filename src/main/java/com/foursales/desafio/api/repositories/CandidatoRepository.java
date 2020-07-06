package com.foursales.desafio.api.repositories;

import com.foursales.desafio.api.models.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
    Candidato findByCpf(String cpf);
}