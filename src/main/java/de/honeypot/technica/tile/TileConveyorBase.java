package de.honeypot.technica.tile;

import de.honeypot.technica.util.ISideSensitiveCapabilityStorage;
import net.minecraftforge.items.IItemHandler;

public class TileConveyorBase extends TileSidedItemHandler {
    @Override
    protected ISideSensitiveCapabilityStorage<IItemHandler> createInventory() {
        return null;
    }
}
