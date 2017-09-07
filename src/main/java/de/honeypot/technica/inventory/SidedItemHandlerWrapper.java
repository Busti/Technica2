package de.honeypot.technica.inventory;

import de.honeypot.technica.util.SidedCapabilityWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public abstract class SidedItemHandlerWrapper extends SidedCapabilityWrapper<IItemHandler> {
    private final ISidedItemHandler itemHandler;

    protected SidedItemHandlerWrapper(Supplier<ISidedItemHandler> itemHandlerSupplier) {
        super();

        itemHandler = itemHandlerSupplier.get();
    }

    @Override
    protected IItemHandler createCapability(EnumFacing facing) {
        return new SidedItemHandler(this, facing);
    }

    private class SidedItemHandler implements IItemHandler {
        private final SidedItemHandlerWrapper wrapper;
        private final EnumFacing facing;

        private SidedItemHandler(SidedItemHandlerWrapper wrapper, @Nullable EnumFacing facing) {
            this.wrapper = wrapper;
            this.facing = facing;
        }

        @Override
        public int getSlots() {
            return wrapper.itemHandler.getSlots();
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return wrapper.itemHandler.getStackInSlot(slot);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return wrapper.itemHandler.insertItem(facing, slot, stack, simulate);
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return wrapper.itemHandler.extractItem(facing, slot, amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return wrapper.itemHandler.getSlotLimit(slot);
        }
    }
}
