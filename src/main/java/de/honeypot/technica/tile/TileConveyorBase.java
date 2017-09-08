package de.honeypot.technica.tile;

import de.honeypot.technica.inventory.util.ISidedItemHandler;
import de.honeypot.technica.inventory.ItemStackHandlerConveyor;
import de.honeypot.technica.inventory.util.SidedItemHandlerWrapper;
import de.honeypot.technica.util.ISideSensitiveCapabilityStorage;
import net.minecraftforge.items.IItemHandler;

public class TileConveyorBase extends TileSidedItemHandler {
    @Override
    protected ISideSensitiveCapabilityStorage<IItemHandler, ISidedItemHandler> createInventory() {
        return new SidedItemHandlerWrapper(ItemStackHandlerConveyor::new);
    }
}
