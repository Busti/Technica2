package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Technica.MODID)
public class ModModelManager {
    @SubscribeEvent
    public static void registerAllModels(final ModelRegistryEvent event) {

    }
}
