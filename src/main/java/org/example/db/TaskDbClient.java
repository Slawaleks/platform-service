package org.example.db;

import org.example.model.Task;
import org.example.model.User;

import java.sql.*;

public class TaskDbClient {
    public long addTask(Task newTask) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.task(name, description, deadline, user_id) values (?,?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newTask.getName());
            preparedStatement.setString(2, newTask.getDescription());
            preparedStatement.setDate(3, Date.valueOf(newTask.getDeadline()));
            preparedStatement.setLong(4, newTask.getUserId());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);
        }
    }

    public Task getTaskById(Long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, description, deadline, user_id FROM public.task where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            Task task = new Task(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3).toLocalDate(), resultSet.getLong(4));
            return task;
        }
    }

    public void deleteTask(Long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.task WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }

    }


    public void updateTask(Long id, Task task) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.task SET name = ?, description = ?, deadline= ?, user_id= ?  WHERE id = ?");
            preparedStatement.setLong(5, id);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Date.valueOf(task.getDeadline()));
            preparedStatement.setLong(4, task.getUserId());

            preparedStatement.execute();
        }
    }


}

