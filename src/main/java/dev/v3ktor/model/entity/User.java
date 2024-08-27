package dev.v3ktor.model.entity;

import dev.v3ktor.model.enums.UserHoles;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class User {

    //Atributos
    private Integer id;
    private String name;
    private String password;
    private List<UserHoles> holes;

    //Construtores
    public User() {}
    public User(Integer id, String name, String password, UserHoles... holes) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.holes = Arrays.stream(holes).toList();
    }

    //Getters & Setters
    public Integer getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public List<UserHoles> getHoles() { return holes; }

    //Métodos


    //Equals & Hashcode
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }


}
