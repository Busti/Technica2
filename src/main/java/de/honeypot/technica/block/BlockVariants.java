package de.honeypot.technica.block;

import de.honeypot.technica.item.ItemBlockVariants;
import de.honeypot.technica.util.IEnumVariants;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockVariants<E extends Enum<E> & IEnumVariants<E>> extends BlockBase {
    public final IProperty<E> propVariant;
    private final E[] variants;

    private static PropertyEnum<? extends Enum<?>> tmp;

    public BlockVariants(Material material, String name, PropertyEnum<E> propVariant, Class<E> clazz) {
        super(preInit(material, propVariant), name);
        variants = clazz.getEnumConstants();
        this.propVariant = propVariant;
    }

    public BlockVariants(Material material, String name, Class<E> clazz) {
        this(material, name, PropertyEnum.create("variant", clazz.getEnumConstants()[0].getDeclaringClass()), clazz);
    }

    /**
     * Internal method used for populating a temporary PropertyEnum object used in {@link #createBlockState()}
     * @param material The material is being passed through.
     * @param propVariant The property to be stored in the temporary variable
     * @return The passed through material.
     */
    private static Material preInit(Material material, PropertyEnum<?> propVariant) {
        tmp = propVariant;
        return material;
    }

    private E byMetadata(int meta) {
        return variants[0].byMetadata(meta);
    }

    protected E getVariantFromMeta(int meta) {
        return meta < 0 || meta >= variants.length ? byMetadata(0) : byMetadata(meta);
    }

    public int getMetaFromVariant(E variant) {
        return variant.getMetadata();
    }

    protected IBlockState getStateFromVariant(E variant) {
        return getDefaultState().withProperty(this.propVariant, variant);
    }

    protected E getVariantFromState(IBlockState state) {
        return state.getValue(propVariant);
    }

    protected String getName(final ItemStack stack) {
        final int metadata = stack.getMetadata();
        return variants[metadata].getName();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, propVariant == null ? tmp : propVariant);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        Class<E> val = propVariant.getValueClass();
        return getDefaultState().withProperty(propVariant, getVariantFromMeta(meta));
    }

    @Override
    public int getMetaFromState(final IBlockState state) {
        return state.getValue(propVariant).getMetadata();
    }

    @Override
    public int damageDropped(final IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(final CreativeTabs tab, final NonNullList<ItemStack> list) {
        for (E state : variants) {
            list.add(new ItemStack(this, 1, state.getMetadata()));
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(state));
    }

    @Override
    public Item getBlockItem() {
        return new ItemBlockVariants<E>(this, this::getName, variants);
    }
}
