package me.ijedi.jediutil.enums;

public enum PlayerDirectionEnum {

    // Enums
    SOUTH("S", 337.5F, 22.5F),
    SOUTH_WEST("SW", 22.5F, 67.5F),
    WEST("W", 67.5F, 112.5F),
    NORTH_WEST("NW", 112.5F, 157.5F), // not Kanye's daughter
    NORTH("N", 157.5F, 202.5F),
    NORTH_EAST("NE", 202.5F, 247.5F),
    EAST("E", 247.5F, 292.5F),
    SOUTH_EAST("SE", 292.5F, 337.5F);

    // Properties
    String directionString;
    float min;
    float max;

    PlayerDirectionEnum(String directionString, float min, float max){
        this.directionString = directionString;
        this.min = min;
        this.max = max;
    }

    // Getters
    public String toDirectionString() {
        return directionString;
    }
    public boolean isDirection(float f) {
        if(this.equals(SOUTH)){ // The south is strange like always
            return (f > this.min || f < this.max);
        }else{
            return (f > this.min && f < this.max);
        }
    }

    // Public Methods
    public static String getDirectionString(float yaw){

        yaw %= 360;
        if(yaw < 0){
            yaw += 360;
            //yaw *= -1;
        }

        // Figure out which direction it is
        for(PlayerDirectionEnum directionEnum : PlayerDirectionEnum.values()){
            if(directionEnum.isDirection(yaw)){
                return directionEnum.toDirectionString();
            }
        }
        return "UNKNOWN";
    }
}
