package com.contactlist.app.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class HttpErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
