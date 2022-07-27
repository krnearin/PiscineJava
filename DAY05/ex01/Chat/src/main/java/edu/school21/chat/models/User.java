package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> creates;
    private List<Chatroom> membered;

    public User(Long id, String login, String password, List<Chatroom> creates, List<Chatroom> membered) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.creates = creates;
        this.membered = membered;
    }

    public User(Long user_id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", creates=" + creates +
                ", membered=" + membered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) && password.equals(user.password) && creates.equals(user.creates) && membered.equals(user.membered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, creates, membered);
    }
}
