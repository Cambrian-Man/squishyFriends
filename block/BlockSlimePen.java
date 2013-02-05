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
	
    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
    }
	
	public String getTextureFile()
	{
		return "/squishyFriends/blocks.png";
	}
}
