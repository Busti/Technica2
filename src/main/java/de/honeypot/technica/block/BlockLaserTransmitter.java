package de.honeypot.technica.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLaserTransmitter extends BlockBase {

	public static final IProperty FACING = BlockDirectional.FACING;

	public BlockLaserTransmitter() {
		super(Material.IRON, "laser_transmitter");
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.UP));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
