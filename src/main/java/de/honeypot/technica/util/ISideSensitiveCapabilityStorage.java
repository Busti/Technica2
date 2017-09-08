package de.honeypot.technica.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

public interface ISideSensitiveCapabilityStorage<T> extends INBTSerializable<NBTTagCompound> {
    T getCapability(@Nullable EnumFacing facing);
}
