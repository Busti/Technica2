package de.honeypot.technica.util;

import jline.internal.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.function.Supplier;

public abstract class SidedCapabilityWrapper<C, I extends INBTSerializable<NBTTagCompound>> implements ISideSensitiveCapabilityStorage<C> {
    private final I capabilityImpl;
    private final HashMap<EnumFacing, C> sideToCapabilityMapping;

    protected SidedCapabilityWrapper(Supplier<I> capabilityImplSupplier) {
        capabilityImpl = capabilityImplSupplier.get();

        sideToCapabilityMapping = new HashMap<>();
        for (EnumFacing facing :EnumFacing.VALUES)
            sideToCapabilityMapping.put(facing, createCapability(facing));
        sideToCapabilityMapping.put(null, createCapability(null));
    }

    protected I getCapabilityImpl() {
        return capabilityImpl;
    }

    protected abstract C createCapability(@Nullable EnumFacing facing);

    @Override
    public C getCapability(@Nullable EnumFacing facing) {
        return sideToCapabilityMapping.get(facing);
    }
}
