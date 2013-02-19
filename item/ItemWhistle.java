package squishyFriends.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import squishyFriends.ClientProxy;
import squishyFriends.SquishyFriends;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemWhistle extends Item {
	public ItemWhistle(int id) {
		super(id);
		
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
			player.openGui(SquishyFriends.instance, 1, world, 0, 0, 0);
		}
		else {
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			
			if (side == Side.SERVER) {
				if (stack.hasTagCompound() && stack.getTagCompound().getCompoundTag("core") != null) {
					SquishyFriends.instance.registry.spawnPet(player, world, stack);
				}
			}
		}
		return stack;
	}
}