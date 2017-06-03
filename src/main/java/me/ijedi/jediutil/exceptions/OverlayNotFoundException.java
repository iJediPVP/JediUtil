package me.ijedi.jediutil.exceptions;

public class OverlayNotFoundException extends RuntimeException {

    public OverlayNotFoundException(String message) {
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
