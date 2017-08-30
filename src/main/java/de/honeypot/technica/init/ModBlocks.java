package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.block.CopperOre;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModBlocks {
    public static final Block copperOre = new CopperOre().setRegistryName(Technica.MODID, "copperOre").setUnlocalizedName("copperOre");

    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(copperOre);
        }

        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            event.getRegistry().register(new ItemBlock(copperOre).setRegistryName("copperOre"));
        }
    }
}
