package org.example.db;

import org.example.model.User;

import java.sql.*;
import java.util.regex.Pattern;

public class UserDbClient {

    public long addUser(User newUser) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.user(username, email) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newUser.getUserName());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);
        }
    }

    public User getUserById(Long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, email FROM public.user where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            User user = new User();
            user.setUserName(resultSet.getString(1));
            user.setEmail(resultSet.getString(2));
            return user;
        }
    }

    public void deleteUser(Long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.user WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }

    }


    public void updateUser(Long id, User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.user SET username = ?, email = ? WHERE id = ?");
            preparedStatement.setLong(3, id);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.execute();
        }
    }
//    public static <EmailValidator> boolean emailValidator(String email) {
//
//        EmailValidator validator = EmailValidator.getInstance();
//
//        if (!validator.isValid(email); {
//            return false;
//        }
//        return true;
//}
//    public static boolean patternMatches(String emailAddress, String regexPattern) {
//        return Pattern.compile(regexPattern)
//                .matcher(emailAddress)
//                .matches();
//    }
}