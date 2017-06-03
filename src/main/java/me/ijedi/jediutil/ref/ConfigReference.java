package me.ijedi.jediutil.ref;

import me.ijedi.jediutil.client.overlays.OverlayManager;
import me.ijedi.jediutil.enums.OverlayEnum;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigReference {

    public static Configuration configuration;

    // Properties here
    public static boolean allOverlaysEnabled = true;
    private static final String ALL_OVERLAYS_ENABLED = "overlaysEnabled";

    public static void init(File configFile){
        if(configuration == null){
            configuration = new Configuration(configFile);
        }
        loadConfiguration();
    }

    public static void loadConfiguration(){

        allOverlaysEnabled = configuration.getBoolean(ALL_OVERLAYS_ENABLED, OverlayManager.OVERLAY_CATEGORY, true, "Enable all overlays");

        // Load overlay config settings
        for(OverlayEnum overlayEnum : OverlayEnum.values()){
            overlayEnum.loadConfiguration(configuration, false);
        }

        if(configuration.hasChanged()){
            configuration.save();
        }
    }

}
