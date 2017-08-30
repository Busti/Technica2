package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.item.ItemGeneric;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

/**
 * @author Chloroplast
 *
 */

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModItems {


    public static Item COPPER_COIL;
    public static Item ELECTRIC_ENGINE;

    private static boolean isFinished = false;
    private static ArrayList<Item> items = new ArrayList<Item>(20);



    public static void registerItem(Item item){

        if(isFinished){
            throw new IllegalStateException("cant add items after finishing");
        }

        items.add(item);


    }

    public static void registerModels(ItemModelMesher mesher) {

        items.forEach(item -> {

            ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelLoader.registerItemVariants(item, model);
            mesher.register(item, 0, model);

        });

    }


    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {

            COPPER_COIL     = new ItemGeneric("copper_coil");
            ELECTRIC_ENGINE = new ItemGeneric("electric_engine");

            isFinished = true;

            items.forEach(  event.getRegistry()::register  );

        }
    }
}