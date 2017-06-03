package me.ijedi.jediutil.enums;

import me.ijedi.jediutil.exceptions.ColorEnumNotFoundException;
import net.minecraft.util.text.TextFormatting;

public enum ColorEnum {

    // Source: http://minecraft.gamepedia.com/Formatting_codes
    BLACK(0x000000, TextFormatting.BLACK),
    DARK_BLUE(0x0000AA, TextFormatting.DARK_BLUE),
    DARK_GREEN(0x00AA00, TextFormatting.DARK_GREEN),
    DARK_AQUA(0x00AAAA, TextFormatting.DARK_AQUA),
    DARK_RED(0xAA0000, TextFormatting.DARK_RED),
    DARK_PURPLE(0xAA00AA, TextFormatting.DARK_PURPLE),
    GOLD(0xFFAA00, TextFormatting.GOLD),
    GRAY(0xAAAAAA, TextFormatting.GRAY),
    DARK_GRAY(0x555555, TextFormatting.DARK_GRAY),
    BLUE(0x5555FF, TextFormatting.BLUE),
    GREEN(0x55FF55, TextFormatting.GREEN),
    AQUA(0x55FFFF, TextFormatting.AQUA),
    RED(0xFF5555, TextFormatting.RED),
    LIGHT_PURPLE(0xFF55FF, TextFormatting.LIGHT_PURPLE),
    YELLOW(0xFFFF55, TextFormatting.YELLOW),
    WHITE(0xFFFFFF, TextFormatting.WHITE);

    private int colorCode;
    private TextFormatting textFormatting;
    ColorEnum(int colorCode, TextFormatting textFormatting){
        this.colorCode = colorCode;
        this.textFormatting = textFormatting;
    }

    public int getColorCode() {
        return colorCode;
    }

    public TextFormatting getTextFormatting(){
        return textFormatting;
    }

    public static ColorEnum getColorEnumFromString(String colorString){
        ColorEnum[] colorEnums = ColorEnum.values();
        for(ColorEnum colorEnum : colorEnums){
            if(colorEnum.toString().equalsIgnoreCase(colorString)){
                return colorEnum;
            }
        }
        throw new ColorEnumNotFoundException("Could not find ColorEnum from string: " + colorString);
    }

    public static ColorEnum getNextColorEnum(ColorEnum colorEnum){
        ColorEnum[] colorEnums = ColorEnum.values();
        for(int x = 0; x < colorEnums.length; x++){
            if(colorEnums[x].equals(colorEnum)){
                x++;
                // Get first item in array
                if(x == colorEnums.length){
                    x = 0;
                }
                return colorEnums[x];
            }
        }
        throw new ColorEnumNotFoundException("Could not find next ColorEnum after: " + colorEnum.toString());
    }
}
