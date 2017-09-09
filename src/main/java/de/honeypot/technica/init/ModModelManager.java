package de.honeypot.technica.init;

import de.honeypot.technica.Technica;
import de.honeypot.technica.client.IModelRegistrator;
import de.honeypot.technica.client.MeshDefinitionFix;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Technica.MODID)
public class ModModelManager {
    @SubscribeEvent
    public static void registerAllModels(final ModelRegistryEvent event) {
        // @formatter:off
        ModItems.getItems().stream().filter(item ->   item instanceof IModelRegistrator ).forEach(item -> ((IModelRegistrator) item).registerModel());
        ModItems.getItems().stream().filter(item -> !(item instanceof IModelRegistrator)).forEach(ModModelManager::registerItemModel);
        // @formatter:on
    }

    public static void registerItemModel(Item item) {
        final ModelResourceLocation modelLocation = new ModelResourceLocation(item.getRegistryName().toString(), "item");
        ModelLoader.registerItemVariants(item, modelLocation);
        ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelLocation));
    }
}
