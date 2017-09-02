package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Chloroplast on 02.09.2017.
 */
public class BlockHookRubber extends Block {

    public final static String HOOK_RUBBER = "hook_rubber";
    protected static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

    public BlockHookRubber() {
        super(Material.CIRCUITS);

        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);

        setRegistryName(HOOK_RUBBER);
        setUnlocalizedName(HOOK_RUBBER);
        ModBlocks.registerBlock(this);
        setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);


        ItemBlock item = new ItemBlock(this);
        item.setRegistryName(HOOK_RUBBER);
        item.setUnlocalizedName(HOOK_RUBBER);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        ModItems.registerItem(item);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing) {
        return BlockFaceShape.UNDEFINED;
    }
}
