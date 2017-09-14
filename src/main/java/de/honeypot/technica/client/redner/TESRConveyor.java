package de.honeypot.technica.client.redner;

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
    private static final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

    @Override
    public void renderTileEntityFast(TileConveyorBase te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        ItemStack stack = new ItemStack(ModItems.COIL_COPPER);
        IBakedModel model = renderItem.getItemModelWithOverrides(stack, null, null);
        List<BakedQuad> quads = model.getQuads(null, null, 0);
        for (int i = 0; i < quads.size(); ++i) {
            buffer.addVertexData(quads.get(i).getVertexData());
        }
    }
}
