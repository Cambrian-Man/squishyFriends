package squishyFriends.packet;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import squishyFriends.SquishyFriends;

public class PacketSlotWhistle extends SquishyPacket {

	public PacketSlotWhistle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(DataInputStream data, EntityPlayer player) {
		
		int whistleSlot;
		ItemStack whistle;
		boolean hasCore;
		ItemStack core;
		
		try	{
			whistleSlot = data.readInt();
			hasCore = data.readBoolean();
			whistle = player.inventory.getStackInSlot(whistleSlot);
			
			if (hasCore) {
				core = Packet.readItemStack(data);
				
				NBTTagCompound tag = new NBTTagCompound();
				
				tag.setCompoundTag("core", core.writeToNBT(new NBTTagCompound()));
				whistle.setTagCompound(tag);
			}
			else {
				whistle.setTagCompound(new NBTTagCompound());
				SquishyFriends.instance.registry.despawnPet((EntityPlayer) player);
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
	}

}
