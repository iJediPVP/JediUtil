package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClockOverlay extends AbstractOverlay {

    public ClockOverlay(){
        setOverlayEnum(OverlayEnum.CLOCK);
    }

    @Override
    public void updateDisplayMessage(EntityPlayer player) {
        // Convert ticks to IRL time
        // Offset by 6000 so that the time aligns more with a IRL time
        long ticks = player.world.getWorldTime() + 6000;
        // Cut it down to 1 day of time
        ticks %= 24000;
        int hours = (int) Math.floor(ticks / 1000);
        int minutes = (int) ((ticks % 1000) / 1000.0 * 60);

        String amPM = "";
        if(!getOverlayEnum().is24HourClock()){
            // Determine AM/PM
            if(hours > 11){
                amPM = "PM";
            }else{
                amPM = "AM";
            }

            // Convert to 12 hours
            if(hours > 12){
                hours -= 12;
            }
        }

        String displayMessage = String.format("%2s:%2s", Integer.toString(hours), Integer.toString(minutes)).replace(' ', '0');
        displayMessage += " " + amPM;
        setDisplayMessage(displayMessage);
    }

    @Override
    public void updateDisplayMessage(Minecraft minecraft) {

    }

}
