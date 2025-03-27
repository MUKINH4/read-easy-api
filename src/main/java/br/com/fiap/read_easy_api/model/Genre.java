package br.com.fiap.read_easy_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String icon;
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getIcon() {
        return icon.substring(0, 1).toUpperCase() + icon.substring(1);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }
    
}