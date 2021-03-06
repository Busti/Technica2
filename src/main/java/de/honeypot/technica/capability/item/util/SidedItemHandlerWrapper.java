package de.honeypot.technica.capability.item.util;

import de.honeypot.technica.capability.SidedCapabilityWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A sided wrapper around the {@link IItemHandler} capability.
 * @param <I> The type of the side sensitive implementation.
 */
public class SidedItemHandlerWrapper<I extends ISidedItemHandler & INBTSerializable<NBTTagCompound>> extends SidedCapabilityWrapper<IItemHandler, I> {
    public SidedItemHandlerWrapper(I capabilityImpl) {
        super(capabilityImpl);
    }

    @Override
    protected IItemHandler createCapability(EnumFacing facing) {
        return new SideItemHandler(this, facing);
    }

    private class SideItemHandler implements IItemHandler {
        private final SidedItemHandlerWrapper<I> wrapper;
        private final EnumFacing facing;

        private SideItemHandler(SidedItemHandlerWrapper<I> wrapper, @Nullable EnumFacing facing) {
            this.wrapper = wrapper;
            this.facing = facing;
        }

        @Override
        public int getSlots() {
            return wrapper.getCapabilityImpl().getSlots();
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return wrapper.getCapabilityImpl().getStackInSlot(slot);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return wrapper.getCapabilityImpl().insertItem(facing, slot, stack, simulate);
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return wrapper.getCapabilityImpl().extractItem(facing, slot, amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return wrapper.getCapabilityImpl().getSlotLimit(slot);
        }
    }
}
