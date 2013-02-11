package squishyFriends;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import squishyFriends.entity.*;
import squishyFriends.render.*;

public class ClientProxy extends CommonProxy {
	public static String BLOCKS = "/squishyFriends/blocks.png";
	public static String ICONS = "/squishyFriends/icons.png";
	
	@Override
	public void registerRenders()
	{
		MinecraftForgeClient.preloadTexture(BLOCKS);
		MinecraftForgeClient.preloadTexture(ICONS);	
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySlimePen.class, new RenderSlimePen());
	}
}
