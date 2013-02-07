package squishyFriends.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

import squishyFriends.entity.TileEntitySlimePen;
import squishyFriends.inventory.ContainerSlimePen;
import squishyFriends.inventory.ContainerWhistle;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiWhistle extends GuiContainer implements ICrafting {

	public GuiWhistle(ItemStack whistle, IInventory  playerInventory) {
		super(new ContainerWhistle(whistle, playerInventory));
		this.xSize = 175;
		this.ySize = 165;
		FMLLog.info("test");
	}
	
	@Override
	protected void  drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Whistle", 6, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 96 + 2, 4210752);
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
		
        int background = this.mc.renderEngine.getTexture("/squishyFriends/whistle.png");
        this.mc.renderEngine.bindTexture(background);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}