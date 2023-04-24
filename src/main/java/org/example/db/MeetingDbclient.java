package org.example.db;

import org.example.model.Meeting;

import java.sql.*;
import java.util.List;

public class MeetingDbclient {
    public long addMeeting(Meeting newMeeting) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.meeting(title, description, date_time, created_by, partisipants) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newMeeting.getTitle());
            preparedStatement.setString(2, newMeeting.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(newMeeting.getDateTime()));
            preparedStatement.setLong(4, newMeeting.getCreatedBy());
            Array array = connection.createArrayOf("BIGINT", newMeeting.getParticipants().toArray());
            preparedStatement.setArray(5, array);
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);
        }
    }

    public Meeting getMeetingById(Long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT title, description, date_time, created_by, partisipants FROM public.meeting where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            Meeting meeting = new Meeting();
            meeting.setTitle(resultSet.getString(1));
            meeting.setDescription(resultSet.getString(2));
            meeting.setDateTime(resultSet.getTimestamp(3).toLocalDateTime());
            meeting.setCreatedBy(resultSet.getLong(4));
            Array participants = resultSet.getArray(5);
            Long[] participantsArr = (Long[]) participants.getArray();
            List<Long> participantsList = List.of(participantsArr);
            meeting.setParticipants(participantsList);
            return meeting;
        }
    }

    public void deleteMeeting(Long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.meeting WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }

    }


    public void updateMeeting(Long id, Meeting meeting) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1qaz")) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.meeting SET title = ?, description = ?, date_time = ?, created_by = ?, partisipants = ?  WHERE id = ?");
            preparedStatement.setLong(6, id);
            preparedStatement.setString(1, meeting.getTitle());
            preparedStatement.setString(2, meeting.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(meeting.getDateTime()));
            preparedStatement.setLong(4, meeting.getCreatedBy());
            Array array = connection.createArrayOf("BIGINT", meeting.getParticipants().toArray());
            preparedStatement.setArray(5, array);
            preparedStatement.execute();
        }
    }
}
