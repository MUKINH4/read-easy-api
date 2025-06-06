package br.com.fiap.read_easy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.read_easy_api.dto.UserResponse;
import br.com.fiap.read_easy_api.model.User;
import br.com.fiap.read_easy_api.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userSaved = userRepository.save(user);
        return new UserResponse(userSaved.getId(),userSaved.getEmail(), userSaved.getRole());
    }

}
