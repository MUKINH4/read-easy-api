package br.com.fiap.read_easy_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.read_easy_api.model.Genre;
import br.com.fiap.read_easy_api.repository.GenreRepository;

@Service
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;

    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    

}
