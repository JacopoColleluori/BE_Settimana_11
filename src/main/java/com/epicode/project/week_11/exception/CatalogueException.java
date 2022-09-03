package com.epicode.project.week_11.exception;

public class CatalogueException extends RuntimeException{

    private static final long serialVersionUID = -7068045830079190922L;

    public CatalogueException(String message) {
        super(message);
    }
}
