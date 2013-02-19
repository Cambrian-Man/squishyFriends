package squishyFriends.entity;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
	private String ownerName;
	private ItemStack core;
	public SlimeData data;
	
	private int coreID = 12;

	public EntityPetSlime(World par1World) {
		super(par1World);
		this.setSlimeSize(1);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		
		// The owner name.
		this.dataWatcher.addObject(17, "");
	}
	
	public void setCore(ItemStack core) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			this.core = core;
			this.data = new SlimeData(this.core);
		}
		else {
			this.data = new SlimeData(this.dataWatcher.getWatchableObjectItemStack(coreID));
		}
		
		//this.owner = this.worldObj.getPlayerEntityByName(data.getOwner());
		setOwner(data.getOwner());
		
		// We loaded, but there's no player.
		if (this.getOwner() == null) {
			SquishyFriends.instance.registry.removePet(this);
			this.setDead();
		}
	}
	
	public void setCoreData(SlimeData data) {
		this.data = data;
	}

	@Override
	protected void despawnEntity() {
		super.despawnEntity();
		
		// Remove the slime if the player entity is null.
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			if (this.getOwner() == null) {
				SquishyFriends.instance.registry.removePet(this);
			}
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		if (core != null) {
			NBTTagCompound coreTag = core.writeToNBT(new NBTTagCompound());
			tag.setCompoundTag("core", coreTag);
		}
		super.writeEntityToNBT(tag);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		
		NBTTagCompound coreTag = tag.getCompoundTag("core");
		
		if (coreTag != null) {
			setCore(ItemStack.loadItemStackFromNBT(coreTag));
		}
	}
	
	public String getOwnerName() {
		return this.dataWatcher.getWatchableObjectString(17);
	}
	
	public void setOwner(String owner) {
		this.dataWatcher.updateObject(17, owner);
	}
	
	public EntityPlayer getOwner() {
		return worldObj.getPlayerEntityByName(getOwnerName());
	}
}
