package de.honeypot.technica.tile.conveyor;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ConveyorGraphElement {
    @Nonnull
    private final ItemStack itemStack;
    private float offset;
    private boolean isMoving;

    public ConveyorGraphElement(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
