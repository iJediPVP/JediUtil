package me.ijedi.jediutil.event;

import me.ijedi.jediutil.JediUtil;
import me.ijedi.jediutil.enums.PlayerDirectionEnum;
import me.ijedi.jediutil.ref.ConfigReference;
import me.ijedi.jediutil.ui.UIManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;

public class RenderEvent extends Gui {

    private Minecraft minecraft;
    private EntityPlayer player;

    @SubscribeEvent
    public void onPostRenderGameOverlayEvent(RenderGameOverlayEvent.Post event){
        if(event.getType() != RenderGameOverlayEvent.ElementType.CHAT){
            return;
        }

        if(!ConfigReference.isGuiOn){
            return;
        }

        // Render some things!
        if(!JediUtil.player.equals(null))
        {
            player = JediUtil.player;
            minecraft = Minecraft.getMinecraft();
            if(!minecraft.gameSettings.showDebugInfo){

                if(ConfigReference.isShowCoordinates){
                    updatePlayerCoordinates();
                }

                if(ConfigReference.isShowDirection){
                    updatePlayerDirection();
                }

                if(ConfigReference.isShowBiome){
                    updatePlayerBiome();
                }

                if(ConfigReference.isShowClock){
                    updatePlayerTime();
                }

            }
        }
    }

    /*// Run this before we render anything
    private void preRender(){
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        //GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glScalef(0.975F, 0.975F,0.975F);
        GL11.glTranslated(0.975F, 0.975F, 0.975F);
    }

    // Run after we render
    private void postRender(){
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }*/

    // Update the coordinate information on the UI
    private void updatePlayerCoordinates(){
        // Get & display X, Y, Z
        int xPos = (int)player.posX;
        int yPos = (int)player.posY;
        int zPos = (int)player.posZ;
        String coordString = String.format("XYZ: %s | %s | %s", Integer.toString(xPos), Integer.toString(yPos), Integer.toString(zPos));

        int uiXPos = UIManager.coordinateUIObject.getXPos();
        int uiYPos = UIManager.coordinateUIObject.getYPos();
        minecraft.fontRendererObj.drawStringWithShadow(coordString, uiXPos, uiYPos, 0xFFFFFF);
    }

    // Update the player direction on the UI
    private void updatePlayerDirection(){
        // Get & display the direction the player is facing
        float headYaw = player.rotationYawHead;
        String dirString = PlayerDirectionEnum.getDirectionString(headYaw);

        int uiXPos = UIManager.directionUIObject.getXPos();
        int uiYPos = UIManager.directionUIObject.getYPos();
        minecraft.fontRendererObj.drawStringWithShadow(dirString, uiXPos, uiYPos, 0xFFFFFF);
    }

    // Update the world time on the UI
    private void updatePlayerTime(){
        // Convert ticks to IRL time
        // Offset by 6000 so that the time aligns more with a IRL time
        long ticks = player.world.getWorldTime() + 6000;
        // Cut it down to 1 day of time
        ticks %= 24000;
        int hours = (int) Math.floor(ticks / 1000);
        int minutes = (int) ((ticks % 1000) / 1000.0 * 60);


        String amPM = "";
        if(!ConfigReference.isClock24Hour){
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

        // Display
        String timeString = String.format("%2s:%2s", Integer.toString(hours), Integer.toString(minutes)).replace(' ', '0');
        timeString += " " + amPM;

        int uiXPos = UIManager.clockUIObject.getXPos();
        int uiYPos = UIManager.clockUIObject.getYPos();
        minecraft.fontRendererObj.drawStringWithShadow(timeString, uiXPos, uiYPos, 0xFFFFFF);
    }

    // Update the biome on the UI
    private void updatePlayerBiome(){
        World world = player.world;
        Biome biome = world.getBiomeForCoordsBody(player.getPosition());
        int uiXPos = UIManager.biomeUIObject.getXPos();
        int uiYPos = UIManager.biomeUIObject.getYPos();
        minecraft.fontRendererObj.drawStringWithShadow(biome.getBiomeName(), uiXPos, uiYPos, 0xFFFFFF);
    }

}
