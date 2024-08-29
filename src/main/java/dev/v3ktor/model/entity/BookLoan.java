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
    public BookLoan(Integer id, Book book, User user, LocalDate date, LoanStatus status) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.date = date;
        this.status = status;
    }

    //Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Book getBook() { return book; }

    public User getUser() { return user; }

    public LocalDate getDate() { return date; }

    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }

    //Métodos
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");

        sb.append(id).append(";");
        sb.append( book.getId() ).append(";");
        sb.append( user.getId() ).append(";");
        sb.append( date ).append(";");
        sb.append( status.name() ).append(";");

        return sb.toString();
    }

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
