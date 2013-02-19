package squishyFriends;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import squishyFriends.behavior.ISlimeBehavior;
import squishyFriends.behavior.Skill;
import squishyFriends.registry.SkillRegistry;

/**
 * Handler Class for interacting with Slime Data and stats.
 * @author Cambrian Man
 *
 */
public class SlimeData {
	private ItemStack stack;
	private NBTTagCompound tag;
	
	private SkillRegistry registry;
	
	private String name;
	private String owner;
	
	public ISlimeBehavior[] skills = new ISlimeBehavior[5];
	private byte[] progress = new byte[5];

	public SlimeData(ItemStack itemStack) {
		registry = SquishyFriends.instance.skills;
		stack = itemStack;
		
		if (stack.getTagCompound() == null) {
			tag = new NBTTagCompound();
			tag.setName("petStats");
			
			skills = new ISlimeBehavior[5];
			progress = new byte[5];
			
			stack.setTagCompound(tag);
			clearSkills();
		}
		else {
			tag = stack.getTagCompound();
			loadFromNBT(tag);
		}
	}
	
	public SlimeData(NBTTagCompound tag) {
		this.tag = tag;
		loadFromNBT(tag);
	}
	
	/**
	 * Returns a new array representing skills.
	 * @return
	 */
	private byte[] getSkillBytes() {
		byte[] bytes = new byte[5];
		
		for (int i = 0; i < skills.length; i++) {
			bytes[i] = registry.getSkillByte(skills[i]);
		}
		
		return bytes;
	}
	
	public NBTTagCompound getNBTTag() {
		return tag;
	}
	
	private void loadFromNBT(NBTTagCompound tag) {
		name = tag.getString("name");
		owner = tag.getString("owner");
		
		byte[] skillBytes =	tag.getByteArray("skills");
		
		
		for (int i = 0; i < skillBytes.length; i++) {
			try {
				skills[i] = registry.getSkill(skillBytes[i]).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Clears skills and associated progress.
	 */
	private void clearSkills() {
		Arrays.fill(skills, null);
		tag.setByteArray("skills", getSkillBytes());
		
		Arrays.fill(progress, (byte) 0);
		tag.setByteArray("progress", progress);
	}

	public void setOwner(String owner) {
		this.owner = owner;
		tag.setString("owner", owner);
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setName(String name) {
		this.name = name;
		tag.setString("name", name);
	}
	
	public String getName() {
		if (name != null)
			return name;
		else
			return "";
	}
	
	public String getSkillName(int level) {
		return skills[level].toString();
	}
	
	public boolean hasSkill(String name) {
		for (int i = 0; i < skills.length; i++) {
			if (skills[i].getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasSkill(byte index) {
		for (int i = 0; i < skills.length; i++) {
			if (registry.getSkillByte(skills[i]) == index) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets a skill, updating the byte array.
	 * If a skill is set twice, it removes the skill from the list.
	 * @param skill
	 * @param level
	 */
	public void setSkill(byte skill, int level) {
		if (hasSkill(skill)) {
			removeSkill(skill);
		}
		
		try {
			skills[level] = registry.getSkill(skill).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		progress[level] = 0;
		tag.setByteArray("skills", getSkillBytes());
	}
	
	public void removeSkill(byte skill) {
		if (hasSkill(skill)) {
			for (int i = 0; i < skills.length; i++) {
				skills[i] = null;
			}
		}
	}
}