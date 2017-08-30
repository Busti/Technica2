package de.honeypot.technica;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod.EventBusSubscriber(modid = Technica.MODID)
@Mod(modid = Technica.MODID, version = Technica.VERSION)
public class Technica {
    public static final String MODID = "technica";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Starting Technica 2");
    }
}