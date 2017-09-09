package de.honeypot.technica.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

/**
 * Stores capabilities in a side sensitive manner.
 * Must contain a singular implementation to be used.
 * The implementation of this interface must be capable to serialize all of its contents to nbt.
 * @param <C> The Type used by the non side sensitive capabilities.
 * @param <I> The Type used by the implementation of the sided capability.
 */
public interface ISideSensitiveCapabilityStorage<C, I> extends INBTSerializable<NBTTagCompound> {
    /**
     * Get a non side sensitive capability by a side.
     * @param facing The side the capability was accessed from.
     * @return The capability.
     */
    C getCapability(@Nullable EnumFacing facing);

    /**
     * Get the sided capability implementation.
     * @return The capability.
     */
    I getCapabilityImpl();
}
