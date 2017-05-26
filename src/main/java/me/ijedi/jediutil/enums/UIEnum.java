package me.ijedi.jediutil.enums;

public enum UIEnum {

    // Enums
    COORD_X(5, 5),
    COORD_Z(5, 15),
    COORD_Y(5, 25),
    COORD_DIRECTION(5, 35),
    CLOCK(5, 45);

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
