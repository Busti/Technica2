package de.honeypot.technica.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;

public abstract class TileInventory extends TileEntity implements IInventory {
    private final ItemStack[] slots = new ItemStack[getSizeInventory()];

    @Override
    public boolean isEmpty() {
        return Arrays.stream(slots).allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return slots[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (slots[index].isEmpty())
            return ItemStack.EMPTY;

        if (slots[index].getCount() < count)
            count = getStackInSlot(index).getCount();

        ItemStack stack = slots[index].splitStack(count);

        if (slots[index].getCount() <= 0)
            slots[index] = ItemStack.EMPTY;

        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (slots[index].isEmpty())
            return ItemStack.EMPTY;

        ItemStack stack = slots[index];
        slots[index] = ItemStack.EMPTY;

        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit())
            stack.setCount(getInventoryStackLimit());

        slots[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (ItemStack slot: slots)
            slot = ItemStack.EMPTY;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
