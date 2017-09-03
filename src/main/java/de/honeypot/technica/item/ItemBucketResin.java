package de.honeypot.technica.item;

import de.honeypot.technica.Technica;
import de.honeypot.technica.generation.TechnicaWorldGenerator;
import de.honeypot.technica.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Created by Chloroplast on 03.09.2017.
 */
public class ItemBucketResin extends Item {

    public final static String BUCKET_RESIN = "bucket_resin_full";

    public ItemBucketResin(int fullniss) {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);

        this.setRegistryName(BUCKET_RESIN);
        this.setUnlocalizedName(BUCKET_RESIN);

        this.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);

        ModItems.registerItem(this);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)){
            for (int i = 0; i < 16; ++i) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }





}
