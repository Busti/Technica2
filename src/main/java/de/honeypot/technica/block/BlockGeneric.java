package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockGeneric extends Block {
    public BlockGeneric(final Material material, final MapColor mapColor, final String name) {
        super(material, mapColor);
        setRegistryName(name);
        setUnlocalizedName(name);
        ModBlocks.registerBlock(this);
        setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);


        ItemBlock item = new ItemBlock(this);
        item.setRegistryName(name);
        item.setUnlocalizedName(name);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        ModItems.registerItem(item);


    }

    public BlockGeneric(final Material material, final String name) {
        this(material, material.getMaterialMapColor(), name);
    }
}
