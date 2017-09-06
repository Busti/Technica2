package de.honeypot.technica.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileConveyorBase extends TileInventory {
    @Override
    public String getTileName() {
        return "tile.technica.conveyor.name";
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
