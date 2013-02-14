package squishyFriends.entity;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import squishyFriends.SlimeData;
import squishyFriends.SquishyFriends;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

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
		
		this.owner = this.worldObj.getPlayerEntityByName(data.getOwner());
		System.out.println("My owner is " + this.owner);
	}
	
	public void setCoreData(SlimeData data) {
		this.data = data;
	}

	@Override
	protected void despawnEntity() {
		super.despawnEntity();
		
		// Remove the slime if the player entity is null.
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			if (worldObj.getPlayerEntityByName(data.getOwner()) == null) {
				SquishyFriends.instance.registry.removePet(this);
			}
		}
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
