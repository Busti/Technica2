package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import jline.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {
    public BlockBase(Material material, MapColor mapColor, String name) {
        super(material, mapColor);
        setRegistryName(name);
        setUnlocalizedName(name);
        ModBlocks.registerBlock(this);
        setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);

        Item item = generateBlockItem(name);
        if (item != null)
            ModItems.registerItem(item);
    }

    public BlockBase(Material material, String name) {
        this(material, material.getMaterialMapColor(), name);
    }

    /**
     * @return An ItemBlock used for this block.
     */
    @Nullable
    public Item getBlockItem() {
        return new ItemBlock(this);
    }

    /**
     * Internal method used to generate the BlockItem for this block.
     * Automatically sets the unlocalized and registry name.
     * @param name The name used for the registry.
     * @return The generated Item
     */
    protected Item generateBlockItem(String name) {
        Item item = getBlockItem();
        if (item == null) return null;
        item.setRegistryName(name);
        item.setUnlocalizedName(name);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        return item;
    }
}
