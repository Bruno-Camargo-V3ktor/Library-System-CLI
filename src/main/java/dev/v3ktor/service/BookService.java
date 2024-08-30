package dev.v3ktor.service;

import dev.v3ktor.model.repository.BookRepository;

public class BookService {

    //ATRIBUTOS
    private final BookRepository bookRepository;

    //CONSTRUTORES
    public BookService( BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
    }

}
