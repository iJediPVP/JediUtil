package me.ijedi.jediutil.ui;

public class UIObject {

    public int xPos = 0;
    public int yPos = 0;

    public UIObject(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos){
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos){
        this.yPos = yPos;
    }
}

