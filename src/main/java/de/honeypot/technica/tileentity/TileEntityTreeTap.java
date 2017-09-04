package de.honeypot.technica.tileentity;

import de.honeypot.technica.block.BlockTreeTap;
import de.honeypot.technica.init.ModBlocks;
import de.honeypot.technica.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Chloroplast on 04.09.2017.
 */
public class TileEntityTreeTap extends TileEntity implements IInventory{

    private ItemStack content = ItemStack.EMPTY;
    private String customName;
    private Item lastItem;

    public byte load = 0;

    public TileEntityTreeTap() {
        GameRegistry.registerTileEntity(TileEntityTreeTap.class, "tree_trap");
    }


    public String getCustomName() {
        return this.customName;
    }
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    /* naming */
    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.tree_tap";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return content==null || content.getCount()==0;
    }

    /* working on the inventory */
    @Override
    public ItemStack getStackInSlot(int index) {

        if (index != 0) {
            return null;
        }

        return content==null ? ItemStack.EMPTY : content;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        if(index != 0){
            return null;
        }

        if (content.getCount() <= count) {

            ItemStack stack = content;
            this.setInventorySlotContents(index, ItemStack.EMPTY);
            this.markDirty();
            updateBlockState();
            return stack;

        } else {

            ItemStack stack = content.splitStack(count);

            if (content.getCount() <= 0) {
                this.setInventorySlotContents(index, ItemStack.EMPTY);
            }

            this.markDirty();
            updateBlockState();
            return stack;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if(index != 0){
            return null;
        }
        ItemStack stack = content;
        content = ItemStack.EMPTY;
        updateBlockState();
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if (index != 0) {
            return;
        }

        if (stack != null && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (stack != null && stack.getCount() == 0) {
            stack = ItemStack.EMPTY;
        }
        content = stack;
        this.markDirty();
        updateBlockState();
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index != 0){
            return false;
        }
        if(content!=null && (content.getCount()>0)){
            return false;
        }
        return (stack.getItem() == Items.BUCKET);
    }

    /* syncing */

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
        content = ItemStack.EMPTY;
        updateBlockState();
    }

    /* nbt */


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        byte state = 0;
        if(content != null){

            if(content.getItem() == Items.BUCKET){
                state = 1;
            }else if(content.getItem() == ModItems.BUCKET_RESIN){
                state = 2;
            }else{
                state = 0;
            }
        }else{
            state = 0;
        }

        nbt.setByte("State", state);
        nbt.setByte("Load", load);

        if (this.hasCustomName()) {
            nbt.setString("CustomName", this.getCustomName());
        }
        updateBlockState();
        return nbt;
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        load = nbt.getByte("Load");
        byte state = nbt.getByte("State");
        if(state == 1){
            content = new ItemStack(Items.BUCKET, 1);
        }else if(state == 2) {
            content = new ItemStack(ModItems.BUCKET_RESIN, 1);
        }else{
            content = ItemStack.EMPTY;
        }

        if (nbt.hasKey("CustomName", 8)) {
            this.setCustomName(nbt.getString("CustomName"));
        }
    }

    public ItemStack getContent() {
        return content;
    }

    private void updateBlockState() {
        if(content.getItem() == Items.BUCKET){
            getWorld().setBlockState(getPos(), getWorld().getBlockState(getPos()).withProperty(BlockTreeTap.BLOCK_STATE, BlockTreeTap.ENUM_STATE.BUCKET), 10);
        }else if(content.getItem() == ModItems.BUCKET_RESIN){
            getWorld().setBlockState(getPos(), getWorld().getBlockState(getPos()).withProperty(BlockTreeTap.BLOCK_STATE, BlockTreeTap.ENUM_STATE.RESIN), 10);
        }else {
            getWorld().setBlockState(getPos(), getWorld().getBlockState(getPos()).withProperty(BlockTreeTap.BLOCK_STATE, BlockTreeTap.ENUM_STATE.NONE), 10);
        }

    }
}
