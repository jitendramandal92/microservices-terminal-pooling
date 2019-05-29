package com.jitendra.microservices.terminalclient.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TerminalClientController {

    public static final String TERMINAL_SERVER_URL = "http://terminal-server/api/terminal";
    @Autowired
    public RestTemplate restTemplate;

    @GetMapping(value = "/validateTerminal",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map> validateTerminal()
    {
        ResponseEntity<Map> response = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);
             response = restTemplate.exchange(TERMINAL_SERVER_URL, HttpMethod.GET, entity, Map.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                Map<String, Object> map = (Map) response.getBody();
                Integer terminalId = (Integer) map.get("terminalId");
                Thread.sleep(10000);

                ResponseEntity response1 = restTemplate.exchange(TERMINAL_SERVER_URL+"/"+terminalId, HttpMethod.PUT, entity, ResponseEntity.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return response;
    }
    
}
