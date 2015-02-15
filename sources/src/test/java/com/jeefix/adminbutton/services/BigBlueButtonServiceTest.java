package com.jeefix.adminbutton.services;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.springframework.web.client.RestClientException;

import com.jeefix.adminbutton.model.Meeting;

public class BigBlueButtonServiceTest {
    
    @Test
    public void foo() throws UnsupportedEncodingException, RestClientException, URISyntaxException {
        
        Meeting meeting = new Meeting();
        meeting.setName("NameXXX");
        meeting.setMeetingId("meetingId");
        new BigBlueButtonService().createMeeting(meeting);
    }
    
    @Test
    public void boo() throws JAXBException {
        String response =
            "<response><returncode>SUCCESS</returncode><meetingID>meetingId</meetingID><attendeePW>7NS234vC</attendeePW><moderatorPW>mKGjtSHv</moderatorPW><createTime>1423826356760</createTime><hasBeenForciblyEnded>false</hasBeenForciblyEnded><messageKey></messageKey><message></message></response>";
        JAXBContext jaxbContext = JAXBContext.newInstance(Meeting.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        
        StringReader reader = new StringReader(response);
        Meeting person = (Meeting) unmarshaller.unmarshal(reader);
        System.out.println(person);
    }
}
