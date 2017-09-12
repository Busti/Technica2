package de.honeypot.technica.util.modenum;

import net.minecraft.util.IStringSerializable;

public enum EnumConTurn implements IStringSerializable {
    STRAIGHT("straight"),
    LEFT("left"),
    RIGHT("right"),
    STRAIGHT_STILL("straight_still"),
    LEFT_STILL("left_still"),
    RIGHT_STILL("right_still");;

    private final String name;

    EnumConTurn(String name) {
        this.name = name;
    }

    public EnumConTurn getStill(){
        switch (this){

            case STRAIGHT: return STRAIGHT_STILL;
            case LEFT:     return LEFT_STILL;
            case RIGHT:    return RIGHT_STILL;
            default:       return this;
        }
    }

    public EnumConTurn getMoving(){
        switch (this){

            case STRAIGHT_STILL: return STRAIGHT;
            case LEFT_STILL:     return LEFT;
            case RIGHT_STILL:    return RIGHT;
            default:       return this;
        }
    }

    public boolean isMoving(){
        switch (this) {
            case STRAIGHT:
            case LEFT:
            case RIGHT:     return true;
            default:        return false;
        }
    }


    @Override
    public String getName() {
        return name;
    }
}
