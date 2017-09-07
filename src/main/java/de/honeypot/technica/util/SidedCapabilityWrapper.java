package de.honeypot.technica.util;

import jline.internal.Nullable;
import net.minecraft.util.EnumFacing;

import java.util.HashMap;

public abstract class SidedCapabilityWrapper<C> {
    private final HashMap<EnumFacing, C> sideToCapabilityMapping;
    private final C sideless;

    protected SidedCapabilityWrapper() {
        sideToCapabilityMapping = new HashMap<>();
        for (EnumFacing facing :EnumFacing.VALUES) {
            sideToCapabilityMapping.put(facing, createCapability(facing));
        }
        sideless = createCapability(null);
    }

    protected abstract C createCapability(@Nullable EnumFacing facing);

    public C getCapability(@Nullable EnumFacing facing) {
        return sideToCapabilityMapping.getOrDefault(facing, sideless);
    }
}
