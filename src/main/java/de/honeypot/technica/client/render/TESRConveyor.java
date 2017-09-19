package de.honeypot.technica.client.render;

import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.tile.conveyor.TileConveyorBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TESRConveyor extends TileEntitySpecialRenderer<TileConveyorBase> {
    private static RenderItem renderItem;

    @Override
    public void render(TileConveyorBase te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (renderItem == null) renderItem = Minecraft.getMinecraft().getRenderItem();

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(.5, .5, .5);
        GlStateManager.translate(x, y + 2, z);
        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        ItemStack stack = new ItemStack(ModItems.COIL_COPPER);
        IBakedModel model = renderItem.getItemModelWithOverrides(stack, null, null);

        renderItem.renderItem(stack, model);

        GlStateManager.popMatrix();
    }
}