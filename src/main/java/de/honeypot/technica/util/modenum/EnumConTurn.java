package de.honeypot.technica.util.modenum;

import net.minecraft.util.IStringSerializable;

public enum EnumConTurn implements IStringSerializable {
    STRAIGHT("straight"),
    LEFT("left"),
    RIGHT("right");

    private final String name;

    EnumConTurn(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
