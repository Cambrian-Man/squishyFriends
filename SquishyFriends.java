package squishyFriends;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import squishyFriends.block.BlockSlimePen;
import squishyFriends.entity.TileEntitySlimePen;
import squishyFriends.gui.GuiHandler;
import squishyFriends.item.ItemSlimeCore;
import squishyFriends.item.ItemWhistle;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Cambrian_Man.SquishyFriends", name = "Squishy Friends", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
		clientPacketHandlerSpec = @SidedPacketHandler(channels = {"SquishyFriends"}, packetHandler = ClientPacketHandler.class),
		serverPacketHandlerSpec = @SidedPacketHandler(channels = {"SquishyFriend"}, packetHandler = ServerPacketHandler.class))

public class SquishyFriends {
	public static TileEntitySlimePen slimePen;
	public static Block slimePenBlock;
	public static Item whistle;
	public static Item slimeCore;
	
	@SidedProxy(clientSide = "squishyFriends.ClientProxy", serverSide = "squishyFriends.CommonProxy")
	public static ClientProxy proxy = new ClientProxy();
	
	public static int baseID = 250;

	@Instance
	public static SquishyFriends instance;
	
	private GuiHandler guiHandler = new GuiHandler();
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		
		/*
		 * Definitions
		 */
		slimePenBlock = new BlockSlimePen(baseID + 1, 0).setBlockName("slimePen");
		whistle = new ItemWhistle(baseID + 2);
		slimeCore = new ItemSlimeCore(baseID + 3);
		
		/*
		 * Block Registrations
		 */
		GameRegistry.registerBlock(slimePenBlock, "cambrian_man_slime_pen_block");
		
		/**
		 * Entity Registrations
		 */
		GameRegistry.registerTileEntity(TileEntitySlimePen.class, "cambrian_man_slime_pen");
		
		/*
		 * Item Registrations
		 */
		GameRegistry.registerItem(whistle, "cambrian_man_whistle");
		GameRegistry.registerItem(slimeCore, "cambrian_man_slime_core");
		
		/*
		 * Language Registrations
		 */
		LanguageRegistry.addName(slimePenBlock, "Slime Pen");
		LanguageRegistry.addName(whistle, "Whistle");
		LanguageRegistry.addName(slimeCore, "Slime Core");
		
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		
		registerItems();
		
		proxy.registerRenders();
	}
	
	private void registerItems()
	{
		GameRegistry.addRecipe(new ItemStack(slimePenBlock), 
				"ooo", "gsg", "ooo", 
				'o', new ItemStack(Block.obsidian), 'g', new ItemStack(Block.glass), 's', new ItemStack(Item.slimeBall));
		
		GameRegistry.addRecipe(new ItemStack(whistle), 
				"i  ", " s ", "  i", 'i', new ItemStack(Item.ingotIron), 's', new ItemStack(Item.slimeBall));
		
		GameRegistry.addRecipe(new ItemStack(slimeCore),
				"sss", "sds", "sss",
				's', new ItemStack(Item.slimeBall), 'd', new ItemStack(Item.diamond));

	}
}
