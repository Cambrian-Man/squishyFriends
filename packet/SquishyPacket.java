package squishyFriends.packet;

import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;

public abstract class SquishyPacket {

	public abstract void handle(DataInputStream data, EntityPlayer player);

}
