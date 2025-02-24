package br.com.fiap.read_easy_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.read_easy_api.model.Genre;

@RestController
public class GenreController {
    
    @RequestMapping(path = "/genre", produces = "application/json", method = {RequestMethod.GET})
    public Genre index(){
        return new Genre(1L, "horror", "knife");
    }

    
}
