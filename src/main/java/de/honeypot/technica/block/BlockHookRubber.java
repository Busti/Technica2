package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.util.ModEnum;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Chloroplast on 02.09.2017.
 */
public class BlockHookRubber extends Block {

    public final static String HOOK_RUBBER = "hook_rubber";
    //protected static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);


    public final static PropertyEnum<ModEnum.ENUM_DIRECTION> BLOCK_DIR = PropertyEnum.<ModEnum.ENUM_DIRECTION>create("dir", ModEnum.ENUM_DIRECTION.class);

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

    /*public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDS;
    }*/

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BLOCK_DIR).ordinal();
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(BLOCK_DIR, ModEnum.ENUM_DIRECTION.values()[meta & 0x3]);
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

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BLOCK_DIR);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

        switch(facing) {
            case DOWN:
            case UP:
                return getDefaultState();
            case NORTH:
                return getDefaultState().withProperty(BLOCK_DIR, ModEnum.ENUM_DIRECTION.SOUTH);
            case EAST:
                return getDefaultState().withProperty(BLOCK_DIR, ModEnum.ENUM_DIRECTION.WEST);
            case SOUTH:
                return getDefaultState().withProperty(BLOCK_DIR, ModEnum.ENUM_DIRECTION.NORTH);
            case WEST:
                return getDefaultState().withProperty(BLOCK_DIR, ModEnum.ENUM_DIRECTION.EAST);
            default:
                return getDefaultState();
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {

        if(!worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)){
            return false;
        }

        IBlockState blockAimed;
        ModEnum.ENUM_DIRECTION aimDir;

        switch(side) {
            case DOWN:
            case UP:
                return false;
            case NORTH:
                aimDir = ModEnum.ENUM_DIRECTION.NORTH;
                blockAimed = worldIn.getBlockState(pos.south());
                break;
            case EAST:
                aimDir = ModEnum.ENUM_DIRECTION.EAST;
                blockAimed = worldIn.getBlockState(pos.west());
                break;
            case SOUTH:
                aimDir = ModEnum.ENUM_DIRECTION.SOUTH;
                blockAimed = worldIn.getBlockState(pos.north());
                break;
            case WEST:
                aimDir = ModEnum.ENUM_DIRECTION.WEST;
                blockAimed = worldIn.getBlockState(pos.east());
                break;
            default:
                return false;
        }

        if(blockAimed.getBlock() != ModBlocks.LOG_RUBBER_LIVING){
            return false;
        }

        if(blockAimed.getValue(BlockLogRubberLiving.LOG_STATUS) != BlockLogRubberLiving.CUT_STATUS.READY){
            return false;
        }


        return (blockAimed.getValue(BlockLogRubberLiving.LOG_DIRECTION) == aimDir);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        BlockPos posAimed = null;
        switch(state.getValue(BLOCK_DIR)){
            case NORTH: posAimed = pos.north(); break;
            case EAST:  posAimed = pos.east();  break;
            case SOUTH: posAimed = pos.south(); break;
            case WEST:  posAimed = pos.west();  break;
        }

        worldIn.setBlockState(posAimed,
                ModBlocks.LOG_RUBBER_LIVING.getDefaultState()
                        .withProperty(BlockLogRubberLiving.LOG_DIRECTION, ModEnum.ENUM_DIRECTION.EAST)
                        .withProperty(BlockLogRubberLiving.LOG_STATUS, BlockLogRubberLiving.CUT_STATUS.NONE), 10);



    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        BlockPos aim = null;
        ModEnum.ENUM_DIRECTION dir = null;

        switch(state.getValue(BLOCK_DIR)){
            case NORTH: aim = pos.north(); break;
            case EAST:  aim = pos.east();  break;
            case SOUTH: aim = pos.south(); break;
            case WEST:  aim = pos.west();  break;
        }

        if(worldIn.getBlockState(aim).getBlock() != ModBlocks.LOG_RUBBER_LIVING){
            return;
        }

        worldIn.setBlockState(aim,
                ModBlocks.LOG_RUBBER_LIVING.getDefaultState()
                        .withProperty(BlockLogRubberLiving.LOG_DIRECTION, state.getValue(BLOCK_DIR).getMirrored())
                        .withProperty(BlockLogRubberLiving.LOG_STATUS, BlockLogRubberLiving.CUT_STATUS.DRY), 10);

    }
    /**
     * Called from rubberLog if this Block is connected to it
     */
    public void dropFromMissingSource(World worldIn, IBlockState state, BlockPos pos){
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, new ItemStack(Item.getItemFromBlock(ModBlocks.HOOK_RUBBER))));
        worldIn.setBlockToAir(pos);
    }
}
