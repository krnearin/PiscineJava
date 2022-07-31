package edu.school21.socket.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.socket.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    HikariDataSource dataSource;
//    private final String TABLE_USER = "user";
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_ALL =
            "SELECT * FROM users";
    private final String SQL_FIND_BY_ID =
            "SELECT * FROM user WHERE id = ?";
    private final String SQL_UPDATE =
            "UPDATE users SET email = ? WHERE id = ?";

    private final String SQL_SAVE =
            "INSERT INTO users (emails) VALUES (?)";

    private final String SQL_DELETE =
            "DELETE FROM users WHERE id = ?";

    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("password"));
    };

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query(SQL_FIND_BY_ID, ROW_MAPPER, id).get(0);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER);
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_SAVE, entity.getName(), );
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
