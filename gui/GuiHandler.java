package squishyFriends.gui;

import squishyFriends.entity.TileEntitySlimePen;
import squishyFriends.inventory.ContainerSlimePen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == 0) {
			return openSlimePen(player, world, x, y, z);
		}
		else {
			return null;
		}

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == 0) {
			return openSlimePen(player, world, x, y, z);
		}
		else {
			return null;
		}
	}
	
	private Object openSlimePen (EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntitySlimePen) {
			return new GuiSlimePen((TileEntitySlimePen) tileEntity, player.inventory);
		}
		else {
			return null;
		}
	}

}
