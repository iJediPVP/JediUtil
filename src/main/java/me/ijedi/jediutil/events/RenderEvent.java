package me.ijedi.jediutil.events;

import me.ijedi.jediutil.JediUtil;
import me.ijedi.jediutil.enums.OverlayEnum;
import me.ijedi.jediutil.client.overlays.AbstractOverlay;
import me.ijedi.jediutil.ref.ConfigReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.swing.*;

public class RenderEvent extends Gui {

    private Minecraft minecraft;
    private EntityPlayer player;

    @SubscribeEvent
    public void onPostRenderGameOverlayEvent(RenderGameOverlayEvent.Post event){
        if(event.getType() != RenderGameOverlayEvent.ElementType.CHAT){
            return;
        }

        if(!ConfigReference.allOverlaysEnabled){
            return;
        }

        // Render some things!
        try{
            if(!JediUtil.player.equals(null))
            {
                player = JediUtil.player;
                minecraft = Minecraft.getMinecraft();
                if(!minecraft.gameSettings.showDebugInfo){

                    // getOverlayList should only contain enabled overlays
                    JediUtil.overlayManager.setOverlayPositions();
                    for(AbstractOverlay overlay : JediUtil.overlayManager.getEnabledOverlayList(false)){
                        renderOverlay(overlay);
                    }
                }
            }
        }catch(NullPointerException e){
            JediUtil.player = Minecraft.getMinecraft().player;
        }
    }


    private void renderOverlay(AbstractOverlay overlay){

        if(overlay.getOverlayEnum().equals(OverlayEnum.FRAME_RATE)){
            overlay.updateDisplayMessage(minecraft);
        }else{
            overlay.updateDisplayMessage(player);
        }

        String message = overlay.getDisplayMessage();
        int xPosition = overlay.getPositionX();
        int yPosition = overlay.getPositionY();

        GlStateManager.pushMatrix();{
            GlStateManager.scale(.96F, .96F, .96F);
            minecraft.fontRendererObj.drawString(message, xPosition, yPosition, overlay.getOverlayEnum().getColorEnum().getColorCode());
        }
        GlStateManager.popMatrix();


    }
}
