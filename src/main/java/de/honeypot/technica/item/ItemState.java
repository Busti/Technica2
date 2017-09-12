package de.honeypot.technica.item;

import de.honeypot.technica.client.IModelRegistrator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public class ItemState extends ItemBlock implements IModelRegistrator {
    private final String variant;

    public ItemState(Block block, String variant) {
        super(block);
        this.variant = variant;
    }

    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), variant));
    }
}
