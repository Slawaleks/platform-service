package org.example.model;

import java.time.LocalDate;

public class Task {
    private String name;

    private String description;

    private LocalDate deadline;
    private Long userId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Long getUserId() {
        return userId;
    }

    public Task(String name, String description, LocalDate deadline, Long userId) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.userId = userId;
    }


}
