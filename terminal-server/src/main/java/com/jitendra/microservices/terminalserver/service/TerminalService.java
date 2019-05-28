package com.jitendra.microservices.terminalserver.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TerminalService {

    public static final Map<String,Boolean> poolIds = new HashMap<>();

    @PostConstruct
    public void init()
    {
        poolIds.put("1001",false);
        poolIds.put("1002",false);
        poolIds.put("1003",false);
        poolIds.put("1004",false);
        poolIds.put("1005",false);
    }

    public Map<String,Object> getTerminals() throws Exception
    {

        String poolId= checkAvailablePools();
        if("-1".equals(poolId))
        {
            Thread.sleep(30000);
            poolId= checkAvailablePools();
            if("-1".equals(poolId))
            {
                throw new Exception();
            }

        }

        return constructPayload(poolId);
    }

    public synchronized Map<String,Object> constructPayload(String poolId)
    {
        Map<String,Object> payload = new HashMap<>();
        payload.put("terminalId",poolId);
        payload.put("sequenceNo",0);
        payload.put("timestamp", LocalDateTime.now());
        poolIds.put(poolId,true);
        return payload;
    }



    public String checkAvailablePools()
    {
        String poolId = "-1";
        for(Map.Entry<String,Boolean> entry : poolIds.entrySet())
        {
            if(entry.getValue()) {
                poolId = entry.getKey();


                break;
            }
        }
        return poolId;
    }

    public void updateTerminals(String poolId) {
        poolIds.put(poolId,true);
    }
}
