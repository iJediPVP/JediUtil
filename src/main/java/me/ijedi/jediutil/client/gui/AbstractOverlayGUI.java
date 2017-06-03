package me.ijedi.jediutil.client.gui;

import me.ijedi.jediutil.client.overlays.OverlayManager;
import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;

public abstract class AbstractOverlayGUI extends GuiScreen {

    protected OverlayEnum overlayEnum;
    protected Minecraft minecraft;

    protected GuiButton exitButton;

    protected int screenWidth;
    protected int screenHeight;
    protected int centerX;
    protected int centerY;
    protected String menuText = "";
    protected int menuTextColor = 0xFFFFFF;

    protected abstract void initButtons();
    protected abstract void buttonAction(GuiButton guiButton);
    protected abstract void exitButtonAction(GuiButton guiButton);

    // Overridden methods from GuiScreen
    @Override
    public void initGui(){
        minecraft = Minecraft.getMinecraft();
        screenWidth = minecraft.displayWidth;
        screenHeight = minecraft.displayHeight;
        centerX = screenWidth / 4;
        centerY = screenHeight / 4;

        buttonList.clear();

        initButtons();
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        drawDefaultBackground();
        if(!menuText.equals(null) && menuText.length() > 0){
            drawMenuText();
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen(){
        super.updateScreen();
    }

    @Override
    public void actionPerformed(GuiButton button) throws IOException {
        if(!exitButton.equals(null) && button.id == exitButton.id){
            exitButtonAction(button);
        }else{
            buttonAction(button);
        }
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    public String getColoredButtonText(String text, TextFormatting color){
        TextComponentString componentString = new TextComponentString(text);
        componentString.setStyle(new Style().setColor(color));
        return componentString.getFormattedText();
    }

    private void drawMenuText(){
        GlStateManager.pushMatrix();{
            int menuY = OverlayManager.firstYPosition + 25;
            GlStateManager.translate(centerX - (fontRendererObj.getStringWidth(menuText) / 2), menuY, 0);
            drawString(fontRendererObj, menuText, 0, 0, menuTextColor);
        }
        GlStateManager.popMatrix();
    }

}
