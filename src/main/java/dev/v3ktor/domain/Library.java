package dev.v3ktor.domain;

import dev.v3ktor.model.entity.Author;
import dev.v3ktor.model.entity.Book;
import dev.v3ktor.model.entity.BookLoan;

import java.util.List;
import java.util.ArrayList;

public class Library {

    //Atributos
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<BookLoan> loans = new ArrayList<>();

    //Construtores
    public Library() {}
    public Library(List<Book> books, List<Author> authors, List<BookLoan> loans) {
        this.books = books;
        this.authors = authors;
        this.loans = loans;
    }

    //Getters & Setters
    public List<Book> getBooks() { return books; }

    public List<Author> getAuthors() { return authors; }

    public List<BookLoan> getLoans() { return loans; }


    //Métodos


}
