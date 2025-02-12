package com.jgc.personas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgc.personas.modelo.Persona;
import com.jgc.personas.modelo.PersonaRepository;

import jakarta.persistence.Entity;


@Entity
@EntityScan(basePackages = {"persistence"})
@RestController
@RequestMapping("/persona")
public class PersonaController {
  @Autowired
  private PersonaRepository repository;

  @GetMapping("/find") // GET Method for reading operation
  public List<Persona> getAllPerson() {
    return repository.findAll();
  }

  @GetMapping("/find/{id}") // GET Method for Read operation
  public ResponseEntity<Persona> getPersonaById(@PathVariable(value = "id") Long personaId) throws ClassNotFoundException {
    Persona persona = repository.findById(personaId).orElseThrow(() -> new ClassNotFoundException());
    return ResponseEntity.ok().body(persona);
  }
}
