package de.honeypot.technica.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockVariants<T extends Enum<T> & IStringSerializable> extends BlockBase {
    public final IProperty<T> VARIANT;
    public final T[] values;

    public BlockVariants(Material material, String name, Class<T> clazz) {
        super(material, name);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        values = clazz.getEnumConstants();
        VARIANT = PropertyEnum.create("variant", clazz.getEnumConstants()[0].getDeclaringClass());
    }

    protected T getVariantFromMeta(int meta) {
        return meta >= values.length ? values[0] : values[meta];
    }

    protected IBlockState getStateFromVariant(T variant) {
        return getDefaultState().withProperty(VARIANT, variant);
    }

    public String getName(final ItemStack stack) {
        final int metadata = stack.getMetadata();
        return Integer.toString(metadata);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        Class<T> val = VARIANT.getValueClass();
        return getDefaultState().withProperty(VARIANT, getVariantFromMeta(meta));
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
        for (T state: values) {
            list.add(new ItemStack(this, 1, state.ordinal()));
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(state));
    }

    @Override
    public Item getBlockItem() {
        return new ItemMultiTexture(this, this, this::getName);
    }
}
