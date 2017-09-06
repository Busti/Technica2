package de.honeypot.technica.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Arrays;
import java.util.stream.StreamSupport;

public abstract class TileInventory extends TileNamable implements IInventory {
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
        Arrays.fill(slots, ItemStack.EMPTY);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList list = compound.getTagList("inventory", 10);

        clear();

        StreamSupport.stream(list.spliterator(), false)
                .filter( tag -> tag.getId() == 10)
                .map(    tag -> (NBTTagCompound) tag)
                .forEach(tag -> {
                    int slot = tag.getInteger("slot");
                    slots[slot] = new ItemStack(tag);
                });
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < slots.length; i++) {
            if (!slots[i].isEmpty()) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setInteger("slot", i);
                slots[i].writeToNBT(tag);
            }
        }

        if (list.tagCount() > 0)
            compound.setTag("inventory", list);

        return super.writeToNBT(compound);
    }
}
