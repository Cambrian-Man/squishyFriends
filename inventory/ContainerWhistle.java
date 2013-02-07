package squishyFriends.inventory;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerWhistle extends Container {
	IInventory inventory;
	ItemStack whistle;
	
	public ContainerWhistle(ItemStack whistle, IInventory playerInventory) {
		inventory = new InventoryBasic("whistle", 1);
		this.whistle = whistle;
		
		if (whistle.getTagCompound() != null) {
			ItemStack core = ItemStack.loadItemStackFromNBT(whistle.getTagCompound().getCompoundTag("core"));
			inventory.setInventorySlotContents(0, core);
		}
		
		this.addSlotToContainer(new SlotSlimeCore(inventory, 0, 80, 32));
		
		bindPlayerInventory(playerInventory);
	}
	
	private void bindPlayerInventory(IInventory playerInventory) {
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(playerInventory, var3, 8 + var3 * 18, 142));
        }
		
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer player) {
		ItemStack core = inventory.getStackInSlot(0);
		if (core != null) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setCompoundTag("core", core.writeToNBT(new NBTTagCompound()));
			whistle.setTagCompound(tag);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return true;
	}

}
