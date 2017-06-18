package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLightOverlay extends AbstractOverlay{

    public BlockLightOverlay(){
        setOverlayEnum(OverlayEnum.BLOCK_LIGHT);
    }

    @Override
    public void updateDisplayMessage(EntityPlayer player) {

        // BROKEN
        World world = player.getEntityWorld();
        BlockPos blockPos = new BlockPos((int)player.posX, (int)player.posY, (int)player.posZ);
        Block block = world.getBlockState(blockPos).getBlock();
        int lightLevel = block.getLightValue(block.getBlockState().getBaseState(), world, blockPos);

        String displayMessage = "BL: " + Integer.toString(lightLevel);
        setDisplayMessage(displayMessage);
    }

    @Override
    public void updateDisplayMessage(Minecraft minecraft) {

    }
}
