package me.ijedi.jediutil.ui;

import me.ijedi.jediutil.ref.ConfigReference;

public class UIManager {

    // Objects - these are default positions
    public static UIObject coordinateUIObject = new UIObject(5, 5);
    public static UIObject directionUIObject = new UIObject(5, 15);
    public static UIObject biomeUIObject = new UIObject(5, 25);
    public static UIObject clockUIObject = new UIObject(5, 35);

    public static void resetUIObjects()
    {
        int currentY = 5;
        if(ConfigReference.isShowCoordinates){
            coordinateUIObject.setYPos(currentY);
            currentY += 10;
        }

        if(ConfigReference.isShowDirection){
            directionUIObject.setYPos(currentY);
            currentY += 10;
        }

        if(ConfigReference.isShowBiome){
            biomeUIObject.setYPos(currentY);
            currentY += 10;
        }

        if(ConfigReference.isShowClock){
            clockUIObject.setYPos(currentY);
            currentY += 10;
        }
    }

}
