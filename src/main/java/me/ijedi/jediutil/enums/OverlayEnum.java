package me.ijedi.jediutil.enums;

import me.ijedi.jediutil.client.overlays.OverlayManager;
import me.ijedi.jediutil.exceptions.ColorEnumNotFoundException;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public enum OverlayEnum {

    BIOME("Biome", "biome", true, ColorEnum.WHITE.toString(), false),
    CLOCK("Clock", "clock", true, ColorEnum.WHITE.toString(), true),
    COORDINATES("Coordinates", "coordinates", true, ColorEnum.WHITE.toString(), false),
    DIRECTION("Direction", "direction", true, ColorEnum.WHITE.toString(), false),
    FRAME_RATE("Frame Rate", "framerate", true, ColorEnum.WHITE.toString(), false),
    BLOCK_LIGHT("Block Light", "blocklight", true, ColorEnum.WHITE.toString(), false);

    private String name;
    private String configCategory;
    private boolean isEnabled;
    private String color;
    private boolean is24HourClock;

    private final String ENABLED = "enabled";
    private final String COLOR = "color";
    private final String CLOCK_24HOUR = "24HourClock";

    OverlayEnum(String name, String configCategory, boolean isEnabled, String color, boolean is24HourClock){
        this.name = name;
        this.configCategory = configCategory;
        this.isEnabled = isEnabled;
        this.color = color;
        this.is24HourClock = is24HourClock;
    }

    public String getName() {
        return name;
    }

    public String getConfigCategory() {
        return OverlayManager.OVERLAY_CATEGORY + "." + configCategory;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public ColorEnum getColorEnum() {
        try{
            return ColorEnum.getColorEnumFromString(color);
        }catch(ColorEnumNotFoundException e){
            return ColorEnum.WHITE;
        }
    }

    public void setColor(ColorEnum colorEnum) {
        this.color = colorEnum.toString();
    }

    public boolean is24HourClock() {
        return is24HourClock;
    }

    public void setIs24HourClock(boolean is24HourClock) {
        this.is24HourClock = is24HourClock;
    }

    public boolean isClockEnum(){
        return this.equals(OverlayEnum.CLOCK);
    }

    public void loadConfiguration(Configuration configuration, boolean save){
        isEnabled = configuration.getBoolean(ENABLED, getConfigCategory(), true, String.format("Enable %s overlay", name));
        color = configuration.getString(COLOR, getConfigCategory(), ColorEnum.WHITE.toString(), String.format("Overlay color", name));

        if(isClockEnum()){
            is24HourClock = configuration.getBoolean(CLOCK_24HOUR, getConfigCategory(), true, String.format("24 Hour Clock", name));

        }

        if(save){
            saveConfig(configuration);
        }
    }

    public void saveConfiguration(Configuration configuration, boolean save){
        ConfigCategory category = configuration.getCategory(getConfigCategory());
        category.get(ENABLED).set(isEnabled);
        category.get(COLOR).set(color);

        if(isClockEnum()){
            category.get(CLOCK_24HOUR).set(is24HourClock);
        }

        if(save){
            saveConfig(configuration);
        }
    }

    private void saveConfig(Configuration configuration){
        if(configuration.hasChanged()){
            configuration.save();
        }
    }
}
