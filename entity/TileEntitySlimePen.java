package squishyFriends.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class TileEntitySlimePen extends TileEntity implements IInventory {
	private ItemStack[] penContents;

	public TileEntitySlimePen() {
		 penContents = new ItemStack[8];
	}

	@Override
	public int getSizeInventory() {
		return 8;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return penContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		
		
		if (penContents[slot] != null) {
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
		penContents[slot] = stack;

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

}
