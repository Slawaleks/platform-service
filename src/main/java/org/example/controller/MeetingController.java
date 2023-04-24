package org.example.controller;


import org.example.handler.MeetingHandler;
import org.example.model.Meeting;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/meeting")

public class MeetingController {
    private final MeetingHandler meetingHandler = new MeetingHandler();


    @PostMapping("/")
    public long createMeeting(@RequestBody Meeting meeting) throws SQLException {
        return meetingHandler.createMeeting(meeting);
    }

    @PutMapping("/{id}")
    public void updateMeeting(@RequestBody Meeting meeting, @PathVariable Long id) throws SQLException {
        meetingHandler.updateMeeting(meeting, id);
    }

    @GetMapping("/{id}")
    public Meeting getMeeting(@PathVariable Long id) throws SQLException {
        return meetingHandler.getMeeting(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMeeting(@PathVariable Long id) throws SQLException {
        meetingHandler.deleteMeeting(id);
    }
}
