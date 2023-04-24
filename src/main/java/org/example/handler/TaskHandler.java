package org.example.handler;


import org.example.db.TaskDbClient;

import org.example.exception.ValidationException;
import org.example.model.Task;


import java.sql.SQLException;
import java.time.LocalDate;

public class TaskHandler {
    TaskDbClient taskDbClient = new TaskDbClient();

    public void handle(String[] words) throws SQLException {

        if ("GET".equals(words[0])) {
            if (words.length != 2) {
                throw new ValidationException("Number of parameters must be equals 2");
            } else {
                Long id = Long.parseLong(words[1]);
                Task result = taskDbClient.getTaskById(id);
                System.out.println("Task: name = " + result.getName() + ", description = " + result.getDescription() + ", deadline = " + result.getDeadline() + ", userId = " + result.getUserId());
                return;
            }
        }
        if ("DEL".equals(words[0])) {
            if (words.length != 2) {
                throw new ValidationException("Number of parameters must be equals 2");
            } else {
                long id = Long.parseLong(words[1]);
                taskDbClient.deleteTask(id);
                System.out.println("Task " + id + " deleted");
                return;
            }
        }
        if ("UPDATE".equals(words[0])) {
            if (words.length != 6) {
                throw new ValidationException("Number of parameters must be equals 6");
            } else {
                long id = Long.parseLong(words[1]);
                Task task = new Task(words[2], words[3], LocalDate.parse(words[4]), Long.parseLong(words[5]));
                taskDbClient.updateTask(id, task);
                return;
            }
        }
        if ("ADD".equals(words[0])) {
            if (words.length != 5) {
                throw new ValidationException("Number of parameters must be equals 5");
            } else {
                Task newTask = new Task(words[1], words[2], LocalDate.parse(words[3]), Long.parseLong(words[4]));
                long result = taskDbClient.addTask(newTask);
                System.out.println("id = " + result);

            }
        }
    }
    public Task getTask(Long id) throws SQLException {
        return taskDbClient.getTaskById(id);
    }

    public long createTask(Task task) throws SQLException {
        return taskDbClient.addTask(task);
    }

    public void updateTask(Task task, long id) throws SQLException {
        taskDbClient.updateTask(id, task);
    }

    public void deleteTask(long id) throws SQLException {
        taskDbClient.deleteTask(id);
    }
}
