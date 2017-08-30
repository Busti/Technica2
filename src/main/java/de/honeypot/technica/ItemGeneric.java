package de.honeypot.technica;

import de.honeypot.technica.init.ModItems;
import net.minecraft.item.Item;

import java.util.HashMap;

public class ItemGeneric extends Item {

    private final static HashMap<String, Item> ALREADY_KNOWN = new HashMap<String, Item>();

    private ItemGeneric(String name){

        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        ModItems.registerItem(this);
    }

    public static Item create(String name){

        String nameLower = name.toLowerCase();

        if( ALREADY_KNOWN.containsKey(nameLower) ){
            return ALREADY_KNOWN.get( nameLower );
        }

        ItemGeneric item = new ItemGeneric( name );
        ALREADY_KNOWN.put(nameLower, item);

        return item;
    }

}
