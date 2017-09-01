package de.honeypot.technica;

import de.honeypot.technica.generation.TechnicaWorldGenerator;
import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.init.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

/**
 * @author Chloroplast, Busti
 *
 */

@Mod(modid = Technica.MODID, version = Technica.VERSION)
public class Technica {
    public static final String MODID = "technica";
    public static final String VERSION = "1.0";

    //TODO: This is bad practice. Investigate further.
    public static Logger logger;

    public static final CreativeTabTechnica CREATIVE_TAB_TECHNICA = new CreativeTabTechnica("Technica");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Starting Technica 2");

        ModItems.RegistrationHandler.onPreInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        initClient(mesher);

        ModRecipes.setupFurnaceRecipes();

        GameRegistry.registerWorldGenerator(new TechnicaWorldGenerator(), 0);
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {
        ModItems.registerModels(mesher);
    }
}