package br.com.fiap.read_easy_api.specification;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.data.jpa.domain.Specification;
 
import br.com.fiap.read_easy_api.controller.BookController.BookFilter;
import br.com.fiap.read_easy_api.model.Book;
import jakarta.persistence.criteria.Predicate;
 
public class BookSpecification {
    public static Specification<Book> withFilters(BookFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
 
            if (filter.title() != null && !filter.title().isBlank()) {
                predicates.add(cb.like(root.get("title"), "%" + filter.title().toLowerCase() + "%"));
            }
 
            if(filter.startDate() != null && filter.endDate() != null){
                predicates.add(
                    cb.between(root.get("date"), filter.startDate(), filter.endDate())
                );
            }
 
            var arraypreditates = predicates.toArray(new Predicate[0]);
            return cb.and(arraypreditates);
        };
    }
}