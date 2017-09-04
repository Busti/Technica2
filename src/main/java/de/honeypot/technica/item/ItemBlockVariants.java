package de.honeypot.technica.item;

import de.honeypot.technica.client.IModelRegistrator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;

import java.util.function.Function;

public class ItemBlockVariants<E extends Enum<E> & IStringSerializable> extends ItemBlock implements IModelRegistrator {
    private final Function<ItemStack, String> nameFunction;
    private final E[] variants;

    public ItemBlockVariants(Block block, Function<ItemStack, String> nameFunction, E[] variants) {
        super(block);
        this.nameFunction = nameFunction;
        this.variants = variants;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + this.nameFunction.apply(stack);
    }

    @Override
    public void registerModel() {
        for (E value: variants) {
            String variant = "variant=" + value.getName();
            ModelLoader.setCustomModelResourceLocation(this, value.ordinal(), new ModelResourceLocation(getRegistryName(), variant));
        }
    }
}
