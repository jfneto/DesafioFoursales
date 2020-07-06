package com.foursales.desafio.api.v1.controllers;

import com.foursales.desafio.api.models.Candidato;
import com.foursales.desafio.api.models.Cartao;
import com.foursales.desafio.api.repositories.CandidatoRepository;
import com.foursales.desafio.api.repositories.CartaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/candidatos")
@AllArgsConstructor
@CrossOrigin
public class CandidatoController {

    private CandidatoRepository candidatoRepository;
    private CartaoRepository cartaoRepository;

    @PostMapping
    public ResponseEntity<Candidato> save(@Valid @RequestBody Candidato candidato){

        Candidato existente = candidatoRepository.findByCpf(candidato.getCpf());

        if(existente != null) {
            return new ResponseEntity<>(existente, HttpStatus.OK);
        } else {
            candidatoRepository.save(candidato);
            return new ResponseEntity<>(candidato, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<Candidato>> getAll(){
        List<Candidato> candidatos = new ArrayList<>();
        candidatos = candidatoRepository.findAll();
        return new ResponseEntity<>(candidatos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Candidato>> getById(@PathVariable Integer id){
        Optional<Candidato> candidato;
        try {
            candidato = candidatoRepository.findById(id);
            return new ResponseEntity<Optional<Candidato>>(candidato, HttpStatus.OK);
        }catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Candidato>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Candidato>> deleteById(@PathVariable Integer id) {
        try {
            candidatoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException | EmptyResultDataAccessException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Candidato> update(@PathVariable Integer id, @RequestBody Candidato novoCandidato) {
        return candidatoRepository.findById(id)
                .map(candidato -> {
                    candidato.setNome(novoCandidato.getNome());
                    candidato.setEmail(novoCandidato.getEmail());
                    candidato.setTelefone(novoCandidato.getTelefone());
                    candidato.setCpf(novoCandidato.getCpf());

                    Candidato candidatoUpdated = candidatoRepository.save(candidato);
                    return ResponseEntity.ok().body(candidatoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Cartões --> Optei por incluir a adição e remoçao dos cartões por essa classe para simplificar a estrutura, mesmo ferindo os principios SOLID.
    @PostMapping(path = "/add/cartao/{id}")
    public ResponseEntity<Cartao> addCartao(@PathVariable Integer id, @RequestBody Cartao cartao) {
        Cartao existente = cartaoRepository.findByNumero(cartao.getNumero());
        if(existente != null){
            return new ResponseEntity<>(existente, HttpStatus.OK);
        } else {
            Candidato candidato = candidatoRepository.findById(id).get();

            if(candidato == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                existente = cartaoRepository.save(cartao);
                candidato.getCartaoes().add(existente);
                candidatoRepository.save(candidato);
                return new ResponseEntity<>(cartao, HttpStatus.OK);
            }
        }
    }

    @DeleteMapping(path = "{id}/remove/cartao/{id_cartao}")
    public ResponseEntity<Optional<Cartao>> deleteCartaoById(@PathVariable Integer id, @PathVariable Integer id_cartao) {
        try {
            Candidato candidado = candidatoRepository.findById(id).get();
            Cartao cartao = cartaoRepository.findById(id_cartao).get();
            candidado.getCartaoes().remove(cartao);
            candidatoRepository.save(candidado);

            cartaoRepository.deleteById(id_cartao);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException | EmptyResultDataAccessException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
