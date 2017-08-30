package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.block.CopperOre;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModBlocks {


    public static final Block COPPER_ORE = new CopperOre();

    private static boolean isFinished = false;
    private static ArrayList<Block> blocks = new ArrayList<Block>(20);


    public static void registerBlock(Block block){

        if(isFinished){
            throw new IllegalStateException("cant add blocks after finishing");
        }


    }


    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {

            isFinished = true;
            blocks.forEach(  event.getRegistry()::register  );

        }
    }
}
