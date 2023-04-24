package org.example.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

    public class Meeting {
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private long createdBy;
    private List<Long> participants;

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public void setTitle(String litle) {
        this.title = litle;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String bescription) {
        this.description = bescription;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Long> participants) {
        this.participants = participants;
    }
}

