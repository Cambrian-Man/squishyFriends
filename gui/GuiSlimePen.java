package squishyFriends.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiSlimePen extends GuiContainer implements ICrafting {

	public GuiSlimePen(Container par1Container) {
		super(par1Container);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void  drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Slime Pen", 6, 6, 0xffffff);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 96 + 2, 0xffffff);
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
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		GL11.glColor4f(1.0f,  1.0f,  1.0f, 1.0f);

	}

}
