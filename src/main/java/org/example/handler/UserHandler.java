package org.example.handler;

import org.example.exception.ValidationException;
import org.example.validator.Validator;
import org.example.db.UserDbClient;
import org.example.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

public class UserHandler {

    UserDbClient userDbClient = new UserDbClient();

    public void handle(String[] words) throws SQLException, ValidationException {
        if ("GET".equals(words[0])) {
            if (words.length != 2) {
                throw new ValidationException("Number of parameters must be equals 2");
            } else {
                Long id = Long.parseLong(words[1]);
                User result = userDbClient.getUserById(id);
                System.out.println("User: username = " + result.getUserName() + ", email = " + result.getEmail());
                return;
            }
        }
        if ("DEL".equals(words[0])) {
            if (words.length != 2) {
                throw new ValidationException("Number of parameters must be equals 2");
            } else {
                long id = Long.parseLong(words[1]);
                userDbClient.deleteUser(id);
                System.out.println("User " + id + " deleted");
                return;
            }
        }
        if ("UPDATE".equals(words[0])) {
            if (words.length != 4) {
                throw new ValidationException("Number of parameters must be equals 4");
            } else {
                boolean validationEmailResult = Validator.validateEmail(words[3]);
                if (validationEmailResult == false) {
                    throw new ValidationException("You wrote the wrong Email");
                }
                long id = Long.parseLong(words[1]);
                User user = new User();
                user.setUserName(words[2]);
                user.setEmail(words[3]);
                userDbClient.updateUser(id, user);
                return;
            }
        }
        if ("ADD".equals(words[0])) {
            if (words.length != 3) {
                throw new ValidationException("Number of parameters must be equals 3");
            } else {
                boolean validationEmailResult = Validator.validateEmail(words[2]);
                if (validationEmailResult == false) {
                    System.out.println("You wrote the wrong Email");
                } else {
                    User newUser = new User();
                    newUser.setUserName(words[1]);
                    newUser.setEmail(words[2]);
                    long result = userDbClient.addUser(newUser);
                    System.out.println("id = " + result);

                }
            }
        }

    }


    public User getUser(Long id) throws SQLException {
        return userDbClient.getUserById(id);
    }

    public long createUser(User user) throws SQLException {
        return userDbClient.addUser(user);
    }

    public void updateUser(User user, long id) throws SQLException {
        userDbClient.updateUser(id, user);
    }

    public void deleteUser(long id) throws SQLException {
        userDbClient.deleteUser(id);
    }
}
