package de.honeypot.technica.client.render;

import de.honeypot.technica.init.ModItems;
import de.honeypot.technica.tile.conveyor.TileConveyorBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.animation.FastTESR;

import java.util.List;

public class TESRConveyor extends FastTESR<TileConveyorBase> {
    private static RenderItem renderItem;

    @Override
    public void renderTileEntityFast(TileConveyorBase te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        //Todo: 14.09.2017 Move to init?
        if (renderItem == null) renderItem = Minecraft.getMinecraft().getRenderItem();

        ItemStack stack = new ItemStack(ModItems.COIL_COPPER);
        IBakedModel model = renderItem.getItemModelWithOverrides(stack, null, null);

        //TRSRTransformation transformation = new TRSRTransformation()

        List<BakedQuad> quads = model.getQuads(null, null, 0);
        for (BakedQuad quad : quads) {
            buffer.addVertexData(quad.getVertexData());
        }
    }
}