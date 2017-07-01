package me.ijedi.jediutil;

import me.ijedi.jediutil.events.*;
import me.ijedi.jediutil.client.overlays.OverlayManager;
import me.ijedi.jediutil.ref.ConfigReference;
import me.ijedi.jediutil.ref.ModReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModReference.MOD_ID, name = ModReference.NAME, version = ModReference.VERSION, acceptedMinecraftVersions = ModReference.ACCEPTED_VERSIONS, canBeDeactivated = true)
public class JediUtil {

    public static OverlayManager overlayManager;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigReference.init(event.getSuggestedConfigurationFile());
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Print(">>>>> JediUtil starting up. <<<<<");

        MinecraftForge.EVENT_BUS.register(new RenderEvent());

        MinecraftForge.EVENT_BUS.register(new UtilKeyInputEvent());

        overlayManager = new OverlayManager();
    }

    /*@Mod.EventHandler
    public void post(FMLPostInitializationEvent event) {

    }*/

    public static void Print(String message){
        System.out.println(message);
    }

}

// TODO:
/* Add:
    Block light level
    Sky light level
    Chunk counter

    UI:
        Add way to manage overlay order (aka rank)
        Fix UI Scaling.. it's something to do with dividing by 4 instead of 2. But 2 doesn't work for bigger resolutions..
        Fix FPS not showing in SMP
* */