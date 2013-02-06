package squishyFriends.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import squishyFriends.entity.TileEntitySlimePen;

public class SlotTrainingItems extends Slot {
	private TileEntitySlimePen inventory;

	public SlotTrainingItems(IInventory par1iInventory, int par2, int par3,
			int par4) {
		super(par1iInventory, par2, par3, par4);
		
		inventory = (TileEntitySlimePen) par1iInventory;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (inventory.getCore() == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
