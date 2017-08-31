package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.block.BlockGeneric;
import de.honeypot.technica.block.BlockOre;
import de.honeypot.technica.block.BlockRubberLog;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModBlocks {


    public static Block COPPER_ORE;
    public static Block RUBBER_LOG;

    private static boolean isFinished = false;
    private static ArrayList<Block> blocks = new ArrayList<Block>(20);


    public static void registerBlock(Block block){

        if(isFinished){
            throw new IllegalStateException("cant add blocks after finishing");
        }

        blocks.add(block);

    }


    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {



            COPPER_ORE = new BlockOre(Material.ROCK, "copper_ore");
            RUBBER_LOG = new BlockRubberLog();

            isFinished = true;

            blocks.forEach(  event.getRegistry()::register  );

        }
    }
}
