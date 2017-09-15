package de.honeypot.technica.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * Created by Chloroplast on 15.09.2017.
 */
public class TestTE extends TileEntity implements ITickable{
    @Override
    public void update() {
        System.out.println("tick");
    }
}
