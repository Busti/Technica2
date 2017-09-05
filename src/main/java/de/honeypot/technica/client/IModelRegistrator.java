package de.honeypot.technica.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IModelRegistrator {
    @SideOnly(Side.CLIENT)
    void registerModel();
}
