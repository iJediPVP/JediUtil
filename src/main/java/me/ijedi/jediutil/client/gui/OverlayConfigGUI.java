package me.ijedi.jediutil.client.gui;


import me.ijedi.jediutil.enums.ColorEnum;
import me.ijedi.jediutil.enums.OverlayEnum;
import me.ijedi.jediutil.exceptions.ColorEnumNotFoundException;
import me.ijedi.jediutil.ref.ConfigReference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.text.TextFormatting;

public class OverlayConfigGUI extends AbstractOverlayGUI{

    private GuiButton enableButton;
    private GuiButton colorButton;
    private GuiButton clock24HourButton;

    private final int BUTTON_HEIGHT = 20;
    private final int BUTTON_WIDTH = 75;

    public OverlayConfigGUI(OverlayEnum overlayEnum){
        this.overlayEnum = overlayEnum;
        this.menuText = this.overlayEnum.getName();
    }

    @Override
    public void initButtons() {
        int xSpaceInterval = 5;
        int ySpaceInterval = 3;
        int leftX = centerX - BUTTON_WIDTH - xSpaceInterval;
        int rightX = centerX + xSpaceInterval;
        int currentY = centerY - (centerY / 2);

        // Set enableButton
        String buttonText;
        if(overlayEnum.isEnabled()){
            buttonText = getColoredButtonText("Enabled", TextFormatting.GREEN);

        }else{
            buttonText = getColoredButtonText("Disabled", TextFormatting.RED);
        }
        currentY += (BUTTON_HEIGHT + ySpaceInterval);
        enableButton = initButton(buttonText, leftX, currentY);

        // Set colorButton
        String colorButtonText = getColoredButtonText("Color", overlayEnum.getColorEnum().getTextFormatting());
        colorButton = initButton(colorButtonText, rightX, currentY);

        // CLOCK has an extra button..
        if(overlayEnum.isClockEnum()){

            String clockButtonText;
            if(overlayEnum.is24HourClock()){
                clockButtonText = getColoredButtonText("24 HR", TextFormatting.GREEN);

            }else{
                clockButtonText = getColoredButtonText("24 HR", TextFormatting.RED);
            }

            currentY += (BUTTON_HEIGHT + ySpaceInterval);
            clock24HourButton = initButton(clockButtonText, leftX, currentY);
        }

        // Set exitButton
        int exitX = centerX - (BUTTON_WIDTH / 2);
        currentY += (BUTTON_HEIGHT * 3); // move exit button down a bit below the other buttons
        exitButton = initButton("Done", exitX, currentY);
    }

    @Override
    public void buttonAction(GuiButton guiButton) {
        int buttonId = guiButton.id;
        if(buttonId == enableButton.id){
            overlayEnum.setEnabled(!overlayEnum.isEnabled());

        }else if(buttonId == colorButton.id){
            try{
                ColorEnum nextColorEnum = ColorEnum.getNextColorEnum(overlayEnum.getColorEnum());
                overlayEnum.setColor(nextColorEnum);

            }catch(ColorEnumNotFoundException e){
                //TODO: logging
            }

        }else if(overlayEnum.isClockEnum() && !clock24HourButton.equals(null) && buttonId == clock24HourButton.id){
            overlayEnum.setIs24HourClock(!overlayEnum.is24HourClock());

        }

        // Save config & re-init the gui
        overlayEnum.saveConfiguration(ConfigReference.configuration, true);
        super.initGui();
    }

    @Override
    protected void exitButtonAction(GuiButton guiButton) {
        minecraft.displayGuiScreen(new MainOverlayGUI());
    }

    private GuiButton initButton(String buttonText, int x, int y){
        GuiButton guiButton = new GuiButton(buttonList.size(), x, y, BUTTON_WIDTH, BUTTON_HEIGHT,buttonText);
        buttonList.add(guiButton);
        return guiButton;
    }

}
