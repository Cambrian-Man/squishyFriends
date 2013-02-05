package squishyFriends.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySlimePen extends TileEntity implements IInventory {
	private ItemStack core;
	private ItemStack whistle;
	private ItemStack[] trainingItems;
	private ItemStack appearanceItem;

	public TileEntitySlimePen() {
		trainingItems = new ItemStack[5];
		
		
	}

	@Override
	public int getSizeInventory() {
		return 8;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		switch (slot) {
		case 0:
			return core;
		case 6:
			return whistle;
		case 7:
			return appearanceItem;
		default:
			return trainingItems[slot];
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		
		
		if (stack != null) {
			if (stack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			}
			else {
				stack = stack.splitStack(amount);
				
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		switch (slot) {
		case 0:
			core = stack;
			break;
		case 6:
			whistle = stack;
			break;
		case 7:
			appearanceItem = stack;
			break;
		default:
			trainingItems[slot] = stack;
			break;
		}
	}

	@Override
	public String getInvName() {
		return "SlimePenInventory";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		core = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("core"));
		whistle = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("whistle"));
		appearanceItem = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("appearanceItem"));
		
		NBTTagList items = tag.getTagList("items");
		for (int i = 0; i < items.tagCount(); i++) {
			trainingItems[i] = ItemStack.loadItemStackFromNBT((NBTTagCompound) items.tagAt(i));
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		if (core != null) {
			tag.setCompoundTag("core", core.writeToNBT(new NBTTagCompound()));
		}
		
		if (whistle != null) {
			tag.setCompoundTag("whistle", whistle.writeToNBT(new NBTTagCompound()));
		}
		
		if (appearanceItem != null) {
			tag.setCompoundTag("appearance", appearanceItem.writeToNBT(new NBTTagCompound()));
		}
		
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < trainingItems.length; i++) {
			if (trainingItems[i] != null) {
				NBTTagCompound item = new NBTTagCompound();
				trainingItems[i].writeToNBT(item);
				items.appendTag(item);
			}
		}
		
		tag.setTag("Items", items);
	}

}
