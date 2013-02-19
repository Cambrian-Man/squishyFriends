package squishyFriends.registry;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import squishyFriends.SlimeData;
import squishyFriends.entity.EntityPetSlime;

public class RegistryEntry {
	public EntityPlayer player;
	public EntityPetSlime pet;
	public int petID;
	public String playerName;
	public ItemStack core;
	public ItemStack whistle;
	
	public RegistryEntry(EntityPlayer player, EntityPetSlime slime, ItemStack whistleStack) {
		this.player = player;
		this.playerName = player.getEntityName();
		this.pet = slime;
		this.petID = slime.entityId;
		this.whistle = whistleStack;
		
		core = ItemStack.loadItemStackFromNBT(whistleStack.getTagCompound().getCompoundTag("core"));
	}
}
