package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.lang.reflect.Field;

public class FrameRateOverlay extends AbstractOverlay {

    public FrameRateOverlay(){
        setOverlayEnum(OverlayEnum.FRAME_RATE);
    }

    @Override
    public void updateDisplayMessage(EntityPlayer player) {

    }

    @Override
    public void updateDisplayMessage(Minecraft minecraft) {

        // Use reflection to get the debugFPS field from the Minecraft class
        try{
            Class<Minecraft> minecraftClass = Minecraft.class;
            Field field = minecraftClass.getDeclaredField("debugFPS");
            field.setAccessible(true);
            int fps = (int)field.get(minecraft);
            String displayMessage = "FPS: " + Integer.toString(fps);
            setDisplayMessage(displayMessage);
        }catch(NoSuchFieldException | IllegalAccessException e){
            // TODO: log this
        }
    }

}
