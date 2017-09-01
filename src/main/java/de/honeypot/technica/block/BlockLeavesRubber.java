package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gast2 on 01.09.17.
 */
public class BlockLeavesRubber extends Block implements IShearable {

    public final static int NEEDED_WOOD_RANGE = 5;
    public final static String LEAVES_RUBBER = "leaves_rubber";

    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    protected boolean leavesFancy;

    public BlockLeavesRubber() {

        super(Material.LEAVES);
        setTickRandomly(true);
        setHardness(0.2F);
        setLightOpacity(1);
        setSoundType(SoundType.PLANT);

        setRegistryName(LEAVES_RUBBER);
        setUnlocalizedName(LEAVES_RUBBER);
        ModBlocks.registerBlock(this);
        setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);

        setDefaultState(getDefaultState().withProperty(DECAYABLE, false));


        ItemBlock item = new ItemBlock(this);
        item.setRegistryName(LEAVES_RUBBER);
        item.setUnlocalizedName(LEAVES_RUBBER);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        ModItems.registerItem(item);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP) && rand.nextInt(15) == 1) {
            double d0 = pos.getX() + rand.nextFloat();
            double d1 = pos.getY() - 0.05;
            double d2 = pos.getZ() + rand.nextFloat();
            worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0, 0, 0);
        }
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 20;
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.SAPLING_RUBBER);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return leavesFancy ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos){ return true; }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<ItemStack>(1);
        list.add(new ItemStack(Item.getItemFromBlock(ModBlocks.LEAVES_RUBBER), 1));
        return list;
    }

    @Override public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos){ return true; }

    @Override
    public void beginLeavesDecay(IBlockState state, World world, BlockPos pos) {

        if(!state.getValue(DECAYABLE)){
            return;
        }


        int half = NEEDED_WOOD_RANGE/2;
        boolean isWoodSupported = false;

        for(int dx = -half; dx < half+1; ++dx){
            for(int dy = -half; dy < half+1; ++dy){
                for(int dz = -half; dz < half+1; ++dz){

                    BlockPos otherPos = new BlockPos(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                    IBlockState other = world.getBlockState(otherPos);
                    if(other.getBlock().isWood(world, otherPos)){
                        isWoodSupported = true;
                        dx = dy = dz = half;
                    }
                }
            }
        }

        if(!isWoodSupported) {
            world.setBlockToAir(pos);
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this.getItemDropped(state, world.rand, 0), this.quantityDropped(world.rand))));
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, DECAYABLE);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {

        beginLeavesDecay(state, worldIn, pos);

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(DECAYABLE) ? 1 : 0;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(DECAYABLE, meta == 1);
    }




}
