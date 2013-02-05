package squishyFriends.inventory;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import squishyFriends.SlimeData;
import squishyFriends.SquishyFriends;
import squishyFriends.entity.TileEntitySlimePen;

public class ContainerSlimePen extends Container implements ICrafting {
	private TileEntitySlimePen tileEntity;
	
	private SlimeData slimeData;
	
	public ContainerSlimePen(TileEntitySlimePen tileEntity, IInventory  playerInventory) {
		super();
		this.tileEntity = tileEntity;
		this.addSlotToContainer(new SlotSlimeCore(tileEntity, 0, 7, 47));
		
		for (int i = 0; i < 5; i++) {
			this.addSlotToContainer(new Slot(tileEntity, i + 1, 43, 24 + (i * 18)));
		}
		
		this.addSlotToContainer(new Slot(tileEntity, 6, 7, 96));
		this.addSlotToContainer(new Slot(tileEntity, 7, 149, 24));
		
		bindPlayerInventory(playerInventory);
	}
	
	private void bindPlayerInventory(IInventory playerInventory) {
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 9 + j * 18, 64 + i * 16));
			}
		}
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
