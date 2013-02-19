package squishyFriends.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import squishyFriends.behavior.ISlimeBehavior;

public class SkillRegistry {
	private List<Class<? extends ISlimeBehavior>> skills;
	private HashMap<Class<? extends ISlimeBehavior>, String> skillNames;
	
	public SkillRegistry() {
		skills = new ArrayList(128);
		skillNames = new HashMap<Class<? extends ISlimeBehavior>, String>();
	}
	
	public void add(Class<? extends ISlimeBehavior> handler, String name) {
		skills.add(handler);
		skillNames.put(handler, handler.getName());
	}
	
	public Class<? extends ISlimeBehavior> getSkill(String name) {
		int i;
		Iterator itr = skills.iterator();
		
		Class<ISlimeBehavior> handler;
		while (itr.hasNext()) {
			handler = (Class<ISlimeBehavior>) itr.next();
			if (handler.getName().equals(name)) {
				return handler;
			}
		}
		
		return null;
	}
	
	public Class<? extends ISlimeBehavior> getSkill(byte index) {
		return (Class<? extends ISlimeBehavior>) skills.get((int) index);
	}
	
	public byte getSkillByte(ISlimeBehavior handler) {
		return (byte) skills.indexOf(handler);
	}
}
