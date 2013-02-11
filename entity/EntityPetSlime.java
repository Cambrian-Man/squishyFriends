package squishyFriends.entity;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import squishyFriends.SquishyFriends;

public class EntityPetSlime extends EntitySlime {
	private float ownershipTimer = 200;
	private float ownershipTimeRemaining = 200;
	private EntityPlayer owner;

	public EntityPetSlime(World par1World) {
		super(par1World);
		this.setSlimeSize(1);
	}

	@Override
	protected void despawnEntity() {
		SquishyFriends.instance.registry.removePet(this);
		super.despawnEntity();
	}
}
