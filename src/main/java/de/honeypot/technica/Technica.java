package de.honeypot.technica;

import de.honeypot.technica.generation.TechnicaWorldGenerator;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Chloroplast, Busti
 *
 */

@Mod(modid = Technica.MODID, version = Technica.VERSION)
public class Technica {
    public static final String MODID = "technica";
    public static final String VERSION = "1.0";

    public static final CreativeTabTechnica CREATIVE_TAB_TECHNICA = new CreativeTabTechnica("Technica");

    @EventHandler
    public void init(FMLInitializationEvent event) {

        System.out.println("Starting Technica 2");

        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        initClient(mesher);

        setupFurnaceRecipes();

        GameRegistry.registerWorldGenerator(new TechnicaWorldGenerator(), 0);
    }



    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {
        ModItems.registerModels(mesher);
    }

    private void setupFurnaceRecipes() {

        GameRegistry.addSmelting(ModBlocks.COPPER_ORE, new ItemStack(ModItems.COPPER_INGOT), 0.7f);
        GameRegistry.addSmelting(ModBlocks.RUBBER_LOG, new ItemStack(ModItems.RUBBER, 2),0.8f);
        GameRegistry.addSmelting(new ItemStack(ModItems.RUBBER_BAND, 1), new ItemStack(ModItems.RUBBER, 1),0.8f);

    }
}