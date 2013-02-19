package squishyFriends.behavior;

import net.minecraft.entity.ai.EntityAIBase;

public interface ISlimeBehavior {
	
	/**
	 * Return a task that the behavior should execute during the 
	 * loop.
	 * 
	 * @param level	An integer between 1 and 5, corresponding to level of the
	 * 				skill.
	 * @return
	 */
	public EntityAIBase getTask(int level);
	
	public String getName();
	
	public String getConfig();
}
