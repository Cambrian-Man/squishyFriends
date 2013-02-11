package squishyFriends.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSlimePen extends ModelBase
{
  //fields
    ModelRenderer Top;
    ModelRenderer Bottom;
    ModelRenderer Post1;
    ModelRenderer Post2;
    ModelRenderer Post3;
    ModelRenderer Post4;
    ModelRenderer Interior;
    
  public ModelSlimePen()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Top = new ModelRenderer(this, 0, 0);
      Top.addBox(-8F, -16F, -8F, 16, 3, 16);
      Top.setRotationPoint(0F, 0F, 0F);
      Top.setTextureSize(128, 64);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, (float) Math.PI);
      Bottom = new ModelRenderer(this, 64, 0);
      Bottom.addBox(-8F, 13F, -8F, 16, 3, 16);
      Bottom.setRotationPoint(0F, 16F, 0F);
      Bottom.setTextureSize(128, 64);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, (float) Math.PI);
      Post1 = new ModelRenderer(this, 64, 32);
      Post1.addBox(0F, 0F, 0F, 1, 10, 1);
      Post1.setRotationPoint(-8F, 3F, -8F);
      Post1.setTextureSize(128, 64);
      Post1.mirror = true;
      setRotation(Post1, 0F, 0F, 0F);
      Post2 = new ModelRenderer(this, 64, 32);
      Post2.addBox(0F, 0F, 0F, 1, 10, 1);
      Post2.setRotationPoint(-8F, 3F, 7F);
      Post2.setTextureSize(128, 64);
      Post2.mirror = true;
      setRotation(Post2, 0F, 0F, 0F);
      Post3 = new ModelRenderer(this, 64, 32);
      Post3.addBox(0F, 0F, 0F, 1, 10, 1);
      Post3.setRotationPoint(7F, 3F, -8F);
      Post3.setTextureSize(128, 64);
      Post3.mirror = true;
      setRotation(Post3, 0F, 0F, 0F);
      Post4 = new ModelRenderer(this, 64, 32);
      Post4.addBox(0F, 0F, 0F, 1, 10, 1);
      Post4.setRotationPoint(7F, 3F, 7F);
      Post4.setTextureSize(128, 64);
      Post4.mirror = true;
      setRotation(Post4, 0F, 0F, 0F);
      Interior = new ModelRenderer(this, 0, 32);
      Interior.addBox(0F, 0F, 0F, 14, 10, 14);
      Interior.setRotationPoint(7F, 13F, -7F);
      Interior.setTextureSize(128, 64);
      Interior.mirror = true;
      setRotation(Interior, 0F, 0F, (float) Math.PI);
  }
  
  public void render()
  {
	  Top.render(0.0625F);
	  Bottom.render(0.0625F);
	  Post1.render(0.0625F);
	  Post2.render(0.0625F);
	  Post3.render(0.0625F);
	  Post4.render(0.0625F);
	  Interior.render(0.0625F);
	  
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
