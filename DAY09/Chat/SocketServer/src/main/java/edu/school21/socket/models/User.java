package edu.school21.socket.models;

public class User {
    Long id;
    String name;
    String pswd;

    public User(Long id, String name, String pswd) {
        this.id = id;
        this.name = name;
        this.pswd = pswd;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPswd() {
        return pswd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pswd='" + pswd + '\'' +
                '}';
    }
}
