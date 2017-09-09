package de.honeypot.technica.inventory;

import de.honeypot.technica.inventory.util.SidedItemStackHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemStackHandlerConveyor extends SidedItemStackHandler {
    @Nonnull
    @Override
    public ItemStack insertItem(@Nullable EnumFacing facing, int slot, @Nonnull ItemStack stack, boolean simulate) {
        return super.insertItem(facing, slot, stack, simulate);
    }
}
