package squishyFriends.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import squishyFriends.entity.TileEntitySlimePen;
import squishyFriends.inventory.ContainerSlimePen;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiSlimePen extends GuiContainer implements ICrafting {

	public GuiSlimePen(TileEntitySlimePen tileEntity, IInventory  playerInventory) {
		super(new ContainerSlimePen(tileEntity, playerInventory));
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
		
        int background = this.mc.renderEngine.getTexture("/squishyFriends/slimePen.png");
        this.mc.renderEngine.bindTexture(background);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

	}

}
