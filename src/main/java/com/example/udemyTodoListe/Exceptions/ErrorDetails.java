package com.example.udemyTodoListe.Exceptions;

import java.util.Date;

public class ErrorDetails {
    private Date date;
    private String message;
    private String detail;


    public ErrorDetails(Date date, String message, String detail) {
        super();
        this.date = date;
        this.message = message;
        this.detail = detail;
    }
}
