package de.honeypot.technica.util.modenum;

import net.minecraft.util.IStringSerializable;

public enum EnumSide implements IStringSerializable {
    BACK("back"),
    LEFT("left"),
    FRONT("front"),
    RIGHT("right");

    private final String name;

    EnumSide(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
