package com.foursales.desafio.controllers;

import com.foursales.desafio.models.Candidato;
import com.foursales.desafio.repositories.CandidatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/candidatos")
@AllArgsConstructor
public class CandidatoController {

    private CandidatoRepository candidatoRepository;

    @PostMapping
    public ResponseEntity<Candidato> save(@RequestBody Candidato candidato){
        candidatoRepository.save(candidato);
        return new ResponseEntity<>(candidato, HttpStatus.OK);
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
            return new ResponseEntity<Optional<Candidato>>(HttpStatus.OK);
        }catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Candidato>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Candidato> update(@PathVariable Integer id, @RequestBody Candidato novoCandidato) {
        return candidatoRepository.findById(id)
                .map(candidato -> {
                    candidato.setNome(novoCandidato.getNome());
                    candidato.setEmail(novoCandidato.getEmail());
                    candidato.setTelefone(novoCandidato.getTelefone());
                    //candidato.setCartao(novoCandidato.getCartao());

                    Candidato candidatoUpdated = candidatoRepository.save(candidato);
                    return ResponseEntity.ok().body(candidatoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
