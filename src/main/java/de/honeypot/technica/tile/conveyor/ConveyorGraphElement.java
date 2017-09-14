package de.honeypot.technica.tile.conveyor;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nonnull;

public class ConveyorGraphElement {

    private final ItemStack itemStack;
    private float offset;
    private boolean isMoving;

    private final ConveyorGraph parent;

    @SideOnly(Side.CLIENT)
    private final Vector3f lastBlockOffset;



    public ConveyorGraphElement(ItemStack itemStack, ConveyorGraph parent) {
        this.itemStack = itemStack;
        this.parent = parent;
        lastBlockOffset = new Vector3f(0.5f, 0.5f, 0.5f);
    }

    @SideOnly(Side.CLIENT)
    public Vector3f getOffsetFromOriginBlock(double position){
        return lastBlockOffset;

    }
}
