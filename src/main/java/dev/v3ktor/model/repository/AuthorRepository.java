package dev.v3ktor.model.repository;

import dev.v3ktor.model.entity.Author;

import java.awt.print.Book;
import java.util.List;

public interface AuthorRepository {

    // C -> Create
    void save(Author author);

    // R -> Read
    Author findById(Integer id);
    Author findByBook(Book book);
    List<Author> findAll();
    List<Author> findByName(String name);

    // U -> Update
    Author update(Author author);

    // D -> Delete
    Author delete(Author author);

}
