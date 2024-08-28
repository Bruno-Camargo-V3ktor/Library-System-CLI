package dev.v3ktor.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    //Atributos
    private Integer id;
    private String title;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private boolean isAvailable;
    private Author author;

    //Construtores
    public Book() {}
    public Book(Integer id, String title, boolean isAvailable, LocalDate creationDate, LocalDate updateDate, Author author) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.isAvailable = isAvailable;
        this.author = author;
    }

    //Getters & Setters
    public Integer getId() { return id; }
    /* public void setId(Integer id) { this.id = id; } */

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDate getCreationDate() { return creationDate; }
    /* public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; } */

    public LocalDate getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDate updateDate) { this.updateDate = updateDate; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public Author getAuthor() { return author; }
    /* public void setAuthor(Author author) { this.author = author; } */


    //Métodos
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");

        sb.append("id=").append(id).append(";");
        sb.append("title=").append(title).append(";");
        sb.append("creationDate=").append(creationDate).append(";");
        sb.append("updateDate=").append(updateDate).append(";");
        sb.append("isAvailable=").append(isAvailable).append(";");
        sb.append("author=").append(author.getId()).append(";");
        sb.append("\n");

        return sb.toString();
    }

    //Equals & Hashcode
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, title, author);
    }

}