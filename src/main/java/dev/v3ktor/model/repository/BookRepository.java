package dev.v3ktor.model.repository;

import dev.v3ktor.model.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository {

    // C -> Create
    void save(Book book);
    void saveAll(List<Book> books);

    // R -> Read
    Book findById(int id);
    Book findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findAll();


    // U -> Update
    void update(Book book);

    // D -> Delete
    void delete(Book book);

}
