package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractOverlay {

    // Fields
    private int positionX = 5;
    private int positionY = 5;
    private String displayMessage = "";
    private int overlayRank = 0;
    private OverlayEnum overlayEnum;

    private final int MENU_BUTTON_WIDTH = 75;
    private final int MENU_BUTTON_HEIGHT = 20;
    private GuiButton menuButton;


    // Getters and setters
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int yPosition){
        this.positionY = yPosition;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public int getOverlayRank() {
        return overlayRank;
    }

    public void setOverlayRank(int overlayRank){
        this.overlayRank = overlayRank;
    }

    public OverlayEnum getOverlayEnum(){
        return overlayEnum;
    }

    public void setOverlayEnum(OverlayEnum overlayEnum){
        this.overlayEnum = overlayEnum;
        createGuiButton(this.overlayEnum.getName());
    }

    public GuiButton getMenuButton() {
        return menuButton;
    }

    public void createGuiButton(String buttonText) {
        this.menuButton = new GuiButton(0, 5,5, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, buttonText);
    }

    // Abstract methods
    public abstract void updateDisplayMessage(EntityPlayer player);
    public abstract void updateDisplayMessage(Minecraft minecraft);

}
