package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockGeneric extends Block {
    public BlockGeneric(final Material material, final MapColor mapColor, final String name) {
        super(material, mapColor);
        setRegistryName(Technica.MODID, name);
        setUnlocalizedName(name);
        ModBlocks.registerBlock(this);
    }

    public BlockGeneric(final Material material, final String name) {
        this(material, material.getMaterialMapColor(), name);
    }
}
