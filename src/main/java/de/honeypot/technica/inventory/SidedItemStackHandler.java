package de.honeypot.technica.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class SidedItemStackHandler extends ItemStackHandler implements ISidedItemHandler {
    @Nonnull
    @Override
    public ItemStack insertItem(@Nullable EnumFacing facing, int slot, @Nonnull ItemStack stack, boolean simulate) {
        return insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(@Nullable EnumFacing facing, int slot, int amount, boolean simulate) {
        return extractItem(slot, amount, simulate);
    }
}
