package de.honeypot.technica.item;

import de.honeypot.technica.init.ModItems;
import net.minecraft.item.Item;

/**
 * @author Chloroplast
 *
 */

public class ItemGeneric extends Item {

    public ItemGeneric(String name){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        ModItems.registerItem(this);
    }
}
