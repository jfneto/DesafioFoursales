package com.foursales.desafio.api.repositories;

import com.foursales.desafio.api.models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
    Cartao findByNumero(String numero);
}
