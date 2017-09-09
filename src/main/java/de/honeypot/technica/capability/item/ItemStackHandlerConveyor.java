package de.honeypot.technica.capability.item;

import de.honeypot.technica.capability.item.util.SidedItemStackHandler;
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
