package br.com.fiap.read_easy_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.read_easy_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String username);
}
