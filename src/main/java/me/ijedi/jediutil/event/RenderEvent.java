package me.ijedi.jediutil.event;

import me.ijedi.jediutil.JediUtil;
import me.ijedi.jediutil.enums.PlayerDirectionEnum;
import me.ijedi.jediutil.enums.UIEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderEvent extends Gui {

    private Minecraft minecraft;
    private EntityPlayer player;

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event){
        if(event.getType() != RenderGameOverlayEvent.ElementType.CHAT){
            return;
        }

        // Render some things!
        if(!JediUtil.player.equals(null))
        {
            player = JediUtil.player;
            minecraft = Minecraft.getMinecraft();
            if(!minecraft.gameSettings.showDebugInfo){
                updatePlayerCoordinates();
                updatePlayerTime();
            }
        }
    }

    private void updatePlayerCoordinates(){
        // Get X, Y, Z
        int xPos = player.getPosition().getX();
        int yPos = player.getPosition().getY();
        int zPos = player.getPosition().getZ();
        String xString = "X: " + Integer.toString(xPos);
        String zString = "Z: " + Integer.toString(zPos);
        String yString = "Y: " + Integer.toString(yPos);

        // Figure out the direction the player is facing
        float headYaw = player.rotationYawHead;
        String dirString = PlayerDirectionEnum.getDirectionString(headYaw);

        // Display coordinates
        minecraft.fontRendererObj.drawStringWithShadow(xString, UIEnum.COORD_X.getX(), UIEnum.COORD_X.getY(), 0xFFFFFF);
        minecraft.fontRendererObj.drawStringWithShadow(zString, UIEnum.COORD_Z.getX(), UIEnum.COORD_Z.getY(), 0xFFFFFF);
        minecraft.fontRendererObj.drawStringWithShadow(yString, UIEnum.COORD_Y.getX(), UIEnum.COORD_Y.getY(), 0xFFFFFF);
        minecraft.fontRendererObj.drawStringWithShadow(dirString, UIEnum.COORD_DIRECTION.getX(), UIEnum.COORD_DIRECTION.getY(), 0xFFFFFF);
    }

    private void updatePlayerTime(){
        // Convert ticks to IRL time
        // Offset by 6000 so that the time aligns more with a IRL time
        long ticks = player.world.getWorldTime() + 6000;
        // Cut it down to 1 day of time
        ticks %= 24000;
        int hours = (int) Math.floor(ticks / 1000);
        int minutes = (int) ((ticks % 1000) / 1000.0 * 60);

        // AM/PM
        String amPM = "AM";
        if(hours > 11){
            amPM = "PM";
        }

        // To 12 hour time
        if(hours > 12){
            hours -= 12;
        }

        // Display
        String timeString = String.format("%2s:%2s", Integer.toString(hours), Integer.toString(minutes)).replace(' ', '0');
        timeString += " " + amPM;
        minecraft.fontRendererObj.drawStringWithShadow(timeString, UIEnum.CLOCK.getX(), UIEnum.CLOCK.getY(), 0xFFFFFF);
    }
}
