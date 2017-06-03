package me.ijedi.jediutil.client.gui;

import me.ijedi.jediutil.JediUtil;
import me.ijedi.jediutil.exceptions.OverlayNotFoundException;
import me.ijedi.jediutil.client.overlays.AbstractOverlay;
import net.minecraft.client.gui.GuiButton;
import java.util.List;

public class MainOverlayGUI extends AbstractOverlayGUI{

    public MainOverlayGUI(){
        this.menuText = "JediUtil Menu";
    }

    @Override
    protected void initButtons(){

        int xSpaceInterval = 5;
        int ySpaceInterval = 3;
        int currentX;
        int currentY = centerY - (centerY / 2);

        // Set the overlays option buttons
        List<AbstractOverlay> overlayList = JediUtil.overlayManager.getOverlayList();
        for(int x = 0; x < overlayList.size(); x++){
            AbstractOverlay overlay = overlayList.get(x);
            GuiButton guiButton = overlay.getMenuButton();
            guiButton.id = x;

            // 0,2,4 etc go to the left
            if(x % 2 == 0){
                currentY += (guiButton.height + ySpaceInterval);
                currentX = centerX - guiButton.width - xSpaceInterval;

            }else{ // 1,3,5 etc go right
                currentX = centerX + xSpaceInterval;
            }

            guiButton.xPosition = currentX;
            guiButton.yPosition = currentY;
            buttonList.add(guiButton);
        }

        // Set exit buttons
        int exitButtonId = JediUtil.overlayManager.getOverlayList().size(); // Always 1 after last overlays
        exitButton = new GuiButton(exitButtonId, 0, 0, 100, 20, "Exit");
        currentX = centerX - (exitButton.width / 2);
        currentY += (exitButton.height + 20); // move exitButton down a bit below the other buttons
        exitButton.xPosition = currentX;
        exitButton.yPosition = currentY;
        buttonList.add(exitButton);
    }

    @Override
    protected void buttonAction(GuiButton button){
        int buttonId = button.id;
        if(buttonId == exitButton.id)
        {
            minecraft.displayGuiScreen(null);
        }else{
            // Try overlays options
            try{
                AbstractOverlay overlay = JediUtil.overlayManager.getOverlayFromMenuButtonId(buttonId);
                minecraft.displayGuiScreen(new OverlayConfigGUI(overlay.getOverlayEnum()));

            }catch(OverlayNotFoundException e){
                // TODO: logging
                JediUtil.Print(e.getMessage());
            }
        }
    }

    @Override
    protected void exitButtonAction(GuiButton guiButton) {
        //ConfigReference.saveConfiguration();
        minecraft.displayGuiScreen(null);
    }

}
