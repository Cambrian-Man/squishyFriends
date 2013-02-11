package squishyFriends.inventory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public class ContainerWhistle extends Container {
	InventoryWhistle inventory;
	ItemStack whistle;
	int whistleSlot;
	EntityPlayer player;
	
	public static ItemStack testWhistle;
	
	public ContainerWhistle(EntityPlayer player) {
		this.player = player;
		
		whistle = player.inventory.getCurrentItem();
		whistleSlot = player.inventory.currentItem;
		inventory = new InventoryWhistle("whistle", 1, this, player.inventory.currentItem);
		
		if (whistle.hasTagCompound()) {
			inventory.setInventorySlotContents(0, ItemStack.loadItemStackFromNBT(whistle.getTagCompound().getCompoundTag("core")));
		}
		
		this.addSlotToContainer(new SlotSlimeCore(inventory, 0, 80, 32));
		
		bindPlayerInventory(player.inventory);
		
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
	
	public void onCoreChanged(ItemStack core) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
    	
    	if (side == Side.CLIENT) {    		
    		Packet250CustomPayload packet = new Packet250CustomPayload();
    		packet.channel = "SF.setWhistle";
    		
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		DataOutputStream outputStream = new DataOutputStream(bos);
    		
    		try {
    			outputStream.writeInt(whistleSlot);
    			
    			if (core == null) {
    				outputStream.writeBoolean(false);
    			}
    			else {
    				outputStream.writeBoolean(true);
    				packet.writeItemStack(core, outputStream);
    			}
    		}
    		catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		packet.data = bos.toByteArray();
    		packet.length = bos.size();
    		
    		PacketDispatcher.sendPacketToServer(packet);
    	}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return true;
	}

}
