package squishyFriends;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import squishyFriends.entity.EntityPetSlime;

public class EventListener {

	public EventListener() {
		
	}

	@ForgeSubscribe
	public void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			if (event.entityLiving instanceof EntityPetSlime) {
				if (!SquishyFriends.instance.registry.petHasOwner(event.world, event.entity.entityId)) {
					event.setResult(Event.Result.DENY);
				}
			}
		}
	}
}
