package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.enums.OverlayEnum;
import me.ijedi.jediutil.enums.PlayerDirectionEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class DirectionOverlay extends AbstractOverlay {

    public DirectionOverlay(){
        setOverlayEnum(OverlayEnum.DIRECTION);
    }

    @Override
    public void updateDisplayMessage(EntityPlayer player) {
        // Get the direction the player is facing
        float headYaw = player.rotationYawHead;
        String directionOverlay = "D: " + PlayerDirectionEnum.getDirectionString(headYaw);
        setDisplayMessage(directionOverlay);
    }

    @Override
    public void updateDisplayMessage(Minecraft minecraft) {

    }

}
