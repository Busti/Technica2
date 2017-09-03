package de.honeypot.technica.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class BlockOre extends BlockVariants<BlockOre.Type> {
    public BlockOre(Material material, String name) {
        super(material, name, Type.class);
        setHardness(3f);
        setResistance(5f);

        for (Type type: Type.values())
            setHarvestLevel("pickaxe", type.level, getStateFromVariant(type));
    }

    public enum Type implements IStringSerializable {
        COPPER("copper", 1),
        ALUMINIUM("aluminium", 1),
        LEAD("lead", 2);

        private final String name;
        private final int level;

        Type(String name, int level) {
            this.name = name;
            this.level = level;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
