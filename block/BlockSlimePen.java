package squishyFriends.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import squishyFriends.SquishyFriends;
import squishyFriends.entity.TileEntitySlimePen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSlimePen extends BlockContainer {
	public BlockSlimePen(int id, int texture)
	{
		super(id, texture, Material.ice);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(10.0F);
		this.setLightOpacity(0);
	}
	
	public int getBlockTextureFromSide(int i)
	{
		switch (i)
		{
		case 0: return 1;
		case 1: return 2;
		default: return 0;
		}
	}
	
	public int getRenderBlockPass()
    {
        return 1;
    }
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		TileEntitySlimePen penEntity = new TileEntitySlimePen();
		
		return penEntity;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		
		player.openGui(SquishyFriends.instance, 0, world, x, y, z);
		
		return true;
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
    @SideOnly(Side.CLIENT)
	
	public String getTextureFile()
	{
		return "/squishyFriends/blocks.png";
	}
}
