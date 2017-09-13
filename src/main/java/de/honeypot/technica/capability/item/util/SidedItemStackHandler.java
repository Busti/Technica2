package de.honeypot.technica.capability.item.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An addon to {@link ItemStackHandler} that handles the methods from {@link ISidedItemHandler},
 * by passing them along to the non side sensitive versions in {@link ItemStackHandler}.
 * This class is abstract because instantiating it would defy the purpose of {@link ISidedItemHandler}
 * since it would behave the same as implementing {@link ItemStackHandler} without the side sensitive abstraction stack.
 */
public abstract class SidedItemStackHandler extends ItemStackHandler implements ISidedItemHandler {
    protected SidedItemStackHandler() {
        this(1);
    }

    protected SidedItemStackHandler(int size) {
        super(size);
    }

    protected SidedItemStackHandler(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

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
