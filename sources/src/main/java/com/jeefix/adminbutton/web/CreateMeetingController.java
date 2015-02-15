package com.jeefix.adminbutton.web;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeefix.adminbutton.model.Meeting;

@Controller("createMeetingController")
@Scope("session")
public class CreateMeetingController {
    
    private static final Logger log = LoggerFactory.getLogger(CreateMeetingController.class);
    private Meeting meeting;
    private Meeting createdMeeting;
    
    @PostConstruct
    public void init() {
        meeting = new Meeting();
        meeting.setDuration(120);
    }
    
    public String createMeeting() {
        log.debug("Attempting to create meeting: {}", getMeeting());
        meeting.setMeetingId(UUID.randomUUID().toString());
        createdMeeting = meeting;
        resetMeeting();
        return "CREATE-ROOM-SUCCESS";
    }
    
    public void resetMeeting() {
        log.debug("Attempting to reset meeting: {}", getMeeting());
        meeting = new Meeting();
        meeting.setDuration(120);
    }
    
    public Meeting getMeeting() {
        return meeting;
    }
    
    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
    
    public Meeting getCreatedMeeting() {
        return createdMeeting;
    }
    
    public void setCreatedMeeting(Meeting createdMeeting) {
        this.createdMeeting = createdMeeting;
    }
    
}
