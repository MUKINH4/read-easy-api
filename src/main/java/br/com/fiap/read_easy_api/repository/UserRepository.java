package br.com.fiap.read_easy_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.read_easy_api.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
