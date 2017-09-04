package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModBlocks {
    public static BlockLogRubber        LOG_RUBBER;
    public static BlockLogRubberLiving  LOG_RUBBER_LIVING;
    public static BlockSaplingRubber    SAPLING_RUBBER;
    public static BlockLeavesRubber     LEAVES_RUBBER;
    public static BlockTreeTap          TREE_TAP;
    public static BlockOre              ORE;

    private static ArrayList<Block> blocks = new ArrayList<Block>(20);

    public static void registerBlock(Block block) {
        blocks.add(block);
    }

    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOG_RUBBER = new BlockLogRubber();
            LOG_RUBBER_LIVING = new BlockLogRubberLiving();
            SAPLING_RUBBER = new BlockSaplingRubber();
            LEAVES_RUBBER = new BlockLeavesRubber();
            ORE = new BlockOre(Material.ROCK, "ore");
            TREE_TAP = new BlockTreeTap();

            blocks.forEach(event.getRegistry()::register);
        }
    }
}
