package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.fluid.FluidResin;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chloroplast on 03.09.2017.
 */
@GameRegistry.ObjectHolder(Technica.MODID)
public class ModFluids {

    private static List<Fluid> fluids = new ArrayList<Fluid>();
    private static Map<Fluid, Block> fluidsBlock = new HashMap<Fluid, Block>();
    private static Map<Fluid, Item> fluidsItem = new HashMap<Fluid, Item>();

    public static Fluid FLUID_RESIN = create(new FluidResin());

    public static Fluid create(Fluid fluid) {
        fluids.add(fluid);

        Block block = new BlockFluidFinite(fluid, new MaterialLiquid(MapColor.BROWN));
        block.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        block.setRegistryName(fluid.getName());
        block.setUnlocalizedName(fluid.getName());

        fluidsBlock.put(fluid, block);

        ItemBlock item = new ItemBlock(block);
        item.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);
        item.setRegistryName(fluid.getName());
        item.setUnlocalizedName(fluid.getName());

        fluidsItem.put(fluid, item);

        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);

        return fluid;
    }

    @Mod.EventBusSubscriber(modid = Technica.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {

            fluidsItem.values().forEach(event.getRegistry()::register);
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {

            fluids.forEach(fluid -> {
                fluidsBlock.values().forEach(event.getRegistry()::register);
            });
        }
    }

}
