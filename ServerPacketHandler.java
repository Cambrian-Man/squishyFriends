package squishyFriends;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Hashtable;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import squishyFriends.packet.PacketSlotWhistle;
import squishyFriends.packet.SquishyPacket;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {
	private Map<String, SquishyPacket> map = new Hashtable<String, SquishyPacket>();
	
	private PacketSlotWhistle PacketSlotWhistle = new PacketSlotWhistle();
	
	public ServerPacketHandler() {
		map.put("SF.slotWhistle", PacketSlotWhistle);
	}
	

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		map.get(packet.channel).handle(data, (EntityPlayer) player);
		
	}

}
