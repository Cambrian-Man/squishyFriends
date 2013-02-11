package squishyFriends.inventory;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class InventoryWhistle extends InventoryBasic {
	private ContainerWhistle container;
	private int whistleSlot;

	public InventoryWhistle(String par1Str, int par2, ContainerWhistle container, int whistleSlot) {
		super(par1Str, par2);
		
		this.container = container;
		this.whistleSlot = whistleSlot;
	}
	
    @Override
    public void onInventoryChanged() {
    	super.onInventoryChanged();
    	container.onCoreChanged(getStackInSlot(0));
    }
}
