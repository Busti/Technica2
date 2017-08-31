package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * Created by gast2 on 31.08.17.
 */
public class BlockRubberLogLiving extends Block {

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

    public final static String RUBBER_LOG_LIVING = "rubber_log_living";
    public final static PropertyEnum<CUT_DIRECTION> LOG_DIRECTION = PropertyEnum.<CUT_DIRECTION>create("dir", CUT_DIRECTION.class);
    public final static PropertyEnum<CUT_STATUS> LOG_STATUS = PropertyEnum.<CUT_STATUS>create("status", CUT_STATUS.class);

    public BlockRubberLogLiving(){

        super(Material.WOOD);

        setHardness(3.5f);
        setSoundType(SoundType.WOOD);

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

        if(playerIn.isSpectator()){
            return false;
        }

        if(state.getValue(LOG_STATUS) != CUT_STATUS.NONE){
            playerIn.sendMessage(new TextComponentString("cut status must be none" + worldIn.isRemote));
            return false;
        }

        int dmg = de.honeypot.technica.util.NBTUtil.getDamageFromItem(playerIn.getHeldItem(hand));



        if(true){

            return true;


        } else
        {
            return false;
        }
    }
}
