package dev.v3ktor;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.domain.Library;
import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.impl.memory.AuthorMemoryRepositoryImpl;
import dev.v3ktor.model.repository.impl.memory.BookLoanMemoryRepositoryImpl;
import dev.v3ktor.model.repository.impl.memory.BookMemoryRepositoryImpl;
import dev.v3ktor.model.repository.impl.memory.UserMemoryRepositoryImpl;
import dev.v3ktor.util.Props;
import dev.v3ktor.view.ViewManager;
import dev.v3ktor.view.screens.LoginScreen;

import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        // Pré-Configurantion
        try {
            MemoryStorageConfig.setGlobalPath("C:\\Library-CLI");
            MemoryStorageConfig.init(
              List.of( new User(1, "admin", String.valueOf("admin".hashCode()), UserHoles.ADMIN) ),
              null,
              List.of( new Author( 0, "Desconhecido", LocalDate.MIN ) ),
              null
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Dependencias
        var userRepository = new UserMemoryRepositoryImpl();
        var authorRepository = new AuthorMemoryRepositoryImpl();
        var bookRepository = new BookMemoryRepositoryImpl(authorRepository);
        var loanRepository = new BookLoanMemoryRepositoryImpl(bookRepository, userRepository);
        var libary = new Library( bookRepository, userRepository, authorRepository, loanRepository );


        // Views -> Init
        var viewManager = new ViewManager();

        //viewManager.register( "login", LoginScreen.class );

        //viewManager.to( "login", new Props( new String[]{"libary"}, new Object[]{libary} ) );
    }
}