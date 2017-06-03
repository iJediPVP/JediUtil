package me.ijedi.jediutil.exceptions;

public class ColorEnumNotFoundException extends RuntimeException {
    public ColorEnumNotFoundException(String message) {
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
