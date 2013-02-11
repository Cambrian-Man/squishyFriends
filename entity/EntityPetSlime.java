package squishyFriends.entity;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;

public class EntityPetSlime extends EntitySlime {

	public EntityPetSlime(World par1World) {
		super(par1World);
		this.setSlimeSize(1);
	}

}
