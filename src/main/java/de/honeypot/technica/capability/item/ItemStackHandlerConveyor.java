package de.honeypot.technica.capability.item;

import de.honeypot.technica.capability.item.util.SidedItemStackHandler;

/**
 * The item handler for the conveyor belt.
 * The slots are used as follows:
 *   1 -> The input slot of the conveyor belt.
 *   2 -> The motor slot.
 */
public class ItemStackHandlerConveyor extends SidedItemStackHandler {
    public ItemStackHandlerConveyor() {
        super(2);
    }



//    @Nonnull
//    @Override
//    public ItemStack insertItem(@Nullable EnumFacing facing, int slot, @Nonnull ItemStack stack, boolean simulate) {
//        return super.insertItem(facing, slot, stack, simulate);
//    }
}
