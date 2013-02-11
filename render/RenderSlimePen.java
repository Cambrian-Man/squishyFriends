package squishyFriends.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import squishyFriends.entity.TileEntitySlimePen;

public class RenderSlimePen extends TileEntitySpecialRenderer {
	ModelBase slimePenModel;
	ModelBase slimeModel;

	public RenderSlimePen() {
		slimePenModel = new ModelSlimePen();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double d, double d1,
			double d2, float f) {
		if (tileEntity instanceof TileEntitySlimePen) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) d + 0.5F, (float) d1, (float) d2 + 0.5F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			bindTextureByName("/squishyFriends/slimePenTexture.png");
			((ModelSlimePen) slimePenModel).render();
			GL11.glPopMatrix();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_CULL_FACE);
		}

	}

}
