package br.com.fiap.read_easy_api.model;

import lombok.*;

@Getter
@Setter
public class Genre {

    private Long id;
    private String name;
    private String icon;

    public Genre(Long id, String name, String icon){
        this.id = id;
        this.name = name;
        this.icon = icon;
    }


}
