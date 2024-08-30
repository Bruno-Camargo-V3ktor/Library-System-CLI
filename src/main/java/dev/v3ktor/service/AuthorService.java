package dev.v3ktor.service;

import dev.v3ktor.model.repository.AuthorRepository;

public class AuthorService {

    //ATRIBUTOS
    private final AuthorRepository authorRepository;

    //CONSTRUTORES
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


}
