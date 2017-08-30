package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.item.ItemGeneric;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModItems {


    public static Item COPPER_COIL;


    private static boolean isFinished = false;
    private static ArrayList<Item> items = new ArrayList<Item>(20);



    public static void registerItem(Item item){

        if(isFinished){
            throw new IllegalStateException("cant add items after finishing");
        }

        items.add(item);


    }


    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {

            COPPER_COIL = ItemGeneric.create("copper_coil");

            isFinished = true;

            items.forEach(  event.getRegistry()::register  );

        }
    }
}