package br.com.fiap.read_easy_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.read_easy_api.model.Genre;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private List<Genre> repository = new ArrayList<>();
    
    @GetMapping
    public List<Genre> index(){ // mocky
        return repository;
    }

    @PostMapping
    public ResponseEntity<Genre> create(@RequestBody Genre genre){
        repository.add(genre);
        return ResponseEntity.status(201).body(genre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findById(@PathVariable Long id){
        var genre = repository.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst();

        if (genre.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(genre.get());
    }
}
