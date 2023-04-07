package com.C10G14.WorldFitBackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageResponse {
    private String message;

    private Integer Status;
    private Date timeStamp;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(Integer status, String message) {
        Status = status;
        this.message = message;
        this.timeStamp = new Date();
    }
}
