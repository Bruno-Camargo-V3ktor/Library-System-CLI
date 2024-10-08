package dev.v3ktor.service;

import dev.v3ktor.exception.InvalidCredentialsException;
import dev.v3ktor.exception.UserNotPermissionException;
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

    public User getByUsername( String username )
    {
        return userRepository.findByUsername( username );
    }

    public User login( String username, String password )
    {
        var user = userRepository.findByUsername( username );
        if( user == null ) throw new InvalidCredentialsException("Credenciais Inválidas");

        if( !user.getPassword().equals( String.valueOf( password.hashCode() ) ) ) throw new InvalidCredentialsException("Credencias Invalidas");

        return user;
    }

    public void updateUser( User actualUser, User newUser )
    {
        if( userRepository.findByUsername( newUser.getName() ).getId() != newUser.getId() )
            throw new InvalidCredentialsException("Usuario com esse nome ja existente");

        if( newUser.getPassword().length() < 6 )
            throw new InvalidCredentialsException("Sua senha deve possuir mais de 6 caracteres");

        if( !actualUser.getHoles().contains( UserHoles.ADMIN ) && !Objects.equals(actualUser.getId(), newUser.getId()) )
            throw new UserNotPermissionException("Atualizar cadastro desse usuario");

        userRepository.update( newUser );
    }

    public void createUser( User actualUser, User newUser )
    {
        if( userRepository.findByUsername( newUser.getName() ) != null ) throw new InvalidCredentialsException("Usuario com esse nome ja existente");
        if( newUser.getPassword().length() < 6 ) throw new InvalidCredentialsException("Sua senha deve possuir mais de 6 caracteres");

        if( actualUser == null )
        {
            newUser.setHoles( List.of( UserHoles.CLIENT ) );
            userRepository.save( newUser );
            return;
        }

        if( newUser.getHoles().contains( UserHoles.ADMIN ) )
        {
            if( actualUser.getHoles().contains( UserHoles.ADMIN ) )
            {
                userRepository.save( newUser );
                return;
            }

            throw new UserNotPermissionException("Criar um usuario ADMIN");
        }

        userRepository.save( newUser );
    }

}