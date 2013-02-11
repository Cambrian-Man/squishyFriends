package squishyFriends.registry;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import squishyFriends.entity.EntityPetSlime;

public class PetSlimeSelector implements IEntitySelector {

	@Override
	public boolean isEntityApplicable(Entity entity) {
		if (entity instanceof EntityPetSlime) {
			return true;
		}
		else {
			return false;
		}
	}

}
