package squishyFriends.inventory;

import squishyFriends.SquishyFriends;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSlimeCore extends Slot {

	public SlotSlimeCore(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return (stack.getItem() == SquishyFriends.slimeCore);
	}

}
