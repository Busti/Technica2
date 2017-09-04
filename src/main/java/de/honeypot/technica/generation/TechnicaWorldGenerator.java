package de.honeypot.technica.generation;

import de.honeypot.technica.block.BlockOre;
import de.honeypot.technica.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Chloroplast on 31.08.2017.
 */
public class TechnicaWorldGenerator implements IWorldGenerator {
    private WorldGenerator copperOreGenerator;
    private WorldGenTreeRubber rubberTreeGenerator;

    public TechnicaWorldGenerator() {
        copperOreGenerator = new WorldGenMinable(ModBlocks.ORE.getDefaultState().withProperty(ModBlocks.ORE.propVariant, BlockOre.EnumType.COPPER), 8);
        rubberTreeGenerator = new WorldGenTreeRubber();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
            runGenerator(copperOreGenerator, world, random, chunkX, chunkZ, 20, 30, 64);
            runGenerator(rubberTreeGenerator, world, random, chunkX, chunkZ, 4, 10, 20);
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }

}
