package br.com.fiap.read_easy_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome é obrigatório")
    @Size(min = 3, max = 255, message = "deve ter pelo menos 3 letras")
    private String name;

    @NotBlank(message = "ícone é obrigatório")
    @Size(min = 3, max = 15)
    private String icon;
    
    public String getName(){
        return name.substring(0, 1).toUpperCase() + name.substring(1);
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