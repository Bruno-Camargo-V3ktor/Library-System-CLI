package dev.v3ktor.service;

import dev.v3ktor.exception.UserNotPermissionException;
import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.AuthorRepository;

import java.util.List;

public class AuthorService {

    //ATRIBUTOS
    private final AuthorRepository authorRepository;

    //CONSTRUTORES
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //MÉTODOS
    public List<Author> getAll()
    {
        return authorRepository.findAll();
    }

    public Author getAuthorByBook( Book book )
    {
        return authorRepository.findByBook( book );
    }

    public void updateAuthor( User actualUser, Author author )
    {
        if( !actualUser.getHoles().contains( UserHoles.ADMIN ) ) throw new UserNotPermissionException("Atualizar cadastro de um Autor");
        authorRepository.update(author);
    }

    public void createAuthor( User actualUser, Author author )
    {
        if( !actualUser.getHoles().contains( UserHoles.ADMIN ) ) throw new UserNotPermissionException("Criar cadastro de um Autor");;
        authorRepository.save(author);
    }

}
