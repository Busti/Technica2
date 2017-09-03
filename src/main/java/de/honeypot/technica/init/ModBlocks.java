package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.block.BlockLogRubber;
import de.honeypot.technica.block.BlockLogRubberLiving;
import de.honeypot.technica.block.BlockOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModBlocks {
    public static Block LOG_RUBBER;
    public static Block LOG_RUBBER_LIVING;
    public static BlockOre ORE;

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
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            ORE = new BlockOre(Material.ROCK, "ore");

            blocks.forEach(event.getRegistry()::register);
        }
    }
}
