package de.honeypot.technica.tile;

import net.minecraft.item.ItemStack;

/**
 * The base tileentity for all conveyor systems.
 * The inventory slots are used as follows:
 *     1 -> The main input slot for this conveyor.
 *     2 -> The main output slot for this conveyor.
 */
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
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return false;
    }
}
