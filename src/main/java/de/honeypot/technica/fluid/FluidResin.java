package de.honeypot.technica.fluid;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by Chloroplast on 03.09.2017.
 */
public class FluidResin extends Fluid {


    public FluidResin() {
        super("resin_still", new ModelResourceLocation("resin_still", "item"), new ModelResourceLocation("resin_flow", "item"));
    }
}
