package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
        ) {
            String request = "SELECT * , users.id as id_user, room.id as id_room " +
                    "FROM messages " +
                    "	LEFT JOIN users ON messages.author = users.id " +
                    "	LEFT JOIN room ON messages.room_id = room.id " +
                    "WHERE messages.id= " + id;
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                Long user_id = resultSet.getLong("id_user");
                String login = resultSet.getString("login");
                String password = resultSet.getString("passwd");
                User author = new User(user_id, login, password);
                Long room_id = resultSet.getLong("id_room");
                String name = resultSet.getString("chat_name");
                Chatroom room = new Chatroom(room_id, name);
                String text = resultSet.getString("message");
                Object obj = resultSet.getObject("time");
                LocalDateTime dateTime;
                if (obj == null)
                    dateTime = null;
                else
                    dateTime = resultSet.getTimestamp("time").toLocalDateTime();
                return Optional.of(new Message(id, author, room, text, dateTime));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
