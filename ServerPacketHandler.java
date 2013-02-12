package squishyFriends;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		EntityPlayer sender = (EntityPlayer) player;
		
		if (packet.channel == "SF.setWhistle") {
			int whistleSlot;
			ItemStack whistle;
			boolean hasCore;
			ItemStack core;
			
			try	{
				whistleSlot = data.readInt();
				hasCore = data.readBoolean();
				whistle = sender.inventory.getStackInSlot(whistleSlot);
				
				if (hasCore) {
					core = packet.readItemStack(data);
					
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

}
