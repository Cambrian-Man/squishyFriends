package squishyFriends.registry;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import squishyFriends.entity.EntityPetSlime;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class PetRegistry {
	private HashMap<UUID, RegistryEntry> pets;
	

	public PetRegistry() {
		pets = new HashMap();
	}

	public void registerPet(EntityPlayer player, EntityPetSlime pet, ItemStack whistle) {
		RegistryEntry entry = new RegistryEntry(player, pet, whistle);
		pets.put(player.getPersistentID(), entry);
	}
	
	public boolean playerHasPet(EntityPlayer player) {
		UUID uuid = player.getPersistentID();
		
		if (pets.containsKey(uuid)) {
			EntityPetSlime pet = pets.get(uuid).pet;
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public EntityPetSlime getPet(EntityPlayer player) {
		return pets.get(player.getPersistentID()).pet;
	}
	
	public boolean spawnPet(EntityPlayer player, World world, ItemStack whistleStack) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			EntityPetSlime pet;
			if (playerHasPet(player)) {
				pet = getPet(player);
				pet.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0);
			}
			else {
				pet = new EntityPetSlime(world);
				pet.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0);
				registerPet(player, pet, whistleStack);
				world.spawnEntityInWorld(pet);
			}
			
		}
		
		return true;
	}
}
