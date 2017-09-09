package de.honeypot.technica.block;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.tile.TileConveyorBase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BlockConveyor extends BlockContainer {
    public static final String CONVEYOR = "conveyor";

    public static final IProperty DIRECTION = PropertyDirection.create("dir", EnumFacing.Plane.HORIZONTAL);
    public static final Map<EnumFacing, IProperty> CONNECTED = new HashMap<>();
    public static final IProperty HAS_MOTOR = PropertyBool.create("has_motor");

    static {
        for (EnumFacing facing: EnumFacing.Plane.HORIZONTAL.facings()) {
            CONNECTED.put(facing, PropertyBool.create("connected_" + facing.getName()));
        }
    }

    protected BlockConveyor() {
        super(Material.IRON);
        setRegistryName("conveyor");
        setUnlocalizedName("conveyor");
        ModBlocks.registerBlock(this);
        setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);

        ItemBlock item = new ItemBlock(this);
        item.setRegistryName(CONVEYOR);
        item.setUnlocalizedName(CONVEYOR);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        ModItems.registerItem(item);

        setDefaultState(getDefaultState()
                .withProperty(DIRECTION, EnumFacing.NORTH)
                .withProperty(CONNECTED.get(EnumFacing.NORTH), false)
                .withProperty(CONNECTED.get(EnumFacing.EAST), false)
                .withProperty(CONNECTED.get(EnumFacing.SOUTH), false)
                .withProperty(CONNECTED.get(EnumFacing.WEST), false)
                .withProperty(HAS_MOTOR, false)
        );
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileConveyorBase();
    }
}
