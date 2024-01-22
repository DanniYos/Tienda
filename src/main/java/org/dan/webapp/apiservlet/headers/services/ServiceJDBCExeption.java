package org.dan.webapp.apiservlet.headers.services;

public class ServiceJDBCExeption extends RuntimeException{
    public ServiceJDBCExeption(String message) {
        super(message);
    }

    public ServiceJDBCExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
