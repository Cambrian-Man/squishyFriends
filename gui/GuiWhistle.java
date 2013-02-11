package squishyFriends.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import squishyFriends.inventory.ContainerWhistle;

public class GuiWhistle extends GuiContainer {

	public GuiWhistle(EntityPlayer player) {
		super(new ContainerWhistle(player));
		this.xSize = 175;
		this.ySize = 165;
	}
	
	@Override
	protected void  drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Whistle", 6, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 96 + 2, 4210752);
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
