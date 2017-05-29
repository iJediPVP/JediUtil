package me.ijedi.jediutil.ref;

import net.minecraftforge.common.config.Config;

@Config(modid = ModReference.MOD_ID)
public class ConfigReference {

    // Config properties
    @Config.Comment("Turn on/off all GUI overlay.")
    @Config.Name("Enable GUI")
    public static boolean isGuiOn = true;

    @Config.Comment("Turn on/off the coordinates.")
    @Config.Name("Enable Coordinates")
    public static boolean isShowCoordinates = true;

    @Config.Comment("Turn on/off the direction indicator.")
    @Config.Name("Enable Direction")
    public static boolean isShowDirection = true;

    @Config.Comment("Turn on/off the biome indicator.")
    @Config.Name("Enable Biome")
    public static boolean isShowBiome = true;

    @Config.Comment("Turn on/off the clock.")
    @Config.Name("Enable Clock")
    public static boolean isShowClock = true;

    @Config.Comment("Turn on/off 24 hour clock time.")
    @Config.Name("Enable 24 Hour Clock")
    public static boolean isClock24Hour = true;

}
