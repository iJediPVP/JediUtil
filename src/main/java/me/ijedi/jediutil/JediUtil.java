package me.ijedi.jediutil;

import me.ijedi.jediutil.event.*;
import me.ijedi.jediutil.ref.ModReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ModReference.MOD_ID, name = ModReference.NAME, version = ModReference.VERSION, acceptedMinecraftVersions = ModReference.ACCEPTED_VERSIONS, canBeDeactivated = true)
public class JediUtil {

    public static EntityPlayer player;
    /*@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }*/


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Print(">>>>> JediUtil starting up. <<<<<");

        MinecraftForge.EVENT_BUS.register(new RenderEvent());
        MinecraftForge.EVENT_BUS.register(new EntityJoinEvent());
    }

    /*@Mod.EventHandler
    public void post(FMLPostInitializationEvent event) {

    }*/

    public static void Print(String message){
        System.out.println(message);
    }

}
