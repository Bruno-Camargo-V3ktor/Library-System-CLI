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
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public List<UserHoles> getHoles() { return holes; }

    //Métodos
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");

        var holesStr = holes.stream().map( Enum::name ).toList().toString();

        sb.append(id).append(";");
        sb.append(name).append(";");
        sb.append(password).append(";");
        sb.append( holesStr.substring(1, holesStr.length()-1) ).append(";");

        return sb.toString();
    }

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
