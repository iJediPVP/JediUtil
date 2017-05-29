package me.ijedi.jediutil.event;

import me.ijedi.jediutil.ref.ModReference;
import me.ijedi.jediutil.ui.UIManager;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ConfigurationChangeEvent {

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.getModID().equalsIgnoreCase(ModReference.MOD_ID)){
            ConfigManager.sync(ModReference.MOD_ID, Config.Type.INSTANCE);
            UIManager.resetUIObjects();
        }
    }
}
