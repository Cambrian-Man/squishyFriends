package squishyFriends.behavior;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import squishyFriends.entity.EntityPetSlime;

public class EntityAIFollowOwner extends EntityAIBase {
	private EntityPetSlime pet;
	private EntityPlayer owner;
	
	private int level;
	
	public EntityAIFollowOwner (EntityPetSlime pet, EntityPlayer owner, int level) {
		this.pet = pet;
		this.owner = owner;
		this.level = level;
	}
	
	@Override
	public boolean shouldExecute() {
		return (pet.getDistanceToEntity(owner) > 10);
	}
	
	@Override
	public boolean continueExecuting() {
		return (pet.getDistanceToEntity(owner) > 10);
	}

}
