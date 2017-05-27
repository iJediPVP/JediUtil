package me.ijedi.jediutil.enums;

public enum UIEnum {

    // Enums
    COORDS(5, 5),
    COORDS_DIRECTION(5, 15),
    CLOCK(5, 25);

    int x;
    int y;

    UIEnum(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
