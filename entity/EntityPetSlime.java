package squishyFriends.entity;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import squishyFriends.SlimeData;
import squishyFriends.SquishyFriends;

public class EntityPetSlime extends EntitySlime {
	private float ownershipTimer = 200;
	private float ownershipTimeRemaining = 200;
	private EntityPlayer owner;
	public SlimeData data;

	public EntityPetSlime(World par1World) {
		super(par1World);
		this.setSlimeSize(1);
	}
	
	public void setCoreData(NBTTagCompound tag) {
		this.data = new SlimeData(tag);
	}
	
	public void setCoreData(SlimeData data) {
		this.data = data;
	}

	@Override
	protected void despawnEntity() {
		SquishyFriends.instance.registry.removePet(this);
		super.despawnEntity();
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		tag.setCompoundTag("core", data.getNBTTag());
		super.writeEntityToNBT(tag);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		
		setCoreData(tag.getCompoundTag("core"));
	}
}
