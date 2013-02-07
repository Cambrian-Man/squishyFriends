package squishyFriends.item;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import squishyFriends.ClientProxy;
import squishyFriends.SquishyFriends;

public class ItemWhistle extends Item {
	public ItemWhistle(int id) {
		super(SquishyFriends.baseID);
		
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setIconIndex(1);
		setItemName("slimeWhistle");
	}
	
	public String getTextureFile(){
		return ClientProxy.ICONS;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player.isSneaking()) {
			player.openGui(SquishyFriends.instance, 1, world,
					player.chunkCoordX, player.chunkCoordY, player.chunkCoordZ);
		}
		
		return stack;
	}
}
