package br.com.fiap.read_easy_api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.read_easy_api.model.Book;
import br.com.fiap.read_easy_api.repository.BookRepository;
import br.com.fiap.read_easy_api.specification.BookSpecification;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    public record BookFilter (LocalDate startDate, LocalDate endDate, String title){}

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Page<Book> index(BookFilter filters, @PageableDefault(size = 10, sort = "date", direction = Direction.DESC) Pageable pageable){
        System.out.println("Filtros recebidos: " + filters);
        Specification<Book> specification = BookSpecification.withFilters(filters);    
        return bookRepository.findAll(specification, pageable);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }
}
