package br.com.fiap.read_easy_api.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.read_easy_api.model.Book;
import br.com.fiap.read_easy_api.model.Genre;
import br.com.fiap.read_easy_api.repository.BookRepository;
import br.com.fiap.read_easy_api.repository.GenreRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {
    
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init(){
        var genres = 
            List.of(
                Genre.builder().name("Horror").icon("Ghost").favorite(false).build(),
                Genre.builder().name("Romance").icon("Heart").favorite(false).build(),
                Genre.builder().name("Sci-fi").icon("Atom").favorite(true).build()
            );
        genreRepository.saveAll(genres);

        var books = List.of(
            Book.builder().date(LocalDate.now()).title("Harry Potter").description("Muito daora").author("J.K Rowling").build(),
            Book.builder().date(LocalDate.now()).title("A volta dos que não foram").description("nem foram").author("Alguém que foi").build()
            );

        bookRepository.saveAll(books);
    }

}
