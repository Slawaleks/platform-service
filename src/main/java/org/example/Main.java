package org.example;

import org.example.exception.ValidationException;
import org.example.handler.MeetingHandler;
import org.example.handler.TaskHandler;
import org.example.handler.UserHandler;


import java.sql.SQLException;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserHandler userHandler = new UserHandler();
        TaskHandler taskHandler = new TaskHandler();
        MeetingHandler meetingHandler = new MeetingHandler();

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] words = input.split(" ");
                if ("USER".equals(words[0])) {
                    userHandler.handle(Arrays.copyOfRange(words, 1, words.length));
                } else if ("TASK".equals(words[0])) {
                    taskHandler.handle(Arrays.copyOfRange(words, 1, words.length));
                } else if ("MEETING".equals(words[0])) {
                    meetingHandler.handle(Arrays.copyOfRange(words, 1, words.length));
                } else {
                    System.out.println("You wrote the wrong command");
                }
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    System.out.println("Ошибка при работе бд");
                }
                if (e instanceof ValidationException) {
                    System.out.println("Ошибка валидации: " + ((ValidationException) e).getExceptionMessage());
                }

            }
        }
    }
}