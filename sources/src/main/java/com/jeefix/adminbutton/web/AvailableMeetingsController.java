package com.jeefix.adminbutton.web;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.jeefix.adminbutton.model.Meeting;

@Component("availableMeetingsController")
public class AvailableMeetingsController {

    private List<Meeting> meetingList;
    
    @PostConstruct
    public void init() {
        meetingList = new LinkedList<Meeting>();
        for(int i =0;i<10;i++) {
            Meeting r = new Meeting();
            r.setName("meeting no "+i);
            r.setMeetingId(UUID.randomUUID().toString());
            meetingList.add(r);
        }
    }

    
    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    
    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }
    
}
