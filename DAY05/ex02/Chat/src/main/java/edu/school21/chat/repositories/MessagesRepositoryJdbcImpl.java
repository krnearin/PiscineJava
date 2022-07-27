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

    private final String SQL_SAVE_MESSAGE = "insert into messages (room_id, author, message, time) " +
            "values (?, ?, ?, ?);";
    private final String SQL_LAST_MESSAGE_ID = "SELECT ID FROM messages ORDER BY ID DESC LIMIT 1";

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

    public void save(Message message) {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_MESSAGE, Statement.RETURN_GENERATED_KEYS);
        ) {

            preparedStatement.setLong(1, message.getChatroom().getId());
            preparedStatement.setLong(2, message.getChatroom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDate()));
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Long newLast_id = resultSet.getLong(1);
                        message.setId(newLast_id);
                        System.out.println("Сообщение добавлено.");
                        return;
                    }
                }
            } catch (SQLException | NullPointerException e) {
                throw new NotSavedSubEntityException();
                }
        System.out.println("Message Error");
    }
}
