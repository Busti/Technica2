package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.block.BlockRubberLog;
import de.honeypot.technica.block.BlockRubberLogLiving;
import de.honeypot.technica.block.BlockVariants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModBlocks {
    public static Block RUBBER_LOG;
    public static Block RUBBER_LOG_LIVING;
    public static Block ORE_1;

    private static ArrayList<Block> blocks = new ArrayList<Block>(20);

    public static void registerBlock(Block block) {
        blocks.add(block);
    }

    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            RUBBER_LOG = new BlockRubberLog();
            RUBBER_LOG_LIVING   = new BlockRubberLogLiving();
            ORE_1 = new BlockVariants(Material.ROCK, "multi_ore", 1).setHardness(1.5f).setResistance(10);
            ORE_1.setHarvestLevel("pickaxe", 1);

            blocks.forEach(event.getRegistry()::register);
        }
    }
}
