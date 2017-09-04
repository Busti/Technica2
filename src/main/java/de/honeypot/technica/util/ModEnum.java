package de.honeypot.technica.util;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Chloroplast on 03.09.2017.
 */
public class ModEnum {

    public enum ENUM_DIRECTION implements IStringSerializable {
        NORTH, EAST, SOUTH, WEST;

        public ENUM_DIRECTION getMirrored(){

            switch(this){
                case NORTH: return SOUTH;
                case EAST:  return WEST;
                case SOUTH: return NORTH;
                case WEST:  return EAST;
            }
            return null;
        }

        @Override
        public String getName() {
            return this.name().toLowerCase();
        }
    }

}
