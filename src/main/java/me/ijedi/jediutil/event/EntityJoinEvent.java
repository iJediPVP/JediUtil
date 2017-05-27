package me.ijedi.jediutil.event;

import me.ijedi.jediutil.JediUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityJoinEvent {

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event){

        // When a player joins set utilPlayer in JediUtil
        if(event.getEntity() instanceof EntityPlayer){
            JediUtil.player = (EntityPlayer)event.getEntity();
        }
    }

}
