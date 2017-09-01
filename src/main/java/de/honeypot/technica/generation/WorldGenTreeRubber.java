package de.honeypot.technica.generation;

import de.honeypot.technica.block.BlockLeavesRubber;
import de.honeypot.technica.block.BlockLogRubberLiving;
import de.honeypot.technica.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by gast2 on 01.09.17.
 */
public class WorldGenTreeRubber extends WorldGenerator{

    public final static int HEIGHT = 6;

    public static void genTree(World worldIn, Random rand, BlockPos pos, boolean forceUpdate){

        int flag = forceUpdate ? 10 : 4;

        worldIn.setBlockToAir(pos);

        int actualHeight = 0;

        for(int dy = 0; dy < HEIGHT - 1; ++dy) {
            BlockPos otherPos = new BlockPos(pos.getX(), pos.getY() + dy, pos.getZ());
            IBlockState state = worldIn.getBlockState(otherPos);

            if (!state.getMaterial().isReplaceable()) {
                break;
            }

            actualHeight = dy + 1;

            worldIn.setBlockState(otherPos,
                    ModBlocks.LOG_RUBBER_LIVING.getDefaultState()
                            .withProperty(BlockLogRubberLiving.LOG_DIRECTION, BlockLogRubberLiving.CUT_DIRECTION.NORTH)
                            .withProperty(BlockLogRubberLiving.LOG_STATUS, BlockLogRubberLiving.CUT_STATUS.NONE), flag);
        }

        if(actualHeight + 1 == HEIGHT) {
            ++actualHeight;
        }

        int[][] heightsStart = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        heightsStart[0][0] += rand.nextInt(2);
        heightsStart[0][2] += rand.nextInt(2);
        heightsStart[2][0] += rand.nextInt(2);
        heightsStart[2][2] += rand.nextInt(2);

        int[][] heightsEnd = {{actualHeight, actualHeight, actualHeight},
                {actualHeight, actualHeight, actualHeight},
                {actualHeight, actualHeight, actualHeight}};

        heightsEnd[0][0] -= rand.nextInt(2);
        heightsEnd[0][2] -= rand.nextInt(2);
        heightsEnd[2][0] -= rand.nextInt(2);
        heightsEnd[2][2] -= rand.nextInt(2);

        for(int dx = 0; dx < 3; ++dx){
            for(int dz = 0; dz < 3; ++dz){
                for(int dy = heightsStart[dx][dz]; dy < heightsEnd[dx][dz]; ++dy){

                    BlockPos otherPos = new BlockPos(pos.getX() + dx - 1, pos.getY() + dy, pos.getZ() + dz - 1);
                    IBlockState state = worldIn.getBlockState(otherPos);

                    if(state.getMaterial().isReplaceable()){
                        worldIn.setBlockState(otherPos,
                                ModBlocks.LEAVES_RUBBER.getDefaultState()
                                        .withProperty(BlockLeavesRubber.DECAYABLE, true), flag);
                    }

                }
            }
        }

    }

    public static void genTree(World worldIn, Random rand, BlockPos pos){
        genTree(worldIn, rand, pos, true);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {

        if(rand.nextInt(30) != 1){
            return false;
        }

        BlockPos pos = world.getTopSolidOrLiquidBlock(position);
        Biome biome = world.getBiome(pos);

        if(biome.equals(Biomes.FOREST) || biome.equals(Biomes.PLAINS)){
            if(world.getBlockState(pos).getBlock() == Blocks.GRASS){
                genTree(world, rand, pos.up(), false);
                return true;
            }else if(world.getBlockState(pos).getMaterial().isReplaceable()){
                genTree(world, rand, pos, false);
                return true;
            }
        }

        return false;
    }
}
