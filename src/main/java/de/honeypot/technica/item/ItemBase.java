package de.honeypot.technica.item;

import de.honeypot.technica.Technica;
import de.honeypot.technica.init.ModItems;
import net.minecraft.item.Item;

/**
 * @author Chloroplast
 *
 */

public class ItemBase extends Item {
    public ItemBase(String name){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        this.setCreativeTab(Technica.CREATIVE_TAB_TECHNICA);

        ModItems.registerItem(this);
    }
}
