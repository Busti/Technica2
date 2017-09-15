package de.honeypot.technica.tile.conveyor;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.joml.Vector3f;

public class ConveyorGraphElement {

    private final ItemStack itemStack;
    private float offset;
    private boolean isMoving;

    private final ConveyorGraph parent ;

    @SideOnly(Side.CLIENT)
    private final Vector3f lastBlockOffset = new Vector3f(0, 0.75f, 0); // y defines the height of items



    public ConveyorGraphElement(ItemStack itemStack, ConveyorGraph parent) {
        this.itemStack = itemStack;
        this.parent = parent;
    }

    @SideOnly(Side.CLIENT)
    public Vector3f getOffsetFromOriginBlock(){
        return lastBlockOffset;
    }

    public double getOffset(){
        return offset;
    }
}
