package org.example.repository;

import org.example.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserRepository {
    private static final String INSERT_USER_SQL = """
                INSERT INTO users(first_name, last_name, username, password)
                VALUES (?, ?, ?, ?);
            """;

    private static final String FIND_USER_BY_ID_SQL = """
                SELECT 
                    *
                FROM users
                WHERE id = ?;
            """;

    public User insert(User user){
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, user.getFirstname());
            preparedStatement.setObject(2, user.getLastname());
            preparedStatement.setObject(3, user.getUsername());
            preparedStatement.setObject(4, user.getPassword());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if(generatedKeys.next()){
                user.setId(generatedKeys.getInt("id"));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(Integer userId) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL)) {

            preparedStatement.setObject(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if(resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("passport_id")
                );
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
