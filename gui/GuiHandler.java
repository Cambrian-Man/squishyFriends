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
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			
			if (tileEntity instanceof TileEntitySlimePen) {
				return new ContainerSlimePen((TileEntitySlimePen) tileEntity, player.inventory);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == 0) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			
			if (tileEntity instanceof TileEntitySlimePen) {
				return new GuiSlimePen((TileEntitySlimePen) tileEntity, player.inventory);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

}
