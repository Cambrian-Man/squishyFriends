package squishyFriends.item;

import java.util.List;

import squishyFriends.ClientProxy;
import squishyFriends.SlimeData;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSlimeCore extends Item {

	public ItemSlimeCore(int id) {
		super(id);
		setMaxStackSize(1);
		setItemName("slimeCore");
		setCreativeTab(CreativeTabs.tabMisc);
		setIconIndex(2);
	}
	
	public String getTextureFile() {
		return ClientProxy.ICONS;
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		super.onCreated(stack, world, player);
		SlimeData data;
		
		data = new SlimeData(stack);
		data.setOwner(player.getEntityName());
		data.setName("Squishy");
	}
	
	@SideOnly(Side.CLIENT)
	
	@Override
	public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4) {
		SlimeData data;
		if (stack.hasTagCompound()) {
			data = new SlimeData(stack.getTagCompound());

			list.add(data.getName());
			list.add("Owner: " + data.getOwner());
			
			for (int i = 0; i < 5; i++) {
				list.add(data.getSkillName(i));
			}
		}
		
		super.addInformation(stack, player, list, par4);
	}
}
