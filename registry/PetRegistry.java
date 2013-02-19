package squishyFriends.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import squishyFriends.entity.EntityPetSlime;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PetRegistry {
	private HashMap<String, PetRegistryEntry> pets;
	private PetSlimeSelector petSlimeSelector = new PetSlimeSelector();
	

	public PetRegistry() {
		pets = new HashMap();
	}
	
	public void sweepAndUnloadPets(World world, Chunk chunk) {
		ArrayList petList = new ArrayList();
		
		Iterator itr = pets.values().iterator();
		
		while (itr.hasNext()) {
			PetRegistryEntry entry = (PetRegistryEntry) itr.next();
			
			if (!petHasOwner(world, entry.petID)) {
				entry.pet.setDead();
				pets.remove(entry.playerName);
			}
		}
	}
	
	public void despawnPet(EntityPlayer player) {
		PetRegistryEntry entry = pets.get(player.getEntityName());
		
		if (entry != null) {
			entry.pet.setDead();
			pets.remove(player.getEntityName());
		}
	}
	
	public boolean petHasOwner(World world, int petID) {
		EntityPetSlime pet = getPetByID(petID);
		
		// If there is no pet by that ID, return false.
		if (pet == null) {
			return false;
		}
		
		// Iterate through the pet owners, and check them against
		// the list of current players.
		String[] users = FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames();
		Set owners = pets.keySet();
		
		Iterator itr = owners.iterator();
		
		while (itr.hasNext()) {
			String owner = (String) itr.next();
			for (int i = 0; i < users.length; i++) {
				if (owner.equals(users[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public EntityPetSlime getPetByID(int petID) {
		Iterator itr = pets.values().iterator();

		while (itr.hasNext()) {
			PetRegistryEntry entry = (PetRegistryEntry) itr.next();
			
			if (entry.petID == petID) {
				return entry.pet;
			}
		}
		
		return null;
	}

	public PetRegistryEntry registerPet(EntityPlayer player, EntityPetSlime pet, ItemStack whistle) {
		PetRegistryEntry entry = new PetRegistryEntry(player, pet, whistle);
		pets.put(player.getEntityName(), entry);
		return entry;
	}
	
	public void removePet(EntityPetSlime pet) {
		Set owners = pets.keySet();
		
		Iterator itr = owners.iterator();
		while (itr.hasNext()) {
			String owner = (String) itr.next();
			PetRegistryEntry entry = (PetRegistryEntry) pets.get(owner);
			
			if (entry.petID == pet.entityId) {
				pets.remove(owner);
				pet.setDead();
			}
		}
	}
	
	public boolean playerHasPet(EntityPlayer player) {
		String username = player.getEntityName();
		
		if (pets.containsKey(username)) {
			EntityPetSlime pet = pets.get(username).pet;
			if (!pet.isDead) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public EntityPetSlime getPet(EntityPlayer player) {
		return pets.get(player.getEntityName()).pet;
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
				PetRegistryEntry entry = registerPet(player, pet, whistleStack);
				pet.setCore(entry.core);
				world.spawnEntityInWorld(pet);
			}
			
		}
		
		return true;
	}
}
