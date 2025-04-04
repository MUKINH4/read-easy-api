package br.com.fiap.read_easy_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.read_easy_api.model.Genre;
import br.com.fiap.read_easy_api.repository.GenreRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {
    
    @Autowired
    private GenreRepository genreRepository;

    @PostConstruct
    public void init(){
        genreRepository.saveAll(
            List.of(
                Genre.builder().name("Horror").icon("Ghost").build(),
                Genre.builder().name("Romance").icon("Heart").build(),
                Genre.builder().name("Sci-fi").icon("Atom").build()
            )
        );
    }

}
