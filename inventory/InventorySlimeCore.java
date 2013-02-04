package squishyFriends.inventory;

import net.minecraft.inventory.InventoryBasic;

public class InventorySlimeCore extends InventoryBasic {
	final ContainerSlimePen container;
	
	public InventorySlimeCore(ContainerSlimePen container, String name, int slots) {
		super(name, slots);
		this.container = container;
	}
	
	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();
		container.onCraftMatrixChanged(this);
	}

}
