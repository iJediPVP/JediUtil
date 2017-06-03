package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeOverlay extends AbstractOverlay {

    public BiomeOverlay(){
        setOverlayEnum(OverlayEnum.BIOME);
    }

    @Override
    public void updateDisplayMessage(EntityPlayer player) {
        // Get the biome from the player's coordinates
        World world = player.world;
        Biome biome = world.getBiomeForCoordsBody(player.getPosition());
        String displayMessage = "B: " + biome.getBiomeName();
        setDisplayMessage(displayMessage);
    }

    @Override
    public void updateDisplayMessage(Minecraft minecraft) {

    }


}
