package de.honeypot.technica.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void setupFurnaceRecipes() {
        GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 0), new ItemStack(ModItems.INGOT_COPPER), 0.7f);
        //TODO: Discuss the necessity of this.
        //GameRegistry.addSmelting(ModBlocks.LOG_RUBBER, new ItemStack(ModItems.RUBBER, 1),0.8f);
        GameRegistry.addSmelting(ModBlocks.LOG_RUBBER, new ItemStack(Items.COAL, 1, 1), 0.4f);
        GameRegistry.addSmelting(new ItemStack(ModItems.RUBBER_BAND, 1), new ItemStack(ModItems.RUBBER, 1),0.2f);
        GameRegistry.addSmelting(ModItems.BOWL_RESIN, new ItemStack(ModItems.BOWL_RESIN_DRY), 0);
    }
}
