package me.ijedi.jediutil.events;

import me.ijedi.jediutil.client.gui.MainGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

public class UtilKeyInputEvent extends KeyInputEvent {

    private KeyBinding openGUIKeyBind = new KeyBinding("Open JediUtil GUI", Keyboard.KEY_M, "JediUtil");

    public UtilKeyInputEvent(){
        ClientRegistry.registerKeyBinding(openGUIKeyBind);
    }

    @SubscribeEvent
    public void onKeyInputEvent(KeyInputEvent event){

        if(openGUIKeyBind.isPressed()){
            Minecraft.getMinecraft().displayGuiScreen(new MainGUI());
        }

    }

}
