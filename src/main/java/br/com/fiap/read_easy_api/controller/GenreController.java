package br.com.fiap.read_easy_api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.read_easy_api.model.Genre;
import br.com.fiap.read_easy_api.repository.GenreRepository;

@RestController
@RequestMapping("/genres")
@CrossOrigin(origins = "*")
public class GenreController {

    // @Autowired
    // private GenreService genreService;
    
    private final Logger log = LoggerFactory.getLogger(getClass());
	// Injeção de Dependência
	@Autowired
	private GenreRepository genreRepository;

	@GetMapping
	public List<Genre> index() {
		return genreRepository.findAll();
	}

	@PostMapping
	// @ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Genre> create(@RequestBody Genre genre) {
		log.info("Cadastrandênero " + genre.getName());
		genreRepository.save(genre);
		return ResponseEntity.status(201).body(genre);
	}

	@GetMapping("{id}")
	public Genre get(@PathVariable Long id) {
		log.info("buscando gênero " + id);
		return getGenre(id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void destroy(@PathVariable Long id) {
		log.info("Apagando gênero " + id);
		genreRepository.delete(getGenre(id));
	}

	@PutMapping("{id}")
	public Genre update(@PathVariable Long id, @RequestBody Genre genre) {
		log.info("Atualizando categoria " + id + " para " + genre);

		genre.setId(id);
		return genreRepository.save(genre);
	}

	private Genre getGenre(Long id) {
		return genreRepository
				.findById(id)
				.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}

    // @GetMapping
    // public List<Genre> index(){ // mocky
    //     return repository;
    // }

    // @PostMapping
    // public ResponseEntity<String> createGenre(@RequestBody Genre genre){
    //     try {
    //         genreService.addGenre(genre);
    //         return ResponseEntity.ok("Adicionado com sucesso!");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //     }
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Genre> findById(@PathVariable Long id){
    //     var genre = repository.stream()
    //     .filter(t -> t.getId().equals(id))
    //     .findFirst();

    //     if (genre.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     return ResponseEntity.ok().body(genre.get());
    // }
}
