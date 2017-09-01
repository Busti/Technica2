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
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/**
 * @author Chloroplast
 */

@GameRegistry.ObjectHolder(Technica.MODID)
public class ModItems {
    public static Item INGOT_COPPER;
    public static Item WIRE_IRON;
    public static Item WIRE_COPPER;
    public static Item COIL_COPPER;
    public static Item ROD_IRON;
    public static Item ELECTRIC_ENGINE;
    public static Item RUBBER;
    public static Item RUBBER_BAND;
    public static Item BOWL_RESIN;
    public static Item BOWL_RESIN_DRY;

    private static ArrayList<Item> items = new ArrayList<Item>(20);
    private static ArrayList<Item> itemsWithoutModel = new ArrayList<Item>(20);

    public static void registerItem(Item item) {
        items.add(item);
    }

    public static void registerItemWithoutModel(Item item) {
        itemsWithoutModel.add(item);
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
            INGOT_COPPER = new ItemGeneric("ingot_copper");
            WIRE_IRON = new ItemGeneric("wire_iron");
            WIRE_COPPER = new ItemGeneric("wire_copper");
            COIL_COPPER = new ItemGeneric("coil_copper");
            ROD_IRON = new ItemGeneric("rod_iron");
            ELECTRIC_ENGINE = new ItemGeneric("electric_engine");
            RUBBER = new ItemGeneric("rubber");
            RUBBER_BAND = new ItemGeneric("rubber_band");
            BOWL_RESIN = new ItemGeneric("bowl_resin");
            BOWL_RESIN_DRY = new ItemGeneric("bowl_resin_dry");

            items.forEach(event.getRegistry()::register);
            itemsWithoutModel.forEach(event.getRegistry()::register);
        }

        public static void onPreInit() {
            Technica.logger.debug("Registering ore-dictionary entries.");

            OreDictionary.registerOre("ingotCopper", INGOT_COPPER);
            OreDictionary.registerOre("wireIron", WIRE_IRON);
            OreDictionary.registerOre("wireCopper", WIRE_COPPER);
            OreDictionary.registerOre("rodIron", ROD_IRON);
            OreDictionary.registerOre("rubber", RUBBER);
        }
    }
}