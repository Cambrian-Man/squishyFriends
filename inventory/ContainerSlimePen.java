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
		this.addSlotToContainer(new SlotSlimeCore(tileEntity, 5, 8, 25));
		
		for (int i = 0; i < 5; i++) {
			this.addSlotToContainer(new SlotTrainingItems(tileEntity, i, 44, 25 + (i * 18)));
		}
		
		this.addSlotToContainer(new Slot(tileEntity, 6, 8, 97));
		
		bindPlayerInventory(playerInventory);
	}
	
	private void bindPlayerInventory(IInventory playerInventory) {
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 140 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(playerInventory, var3, 8 + var3 * 18, 198));
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
