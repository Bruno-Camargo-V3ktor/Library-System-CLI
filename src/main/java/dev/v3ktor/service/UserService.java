package dev.v3ktor.service;

import dev.v3ktor.model.repository.UserRepository;

public class UserService {

    //ATRIBUTOS
    private final UserRepository userRepository;

    //CONSTRUTORES
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
