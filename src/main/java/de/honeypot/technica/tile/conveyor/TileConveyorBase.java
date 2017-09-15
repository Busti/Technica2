package de.honeypot.technica.tile.conveyor;

import de.honeypot.technica.block.BlockConveyor;
import de.honeypot.technica.capability.ISideSensitiveCapabilityStorage;
import de.honeypot.technica.capability.item.ItemStackHandlerConveyor;
import de.honeypot.technica.capability.item.util.ISidedItemHandler;
import de.honeypot.technica.capability.item.util.SidedItemHandlerWrapper;
import de.honeypot.technica.tile.TileSidedItemHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

public class TileConveyorBase extends TileSidedItemHandler {



    @Override
    protected ISideSensitiveCapabilityStorage<IItemHandler, ISidedItemHandler> createInventory() {
        return new SidedItemHandlerWrapper(new ItemStackHandlerConveyor());
    }

    public boolean isPowered(){
        //TODO: IMPLEMENT
        return true;
    }

    /**
     * should be called everytime the blockstate was updated
     */
    public void blockStateUpdated(IBlockState stateNew){

        if(getWorld().isRemote){
            stateGeometry = ConveyorGeometry.get(stateNew.getValue(BlockConveyor.BELT), stateNew.getValue(BlockConveyor.FACING));
        }

    }

/* -------------------  renderer  -------------------------------- */

    @SideOnly(Side.CLIENT) private ConveyorGraphElement[] containingCGE = new ConveyorGraphElement[  ConveyorGraph.MAX_ELEMENTS_ON_BLOCK  ];
    @SideOnly(Side.CLIENT) private int containingCGEIndex;

    @SideOnly(Side.CLIENT) private ConveyorGeometry stateGeometry;

    @SideOnly(Side.CLIENT)
    public ConveyorGraphElement[] getGraphElementsOnConveyorBlock() {
        return containingCGE;
    }

    /**
     * clears the array of cashed ConveyorGraphElements this TileEntity containing.
     * normally this method should only invoked by ConveyorGraph
     *
     * not threadsafe
     */
    @SideOnly(Side.CLIENT)
    public void cacheContainingClear(){
        for(;containingCGEIndex > 0; --containingCGEIndex){
            containingCGE[containingCGEIndex - 1] = null;
        }
    }

    /**
     * adds an element to the array of cashed ConveyorGraphElements this TileEntity contains.
     * normally this method should only invoked by ConveyorGraph
     *
     * not threadsafe
     */
    @SideOnly(Side.CLIENT)
    public void cacheContainingAdd(ConveyorGraphElement cge){
        containingCGE[containingCGEIndex++] = cge;
    }

    @SideOnly(Side.CLIENT)
    public ConveyorGeometry getItemGeometry(){
        return stateGeometry;
    }




}
