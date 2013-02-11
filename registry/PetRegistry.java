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
	private HashMap<String, RegistryEntry> pets;
	private PetSlimeSelector petSlimeSelector = new PetSlimeSelector();
	

	public PetRegistry() {
		pets = new HashMap();
	}
	
	public void sweepAndUnloadPets(World world, Chunk chunk) {
		ArrayList petList = new ArrayList();
		
		Iterator itr = pets.values().iterator();
		
		while (itr.hasNext()) {
			RegistryEntry entry = (RegistryEntry) itr.next();
			
			if (!petHasOwner(world, entry.petID)) {
				entry.pet.setDead();
				pets.remove(entry.playerName);
			}
		}
	}
	
	public void despawnPet(World world, EntityPlayer player) {
		RegistryEntry entry = pets.get(player.getEntityName());
		
		entry.pet.setDead();
		pets.remove(player.getEntityName());
	}
	
	@SideOnly(Side.SERVER)
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
			RegistryEntry entry = (RegistryEntry) itr.next();
			
			if (entry.petID == petID) {
				return entry.pet;
			}
		}
		
		return null;
	}

	public void registerPet(EntityPlayer player, EntityPetSlime pet, ItemStack whistle) {
		RegistryEntry entry = new RegistryEntry(player, pet, whistle);
		pets.put(player.getEntityName(), entry);
	}
	
	public void removePet(EntityPetSlime pet) {
		Set owners = pets.keySet();
		
		Iterator itr = owners.iterator();
		while (itr.hasNext()) {
			RegistryEntry entry = (RegistryEntry) itr.next();
			
			if (entry.pet == pet) {
				pets.remove(entry);
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
	
	@SideOnly(Side.SERVER)
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
