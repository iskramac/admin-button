package com.jeefix.adminbutton.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jeefix.adminbutton.model.Meeting;

@Service
public class BigBlueButtonService {
    
    private static final Logger log = LoggerFactory.getLogger(BigBlueButtonService.class);
    
    private String bbbApiUrl = "http://172.23.8.198/bigbluebutton/api/";
    private String secret = "2fba436d3c7274d5f30e8061df89af7f";
    
    private RestTemplate restTemplate = new RestTemplate();
    
    public String createMeeting(Meeting meeting) throws RestClientException, URISyntaxException {
        log.debug("Attempting to create meeting:{}", meeting);
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", meeting.getName());
        params.put("meetingID", meeting.getMeetingId());
        params.put("attendeePW", meeting.getAttendeePw());
        params.put("moderatorPW", meeting.getModeratorPw());
        params.put("meta", meeting.getMeta());
        params.put("duration", String.valueOf(meeting.getDuration()));
        
        URI actionUri = getActionUri("create", params);
        log.debug("Invoking BBB with action URI {}", actionUri);
        ResponseEntity<String> result = restTemplate.getForEntity(actionUri, String.class);
        log.info("Received BBB response:\n{}", result);
        return actionUri.toString();
    }
    
    protected URI getActionUri(String action, Map<String, String> params) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        for (Entry<String, String> entry : params.entrySet()) {
            uriBuilder.queryParam(entry.getKey(), entry.getValue());
        }
        String checksum = getChecksumForAction(action, uriBuilder.build().toString());
        
        uriBuilder.queryParam("checksum", checksum);
        uriBuilder.path(bbbApiUrl.concat(action));
        
        try {
            return new URI(uriBuilder.build().toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to create URI", e);
        }
    }
    
    protected String getChecksumForAction(String action, String query) {
        if (query.charAt(0) != '?') {
            throw new IllegalArgumentException("Query must start with '?' sign");
        }
        String source = query.replace("?", action).concat(secret);
        return DigestUtils.shaHex(source);
        
    }
}
