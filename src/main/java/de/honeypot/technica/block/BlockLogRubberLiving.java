package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.util.ModNBTUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Chloroplast on 31.08.17.
 */
public class BlockLogRubberLiving extends Block {

    public enum CUT_DIRECTION implements IStringSerializable {
        NORTH, EAST, SOUTH, WEST;


        @Override
        public String getName() {
            return this.name().toLowerCase();
        }
    }

    public enum CUT_STATUS implements IStringSerializable {
        NONE, FRESH, READY, DRY;


        @Override
        public String getName() {
            return this.name().toLowerCase();
        }
    }

    public final static String RUBBER_LOG_LIVING = "log_rubber_living";
    public final static PropertyEnum<CUT_DIRECTION> LOG_DIRECTION = PropertyEnum.<CUT_DIRECTION>create("dir", CUT_DIRECTION.class);
    public final static PropertyEnum<CUT_STATUS> LOG_STATUS = PropertyEnum.<CUT_STATUS>create("status", CUT_STATUS.class);

    public BlockLogRubberLiving(){

        super(Material.WOOD);

        setHardness(3.5f);
        setSoundType(SoundType.WOOD);
        setTickRandomly(true);

        setRegistryName(RUBBER_LOG_LIVING);
        setUnlocalizedName(RUBBER_LOG_LIVING);
        ModBlocks.registerBlock(this);
        setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);


        ItemBlock item = new ItemBlock(this);
        item.setRegistryName(RUBBER_LOG_LIVING);
        item.setUnlocalizedName(RUBBER_LOG_LIVING);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        ModItems.registerItem(item);
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_DIRECTION, LOG_STATUS);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(LOG_STATUS).ordinal() << 2) | state.getValue(LOG_DIRECTION).ordinal();
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(LOG_DIRECTION, CUT_DIRECTION.values()[meta & 0x3])
                .withProperty(LOG_STATUS,    CUT_STATUS.values()[meta >> 2]);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        CUT_DIRECTION dir = state.getValue(LOG_DIRECTION);
        CUT_STATUS status = state.getValue(LOG_STATUS);

        if(playerIn.isSpectator()){
            return false;
        }

        if(state.getValue(LOG_STATUS) == CUT_STATUS.NONE && ModNBTUtil.getDamageFromItem(playerIn.getHeldItem(hand)) > 1){
            switch (facing){
                case UP:
                case DOWN:
                    return false;
                case NORTH:
                    dir = CUT_DIRECTION.NORTH;
                    break;
                case EAST:
                    dir = CUT_DIRECTION.EAST;
                    break;
                case WEST:
                    dir = CUT_DIRECTION.WEST;
                    break;
                case SOUTH:
                    dir = CUT_DIRECTION.SOUTH;
            }
            worldIn.setBlockState(pos, getDefaultState().withProperty(LOG_DIRECTION, dir).withProperty(LOG_STATUS, CUT_STATUS.FRESH), 10);
            return true;
        }

        if(state.getValue(LOG_STATUS) == CUT_STATUS.READY && playerIn.getHeldItem(hand).getItem() == Items.BOWL) {

            switch (facing){
                case UP:
                case DOWN:
                    return false;
                case NORTH:
                    if(state.getValue(LOG_DIRECTION) != CUT_DIRECTION.NORTH) return false;
                    break;
                case EAST:
                    if(state.getValue(LOG_DIRECTION) != CUT_DIRECTION.EAST) return false;
                    break;
                case WEST:
                    if(state.getValue(LOG_DIRECTION) != CUT_DIRECTION.WEST) return false;
                    break;
                case SOUTH:
                    if(state.getValue(LOG_DIRECTION) != CUT_DIRECTION.SOUTH) return false;
            }

            status = CUT_STATUS.FRESH;
            worldIn.setBlockState(pos, getDefaultState().withProperty(LOG_DIRECTION, dir).withProperty(LOG_STATUS, CUT_STATUS.FRESH), 10);

            //remove one from current item (hopefully bowl)
            playerIn.getHeldItem(hand).setCount( playerIn.getHeldItem(hand).getCount() -1 );

            //give one result item
            ItemStack result = new ItemStack(ModItems.BOWL_RESIN, 1);
            boolean canGive = playerIn.addItemStackToInventory(result);

            if(!canGive && !playerIn.isCreative() && !worldIn.isRemote){
                worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY + playerIn.eyeHeight, playerIn.posZ, result));
            }
            return true;

        }

        return false;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {

        if(state.getValue(LOG_STATUS) == CUT_STATUS.FRESH){
            worldIn.setBlockState(pos, state.withProperty(LOG_STATUS, CUT_STATUS.READY), 10);
        } else if(state.getValue(LOG_STATUS) == CUT_STATUS.READY){
            worldIn.setBlockState(pos, state.withProperty(LOG_STATUS, CUT_STATUS.DRY), 10);
        }else if(state.getValue(LOG_STATUS) == CUT_STATUS.DRY){
            worldIn.setBlockState(pos, state.withProperty(LOG_STATUS, CUT_STATUS.NONE), 10);
        }

    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.LOG_RUBBER);
    }

}