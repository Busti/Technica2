package de.honeypot.technica.tile;

import de.honeypot.technica.capability.item.util.ISidedItemHandler;
import de.honeypot.technica.capability.ISideSensitiveCapabilityStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public abstract class TileSidedItemHandler<C extends ISideSensitiveCapabilityStorage<IItemHandler, ISidedItemHandler>> extends TileEntity {
    protected final C inventory = createInventory();

    protected abstract C createInventory();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(final Capability<T> capability, @Nullable final EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory.getCapability(facing));
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        inventory.deserializeNBT(compound.getCompoundTag("ItemHandler"));
    }

    @Override
    public NBTTagCompound writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("ItemHandler", inventory.serializeNBT());
        return compound;
    }
}
