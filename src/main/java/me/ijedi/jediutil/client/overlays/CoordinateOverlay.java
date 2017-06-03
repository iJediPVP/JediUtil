package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class CoordinateOverlay extends AbstractOverlay {

    public CoordinateOverlay(){
        setOverlayEnum(OverlayEnum.COORDINATES);
    }

    @Override
    public void updateDisplayMessage(EntityPlayer player){
        // Get X, Y, Z
        int xPos = (int)player.posX;
        int yPos = (int)player.posY;
        int zPos = (int)player.posZ;
        String displayMessage = String.format("XYZ: %s | %s | %s", Integer.toString(xPos), Integer.toString(yPos), Integer.toString(zPos));
        setDisplayMessage(displayMessage);
    }

    @Override
    public void updateDisplayMessage(Minecraft minecraft) {

    }

}
