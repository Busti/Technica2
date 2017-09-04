package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.util.IItemRegistrator;
import de.honeypot.technica.item.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private static List<IItemRegistrator> registrators = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();

    /**
     * Marks an IItemRegistrator for registry.
     * The {@link IItemRegistrator#registerItem()} method will be called later, when the item is needed.
     * This is mainly used to circumvent Block-Items from being registered in their constructor as some ItemBlock
     * instances need variables from the Item when they are being registered.
     * @param registrator A class that implements the IItemRegistrator interface.
     */
    public static void markForRegistry(IItemRegistrator registrator) {
        registrators.add(registrator);
    }

    /**
     * Adds the given Item to the registration list.
     * @param item The Item that will be registered.
     */
    public static void registerItem(Item item) {
        items.add(item);
    }

    /**
     * @return A list of all items that have been registered.
     */
    public static List<Item> getItems() {
        return items;
    }

    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            INGOT_COPPER = new ItemBase("ingot_copper");
            WIRE_IRON = new ItemBase("wire_iron");
            WIRE_COPPER = new ItemBase("wire_copper");
            COIL_COPPER = new ItemBase("coil_copper");
            ROD_IRON = new ItemBase("rod_iron");
            ELECTRIC_ENGINE = new ItemBase("electric_engine");
            RUBBER = new ItemBase("rubber");
            RUBBER_BAND = new ItemBase("rubber_band");
            BOWL_RESIN = new ItemBase("bowl_resin");
            BOWL_RESIN_DRY = new ItemBase("bowl_resin_dry");

            items.addAll(registrators.stream().map(IItemRegistrator::registerItem).collect(Collectors.toList()));
            items.forEach(event.getRegistry()::register);
        }

        public static void onPreInit() {
            Technica.logger.debug("Registering ore-dictionary entries.");

            /*OreDictionary.registerOre("ingotCopper", INGOT_COPPER);
            OreDictionary.registerOre("wireIron", WIRE_IRON);
            OreDictionary.registerOre("wireCopper", WIRE_COPPER);
            OreDictionary.registerOre("rodIron", ROD_IRON);
            OreDictionary.registerOre("rubber", RUBBER);*/
        }
    }
}