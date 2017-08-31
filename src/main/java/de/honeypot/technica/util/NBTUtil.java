package de.honeypot.technica.util;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.Collection;

/**
 * Created by gast2 on 31.08.17.
 */
public class NBTUtil {

    public static int getDamageFromItem(ItemStack stack){

        System.out.println("\r\n##########\r\n");

        System.out.println(stack.getAttributeModifiers(EntityEquipmentSlot.MAINHAND).keys().toString());

        Collection<AttributeModifier> attribs = stack.getAttributeModifiers(EntityEquipmentSlot.MAINHAND).get("generic.attackDamage");

        attribs.stream().filter(a -> a.getOperation()==0).mapToDouble(a -> a.getAmount()).sum();

        return 0;
    }


}
