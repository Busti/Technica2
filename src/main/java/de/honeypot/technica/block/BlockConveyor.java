package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.tile.TileConveyorBase;
import de.honeypot.technica.util.EnumSide;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BlockConveyor extends Block {
    public static final String CONVEYOR = "conveyor";

    public static final IProperty<EnumFacing> FACING = BlockHorizontal.FACING;
    public static final Map<EnumSide, IProperty<Boolean>> CONNECTED = new HashMap<>();
    public static final IProperty<Boolean> HAS_MOTOR = PropertyBool.create("has_motor");


    static {
        for (EnumSide side : EnumSide.values()) {
            CONNECTED.put(side, PropertyBool.create(side.getName()));
        }
    }

    public BlockConveyor(String name) {
        super(Material.IRON);
        setRegistryName(name);
        setUnlocalizedName(name);
        ModBlocks.registerBlock(this);
        setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);

        ItemBlock item = new ItemBlock(this);
        item.setRegistryName(name);
        item.setUnlocalizedName(name);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        ModItems.registerItem(item);

        setDefaultState(getDefaultState()
                .withProperty(FACING, EnumFacing.NORTH)
                .withProperty(CONNECTED.get(EnumSide.BACK), false)
                .withProperty(CONNECTED.get(EnumSide.LEFT), false)
                .withProperty(CONNECTED.get(EnumSide.FRONT), false)
                .withProperty(CONNECTED.get(EnumSide.RIGHT), false)
                .withProperty(HAS_MOTOR, false)
        );

        GameRegistry.registerTileEntity(TileConveyorBase.class, name);
    }

    /* State Handling */

    @Override
    protected BlockStateContainer createBlockState() {
        IProperty[] properties = {
                FACING,
                CONNECTED.get(EnumSide.BACK),
                CONNECTED.get(EnumSide.LEFT),
                CONNECTED.get(EnumSide.FRONT),
                CONNECTED.get(EnumSide.RIGHT),
                HAS_MOTOR
        };
        return new BlockStateContainer(this, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    /* Tile Entity */

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileConveyorBase();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}
