package br.com.fiap.read_easy_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.fiap.read_easy_api.service.AIAnalistyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/genres")

public class GenreController {

    // @Autowired
    // private GenreService genreService;

	@Autowired
	private AIAnalistyService aiService;
    
    private final Logger log = LoggerFactory.getLogger(getClass());
	// Injeção de Dependência
	@Autowired
	private GenreRepository genreRepository;

	@GetMapping
	@Cacheable("genres")
	@Operation(
		tags = "Genres",
		summary = "Listar gêneros",
		description = "Lista todos os gêneros cadastrados."
	)
	public List<Genre> index() {
		return genreRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(
		responses = @ApiResponse(responseCode = "400", description = "Validação Falhou")
	)
	@CacheEvict(value = "genres", allEntries = true)
	// @ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Genre> create(@RequestBody @Valid Genre genre) {
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
	@CacheEvict(value = "genres", allEntries = true)
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

	@PutMapping("/{id}/favorite")
	@CacheEvict(allEntries = true, value = "genres")
	public ResponseEntity<Map<String, Object>> toggleFavorite(@PathVariable Long id) {
		log.info("Alternando favorito");
		Genre genre = getGenre(id);
		genre.setFavorite(!genre.isFavorite());
		genreRepository.save(genre);

		Map<String, Object> response = new HashMap<>();
		response.put("id", genre.getId());
		response.put("favorite", genre.isFavorite());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/ai")
	public String askAI(){
		return aiService.recommendedBooks();
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
