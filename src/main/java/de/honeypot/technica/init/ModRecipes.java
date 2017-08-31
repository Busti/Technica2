package de.honeypot.technica.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void setupFurnaceRecipes() {
        GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_1, 1, 0), new ItemStack(ModItems.COPPER_INGOT), 0.7f);
        //TODO: Discuss the necessity of this.
        //GameRegistry.addSmelting(ModBlocks.RUBBER_LOG, new ItemStack(ModItems.RUBBER, 1),0.8f);
        GameRegistry.addSmelting(new ItemStack(ModItems.RUBBER_BAND, 1), new ItemStack(ModItems.RUBBER, 1),0.2f);
        GameRegistry.addSmelting(ModItems.RESIN_BOWL, new ItemStack(ModItems.DRY_RESIN_BOWL), 0);
    }
}
