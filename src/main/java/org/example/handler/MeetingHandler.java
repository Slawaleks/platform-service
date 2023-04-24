package org.example.handler;

import org.example.db.MeetingDbclient;
import org.example.exception.ValidationException;
import org.example.model.Meeting;


import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingHandler {
    MeetingDbclient meetingDbClient = new MeetingDbclient();

    public void handle(String[] words) throws SQLException {

        if ("GET".equals(words[0])) {
            if (words.length != 2) {
                throw new ValidationException("Number of parameters must be equals 2");
            } else {
                Long id = Long.parseLong(words[1]);
                Meeting result = meetingDbClient.getMeetingById(id);
                System.out.println("Meeting: title = " + result.getTitle() + ", description = " + result.getDescription() + ", dateTime = " + result.getDateTime() + ", createdBy = " + result.getCreatedBy() + ", participants: " + result.getParticipants());
                return;
            }

        }
        if ("DEL".equals(words[0])) {
            if (words.length != 2) {
                throw new ValidationException("Number of parameters must be equals 2");
            } else {
                long id = Long.parseLong(words[1]);
                meetingDbClient.deleteMeeting(id);
                System.out.println("Meeting " + id + " deleted");
                return;
            }
        }
        if ("UPDATE".equals(words[0])) {
            if (words.length != 7) {
                throw new ValidationException("Number of parameters must be equals 7");
            } else {
                long id = Long.parseLong(words[1]);
                Meeting meeting = new Meeting();
                meeting.setTitle(words[2]);
                meeting.setDescription(words[3]);
                LocalDateTime dateTime = LocalDateTime.parse(words[4]);
                long createdBy = Long.parseLong(words[5]);
                String[] nums = words[6].split(",");
                List<Long> listParticipants = new ArrayList<>();
                for (int i = 0; i < nums.length; i++) {
                    long participantsId = Long.parseLong(nums[i]);
                    listParticipants.add(participantsId);
                    return;
                }
                meeting.setParticipants(listParticipants);
                meeting.setDateTime(dateTime);
                meeting.setCreatedBy(createdBy);
                meetingDbClient.updateMeeting(id, meeting);
            }
        }
        if ("ADD".equals(words[0])) {
            if (words.length != 6) {
                throw new ValidationException("Number of parameters must be equals 6");
            } else {
                Meeting newMeeting = new Meeting();
                newMeeting.setTitle(words[1]);
                newMeeting.setDescription(words[2]);
                LocalDateTime dateTime = LocalDateTime.parse(words[3]);
                newMeeting.setDateTime(dateTime);
                long createdBy = Long.parseLong(words[4]);
                newMeeting.setCreatedBy(createdBy);
                String[] nums = words[5].split(",");
                List<Long> listParticipants = new ArrayList<>();
                for (int i = 0; i < nums.length; i++) {
                    long participantsId = Long.parseLong(nums[i]);
                    listParticipants.add(participantsId);
                    return;
                }
                newMeeting.setParticipants(listParticipants);
                long result = meetingDbClient.addMeeting(newMeeting);
                System.out.println("id = " + result);
            }
        }
    }

    public Meeting getMeeting(Long id) throws SQLException {
        return meetingDbClient.getMeetingById(id);
    }

    public long createMeeting(Meeting meeting) throws SQLException {
        return meetingDbClient.addMeeting(meeting);
    }

    public void updateMeeting(Meeting meeting, long id) throws SQLException {
        meetingDbClient.updateMeeting(id, meeting);
    }

    public void deleteMeeting(long id) throws SQLException {
        meetingDbClient.deleteMeeting(id);
    }
}

