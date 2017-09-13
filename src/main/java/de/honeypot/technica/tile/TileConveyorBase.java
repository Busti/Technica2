package de.honeypot.technica.tile;

import de.honeypot.technica.capability.item.ItemStackHandlerConveyor;
import de.honeypot.technica.capability.item.util.ISidedItemHandler;
import de.honeypot.technica.capability.item.util.SidedItemHandlerWrapper;
import de.honeypot.technica.capability.ISideSensitiveCapabilityStorage;
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

}
