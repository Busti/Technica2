package de.honeypot.technica.capability.item;

import de.honeypot.technica.capability.item.util.SidedItemStackHandler;

/**
 * The item handler for the conveyor belt.
 * The slots are used as follows:
 *   0 -> The universal input slot for the conveyor.
 *     Sides: UP, DOWN, FRONT, NULL
 *   1 -> The back side.
 *   2 -> The left side.
 *   3 -> The right side.
 */
public class ItemStackHandlerConveyor extends SidedItemStackHandler {
    public ItemStackHandlerConveyor() {
        super(4);
    }



//    @Nonnull
//    @Override
//    public ItemStack insertItem(@Nullable EnumFacing facing, int slot, @Nonnull ItemStack stack, boolean simulate) {
//        return super.insertItem(facing, slot, stack, simulate);
//    }
}
