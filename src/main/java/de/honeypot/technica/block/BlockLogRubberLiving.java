package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.util.ModEnum;
import de.honeypot.technica.util.ModNBTUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Chloroplast on 31.08.17.
 */
public class BlockLogRubberLiving extends Block {

    public enum CUT_STATUS implements IStringSerializable {
        NONE, FRESH, READY, DRY;


        @Override
        public String getName() {
            return this.name().toLowerCase();
        }
    }

    public final static String RUBBER_LOG_LIVING = "log_rubber_living";
    public final static PropertyEnum<ModEnum.ENUM_DIRECTION> LOG_DIRECTION = PropertyEnum.<ModEnum.ENUM_DIRECTION>create("dir", ModEnum.ENUM_DIRECTION.class);
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
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 20;
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
                .withProperty(LOG_DIRECTION, ModEnum.ENUM_DIRECTION.values()[meta & 0x3])
                .withProperty(LOG_STATUS,    CUT_STATUS.values()[meta >> 2]);
    }

    public boolean isWood(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        ModEnum.ENUM_DIRECTION dir = state.getValue(LOG_DIRECTION);
        CUT_STATUS status = state.getValue(LOG_STATUS);

        if(playerIn.isSpectator()){
            return false;
        }

        if(state.getValue(LOG_STATUS) == CUT_STATUS.NONE && ModNBTUtil.getDamageFromItem(playerIn.getHeldItem(hand)) > 1){


            if(state.getValue(LOG_DIRECTION) != ModEnum.ENUM_DIRECTION.NORTH){
                return false;
            }


            switch (facing){
                case UP:
                case DOWN:
                    return false;
                case NORTH:
                    dir = ModEnum.ENUM_DIRECTION.NORTH;
                    break;
                case EAST:
                    dir = ModEnum.ENUM_DIRECTION.EAST;
                    break;
                case WEST:
                    dir = ModEnum.ENUM_DIRECTION.WEST;
                    break;
                case SOUTH:
                    dir = ModEnum.ENUM_DIRECTION.SOUTH;
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
                    if(state.getValue(LOG_DIRECTION) != ModEnum.ENUM_DIRECTION.NORTH) return false;
                    break;
                case EAST:
                    if(state.getValue(LOG_DIRECTION) != ModEnum.ENUM_DIRECTION.EAST) return false;
                    break;
                case WEST:
                    if(state.getValue(LOG_DIRECTION) != ModEnum.ENUM_DIRECTION.WEST) return false;
                    break;
                case SOUTH:
                    if(state.getValue(LOG_DIRECTION) != ModEnum.ENUM_DIRECTION.SOUTH) return false;
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
            worldIn.setBlockState(pos, state.withProperty(LOG_STATUS, CUT_STATUS.NONE).withProperty(LOG_DIRECTION, ModEnum.ENUM_DIRECTION.NORTH), 10);
        }

    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.LOG_RUBBER);
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {


        if(state.getValue(LOG_STATUS) != CUT_STATUS.NONE){
            return;
        }

        if(state.getValue(LOG_DIRECTION) != ModEnum.ENUM_DIRECTION.EAST){
            return;
        }
        //check block in the north
        IBlockState temp = worldIn.getBlockState(pos.north());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.SOUTH){
                ModBlocks.HOOK_RUBBER.dropFromMissingSource(worldIn, temp, pos.north());
                return;
            }
        }
        //check block in the east
        temp = worldIn.getBlockState(pos.east());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.WEST){
                ModBlocks.HOOK_RUBBER.dropFromMissingSource(worldIn, temp, pos.east());
                return;
            }
        }
        //check block in the south
        temp = worldIn.getBlockState(pos.south());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.NORTH){
                ModBlocks.HOOK_RUBBER.dropFromMissingSource(worldIn, temp, pos.south());
                return;
            }
        }
        //check block in the west
        temp = worldIn.getBlockState(pos.west());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.EAST){
                ModBlocks.HOOK_RUBBER.dropFromMissingSource(worldIn, temp, pos.west());
                return;
            }
        }

    }

    @Override
    @SuppressWarnings("deprecation")
    public EnumPushReaction getMobilityFlag(IBlockState state) {

        if(state.getValue(LOG_STATUS) != CUT_STATUS.NONE){
            return EnumPushReaction.NORMAL;
        }

        if(state.getValue(LOG_DIRECTION) != ModEnum.ENUM_DIRECTION.EAST){
            return EnumPushReaction.NORMAL;
        }
        return EnumPushReaction.BLOCK;
    }

    /**
     *
     * @return weather a tree tap is facing at this block or not
     */
    private boolean isSource(World worldIn, BlockPos pos){
        //check block in the north
        IBlockState temp = worldIn.getBlockState(pos.north());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.SOUTH){
                return true;
            }
        }
        //check block in the east
        temp = worldIn.getBlockState(pos.east());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.WEST){
                return true;
            }
        }
        //check block in the south
        temp = worldIn.getBlockState(pos.south());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.NORTH){
                return true;
            }
        }
        //check block in the west
        temp = worldIn.getBlockState(pos.west());
        if(temp.getBlock() == ModBlocks.HOOK_RUBBER){
            if(temp.getValue(BlockTreeTap.BLOCK_DIR) == ModEnum.ENUM_DIRECTION.EAST){
                return true;
            }
        }

        return false;
    }

}
