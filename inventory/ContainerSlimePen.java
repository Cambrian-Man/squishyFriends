package squishyFriends.inventory;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import squishyFriends.SlimeData;
import squishyFriends.SquishyFriends;
import squishyFriends.entity.TileEntitySlimePen;

public class ContainerSlimePen extends Container implements ICrafting {
	private TileEntitySlimePen tileEntity;
	
	private SlimeData slimeData;
	
	public ContainerSlimePen(TileEntitySlimePen tileEntity) {
		this.tileEntity = tileEntity;
		this.addSlotToContainer(new SlotSlimeCore(tileEntity, 0, 76, 27));
	}

	@Override
	public void sendContainerAndContentsToPlayer(Container var1, List var2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendSlotContents(Container var1, int var2, ItemStack var3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendProgressBarUpdate(Container var1, int var2, int var3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		ItemStack coreStack = inventory.getStackInSlot(0);
		
		if (coreStack == null) {
			slimeData = null;
		}
		else if (coreStack.getItem() == SquishyFriends.slimeCore) {
			slimeData = new SlimeData(coreStack.getTagCompound());
		}
	}

}
