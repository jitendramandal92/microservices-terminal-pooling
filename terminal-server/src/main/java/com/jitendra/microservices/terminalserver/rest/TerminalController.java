package com.jitendra.microservices.terminalserver.rest;

import com.jitendra.microservices.terminalserver.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TerminalController {

    @Autowired
    TerminalService terminalService;

    @GetMapping(value = "/terminal",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String,Object>> getTerminals()
    {
        Map<String, Object> response = null;
        try {
             response = terminalService.getTerminals();
        }
        catch (Exception e)
        {
          return   new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
       return new ResponseEntity(response,HttpStatus.OK);
    }

    @PutMapping(value = "/terminal/{terminalId}")
    public ResponseEntity updateTerminals(@PathVariable String terminalId)
    {
        terminalService.updateTerminals(terminalId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
