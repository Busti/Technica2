package de.honeypot.technica.capability;

import jline.internal.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * A capability wrapper that stores an instance of a sideless capability for every side available in {@link EnumFacing}
 * A single side sensitive capability instance is stored as the method calls to the sideless versions should
 * be passed through to the sided implementation by the implementing class.
 * @param <C> The type if the sideless capability.
 * @param <I> The type of the side sensitive capability.
 */
public abstract class SidedCapabilityWrapper<C, I extends INBTSerializable<NBTTagCompound>> implements ISideSensitiveCapabilityStorage<C, I> {
    private final I capabilityImpl;
    private final HashMap<EnumFacing, C> sideToCapabilityMapping;

    protected SidedCapabilityWrapper(Supplier<I> capabilityImplSupplier) {
        capabilityImpl = capabilityImplSupplier.get();

        sideToCapabilityMapping = new HashMap<>();
        for (EnumFacing facing :EnumFacing.VALUES)
            sideToCapabilityMapping.put(facing, createCapability(facing));
        sideToCapabilityMapping.put(null, createCapability(null));
    }

    @Override
    public I getCapabilityImpl() {
        return capabilityImpl;
    }

    /**
     * Creates a new sideless capability instance that calls the appropriate methods in the side sensitive capability
     * instance using the side given by this method. In the event that null is given in the facing parameter null
     * must also be passed to the side sensitive instance.
     * @param facing The facing the sideless instance should be initialized with.
     * @return The sideless instance.
     */
    protected abstract C createCapability(@Nullable EnumFacing facing);

    @Override
    public C getCapability(@Nullable EnumFacing facing) {
        return sideToCapabilityMapping.get(facing);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return capabilityImpl.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        capabilityImpl.deserializeNBT(nbt);
    }
}
