package com.jeefix.adminbutton.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/** Represents meeting
 * 
 * @author mais */
@XmlRootElement(name = "response")
public class Meeting implements Serializable{
    
    private static final long serialVersionUID = -246746194295121691L;
    private String name;
    private String meetingId;
    private String attendeePw;
    private String moderatorPw;
    private String welcome;
    private int duration;
    private String meta;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMeetingId() {
        return meetingId;
    }
    
    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
    
    public String getAttendeePw() {
        return attendeePw;
    }
    
    public void setAttendeePw(String attendeePw) {
        this.attendeePw = attendeePw;
    }
    
    public String getModeratorPw() {
        return moderatorPw;
    }
    
    public void setModeratorPw(String moderatorPw) {
        this.moderatorPw = moderatorPw;
    }
    
    public String getWelcome() {
        return welcome;
    }
    
    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public String getMeta() {
        return meta;
    }
    
    public void setMeta(String meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Meeting [name=" + name + ", meetingId=" + meetingId + ", attendeePw=" + attendeePw + ", moderatorPw="
            + moderatorPw + ", welcome=" + welcome + ", duration=" + duration + ", meta=" + meta + "]";
    }
    
    
}
