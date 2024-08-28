package dev.v3ktor;

import dev.v3ktor.config.MemoryStorageConfig;
import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.User;
import dev.v3ktor.model.enums.UserHoles;
import dev.v3ktor.model.repository.AuthorRepository;
import dev.v3ktor.model.repository.impl.memory.AuthorMemoryRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        try {
            MemoryStorageConfig.setGlobalPath("C:\\Library-CLI");
            MemoryStorageConfig.init(
              List.of( new User(1, "admin", String.valueOf("admin".hashCode()), UserHoles.ADMIN) ),null, null, null
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        AuthorRepository authorRepository = new AuthorMemoryRepositoryImpl();

        var author1 = new Author(1, "Machado de Assis", LocalDate.parse("1820-05-12"));
        var author2 = new Author(2, "Bruno C.", LocalDate.parse("2006-02-28"));
        var book1 = new Book(1, "Dom Casmurro", true, LocalDate.parse("2024-08-28"), LocalDate.parse("2024-08-28"), author1);

        //authorRepository.save( author1 );
        //authorRepository.save(author2);

        //System.out.println( authorRepository.findByBook(book1) );
        //authorRepository.update( new Author(2, "Bruno Camargo F.", LocalDate.parse("2006-02-28")) );

        authorRepository.delete( new Author(3, "Bruno Camargo F.", LocalDate.parse("2006-02-28")) );

        System.out.println( authorRepository.findByName("Assis") );
        authorRepository.findAll().forEach(System.out::println);

    }
}