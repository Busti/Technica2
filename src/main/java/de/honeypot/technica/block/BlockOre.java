package de.honeypot.technica.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;


public class BlockOre extends BlockGeneric {

    public BlockOre(final Material material, final String name) {
        super(material, name);

        setHardness(1.5f);
        setResistance(10);
        setSoundType(SoundType.STONE);
        setLightOpacity(16);
        setLightLevel(0);
        setHarvestLevel("pickaxe", 1);

    }
}
