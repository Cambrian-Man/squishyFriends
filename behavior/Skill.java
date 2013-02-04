package squishyFriends.behavior;

public enum Skill {
	NONE ((byte) 0),
	MOBILITY ((byte) 1),
	CURIOUSITY ((byte) 2),
	PRODUCTIVITY ((byte) 3),
	AGGRESSION ((byte) 4),
	MELEE ((byte) 5),
	RESILIANCE ((byte) 6);
	
	private final byte id;
	
	Skill(byte id) {
		this.id = id;
	}
	
	public final byte getId()
	{
		return this.id;
	}
}