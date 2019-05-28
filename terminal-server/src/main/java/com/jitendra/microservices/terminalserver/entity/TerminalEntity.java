package com.jitendra.microservices.terminalserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "terminal")
public class TerminalEntity implements Serializable {

    @Id
    private String terminalId;

    private Integer sequenceNo;

    private LocalDateTime timeStamp;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
