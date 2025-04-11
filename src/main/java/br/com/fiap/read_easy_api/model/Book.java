package br.com.fiap.read_easy_api.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "não pode ser nulo")
    private String title;

    @PastOrPresent(message = "deve ser passado ou presente")
    private LocalDate date;

    private String description;

    @NotBlank(message = "o autor não pode ser nulo")
    private String author;

}
