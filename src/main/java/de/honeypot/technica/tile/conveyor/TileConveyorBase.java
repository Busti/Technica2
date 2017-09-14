package de.honeypot.technica.tile.conveyor;

import de.honeypot.technica.capability.ISideSensitiveCapabilityStorage;
import de.honeypot.technica.capability.item.ItemStackHandlerConveyor;
import de.honeypot.technica.capability.item.util.ISidedItemHandler;
import de.honeypot.technica.capability.item.util.SidedItemHandlerWrapper;
import de.honeypot.technica.tile.TileSidedItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public class TileConveyorBase extends TileSidedItemHandler {

    @Override
    protected ISideSensitiveCapabilityStorage<IItemHandler, ISidedItemHandler> createInventory() {
        return new SidedItemHandlerWrapper(new ItemStackHandlerConveyor());
    }

    public boolean isPowered(){
        //TODO: IMPLEMENT
        return true;
    }

    public ConveyorGraphElement[] getGraphElementsOnConveyorBlock() {
        ConveyorGraphElement[] list = new ConveyorGraphElement[  ConveyorGraph.MAX_ELEMENTS_ON_BLOCK  ];

        return list;
    }

}
