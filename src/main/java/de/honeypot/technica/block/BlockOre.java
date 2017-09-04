package de.honeypot.technica.block;

import de.honeypot.technica.util.IEnumVariants;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

import java.util.Comparator;
import java.util.stream.Stream;

public class BlockOre extends BlockVariants<BlockOre.EnumType> {
    public BlockOre(Material material, String name) {
        super(material, name, EnumType.class);
        setHardness(3f);
        setResistance(5f);

        for (EnumType type : EnumType.values())
            setHarvestLevel("pickaxe", type.level, getStateFromVariant(type));
    }

    public enum EnumType implements IStringSerializable, IEnumVariants {
        // @formatter:off
        COPPER(   "copper",    0, 1),
        ALUMINIUM("aluminium", 1, 1),
        LEAD(     "lead",      2, 2);
        // @formatter:on

        private static final EnumType[] META_LOOKUP = Stream.of(values()).sorted(Comparator.comparing(EnumType::getMetadata)).toArray(EnumType[]::new);

        private final String name;
        private final int meta;
        private final int level;

        EnumType(String name, int meta, int level) {
            this.name = name;
            this.meta = meta;
            this.level = level;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getMetadata() {
            return meta;
        }

        @Override
        public EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length)
                meta = 0;
            return META_LOOKUP[meta];
        }
    }
}
