package squishyFriends.behavior;

import net.minecraft.entity.ai.EntityAIBase;

public class MobilityHandler implements ISlimeBehavior {

	@Override
	public EntityAIBase getTask(int level) {
		switch (level) {
		case 1:
			// Stationary
			break;

		default:
			break;
		}
		
		return null;
	}

	@Override
	public String getName() {
		return "Mobility";
	}
	
	@Override
	public String getConfig() {
		return "/SquishyFriends/behavior/mobility.json";
	}

}
