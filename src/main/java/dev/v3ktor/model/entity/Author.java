package dev.v3ktor.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Author {

    //Atributos
    private Integer id;
    private String name;
    private LocalDate birthDate;

    //Construtores
    public Author() {}
    public Author(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    //Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    //Métodos
    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("");

        sb.append(id).append(";");;
        sb.append(name).append(";");
        sb.append(birthDate).append(";");;

        return sb.toString();
    }

    //Equals & Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
