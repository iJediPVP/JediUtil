package me.ijedi.jediutil.client.gui;

import me.ijedi.jediutil.JediUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class OverlayOrderGUI extends AbstractGUI {
    GuiTextField textField;

    @Override
    protected void initButtons() {

    }

    @Override
    protected void initGuiItems() {
        JediUtil.Print("OverlayOrderGUI initButtons - Start");
        textField = new GuiTextField(100, fontRendererObj, 5,5, 100,12);
        textField.setMaxStringLength(32);
        textField.setText("example text");
        textField.setEnableBackgroundDrawing(true);
        textField.drawTextBox();


        JediUtil.Print("OverlayOrderGUI initButtons - Finish");
    }

    @Override
    protected void buttonAction(GuiButton guiButton) {

    }

    @Override
    protected void exitButtonAction(GuiButton guiButton) {
        minecraft.displayGuiScreen(new MainGUI());
    }

}
