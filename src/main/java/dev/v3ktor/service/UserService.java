package dev.v3ktor.service;

import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.UserRepository;
import java.util.List;
import java.util.Objects;

public class UserService {

    //ATRIBUTOS
    private final UserRepository userRepository;

    //CONSTRUTORES
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // MÉTODOS
    public List<User> getAllUsers( User actualUser )
    {
        if( !actualUser.getHoles().contains( UserHoles.ADMIN ) ) return null;
        return userRepository.findAll();
    }

    public User login( String username, String password )
    {
        var user = userRepository.findByUsername( username );
        if( !user.getPassword().equals( String.valueOf( password.hashCode() ) ) ) return null;

        return user;
    }

    public void updateUser( User actualUser, User newUser )
    {
        if( Objects.equals(actualUser.getId(), newUser.getId()) ) return;
        userRepository.update( newUser );
    }

}
