package br.com.fiap.read_easy_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.read_easy_api.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
    
}
