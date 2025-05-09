package br.com.fiap.read_easy_api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.read_easy_api.controller.AuthController.Token;
import br.com.fiap.read_easy_api.model.User;

@Service
public class TokenService {

    Instant expiresAt = LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.ofHours(-3));

    Algorithm algorithm = Algorithm.HMAC256("secret123");

    public Token createToken(User user){
        var jwt = JWT.create()
            .withSubject(user.getId())
            .withClaim("email", user.getEmail())
            .withExpiresAt(expiresAt)
            .sign(algorithm);

        return new Token(jwt, user.getEmail());
    }

    public User getUserFromToken(String token){
        var verifiedToken = JWT.require(algorithm).build().verify(token);

        return User.builder()
                .id(verifiedToken.getSubject())
                .email(verifiedToken.getClaim("email").toString())
                .build();
    }
}
