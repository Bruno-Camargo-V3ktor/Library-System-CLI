package dev.v3ktor.model.entity;

import dev.v3ktor.model.enums.LoanStatus;

import java.time.LocalDate;
import java.util.Objects;

public class BookLoan {

    //Atributos
    private Integer id;
    private Book book;
    private User user;
    private LocalDate date;
    private LoanStatus status;

    //Construtores
    public BookLoan() {}
    public BookLoan(Book book, User user, LocalDate date) {
        this.book = book;
        this.user = user;
        this.date = date;
    }

    //Getters & Setters
    public Integer getId() { return id; }

    public Book getBook() { return book; }

    public User getUser() { return user; }

    public LocalDate getDate() { return date; }

    //Métodos

    //Equals & Hashcode
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return Objects.equals(id, bookLoan.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(id);
    }

}
