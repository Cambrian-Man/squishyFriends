package squishyFriends.item;

import squishyFriends.ClientProxy;
import squishyFriends.SquishyFriends;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemWhistle extends Item {
	public ItemWhistle(int id) {
		super(SquishyFriends.baseID);
		
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setIconIndex(0);
		setItemName("slimeWhistle");
	}
	
	public String getTextureFile(){
		return ClientProxy.ICONS;
	}
}
