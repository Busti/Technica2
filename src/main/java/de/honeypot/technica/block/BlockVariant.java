package de.honeypot.technica.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class BlockVariant extends BlockGeneric {
    public static IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockVariant(Material material, MapColor mapColor, String name) {
        super(material, mapColor, name);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        return getDefaultState().withProperty(VARIANT, EnumType.values()[meta]);
    }

    @Override
    public int getMetaFromState(final IBlockState state) {
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    public int damageDropped(final IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(final CreativeTabs tab, final NonNullList<ItemStack> list) {
        for (final EnumType enumType : EnumType.values()) {
            list.add(new ItemStack(this, 1, enumType.ordinal()));
        }
    }

    public String getName(final ItemStack stack) {
        final int metadata = stack.getMetadata();

        return EnumType.values()[metadata].getName();
    }

    public enum EnumType implements IStringSerializable {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O;

        @Override
        public String getName() {
            return this.name();
        }
    }
}
