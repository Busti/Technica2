package de.honeypot.technica.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockVariants<T extends Enum<T> & IEnumVariants & IStringSerializable> extends BlockBase {
    public final IProperty<T> VARIANT;

    public BlockVariants(Material material, String name, Class<T> clazz) {
        super(material, name);
        VARIANT = PropertyEnum.create("variant", clazz);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        Class<T> val = VARIANT.getValueClass();
        Object foo = val::new;
        return getDefaultState().withProperty(VARIANT, );
    }

    @Override
    public int getMetaFromState(final IBlockState state) {
        return state.getValue(VARIANT);
    }

    @Override
    public int damageDropped(final IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(final CreativeTabs tab, final NonNullList<ItemStack> list) {
        for (int i = 0; i < subtypes; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    public String getName(final ItemStack stack) {
        final int metadata = stack.getMetadata();
        return Integer.toString(metadata);
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
