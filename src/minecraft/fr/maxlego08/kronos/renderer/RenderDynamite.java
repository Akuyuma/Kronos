package fr.maxlego08.kronos.renderer;

import org.lwjgl.opengl.GL11;

import fr.maxlego08.kronos.entities.EntityDynamite;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderDynamite
  extends Render
{
  public static final ResourceLocation texture = new ResourceLocation("textures/entity/dynamite.png");
  
  public void doRender(EntityDynamite entitydynamite, double p_76986_2_, double p_76986_3_, double p_76986_4_, float p_76986_5_, float p_76986_6_)
  {
    bindEntityTexture(entitydynamite);
    GL11.glPushMatrix();
    GL11.glTranslatef((float)p_76986_2_, (float)p_76986_3_, (float)p_76986_4_);
    GL11.glRotatef(entitydynamite.rotationYaw + 90.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(entitydynamite.prevRotationPitch + (entitydynamite.rotationPitch - entitydynamite.prevRotationPitch) * p_76986_6_, 0.0F, 0.0F, 1.0F);
    Tessellator tessellator = Tessellator.instance;
    int i = 0;
    float f2 = 0.0F;
    float f3 = 0.5F;
    float f4 = (0 + i * 10) / 32.0F;
    float f5 = (5 + i * 10) / 32.0F;
    float f6 = 0.0F;
    float f7 = 0.15625F;
    float f8 = (5 + i * 10) / 32.0F;
    float f9 = (10 + i * 10) / 32.0F;
    float f10 = 0.05625F;
    GL11.glEnable(32826);
    float f11 = -p_76986_6_;
    if (f11 > 0.0F)
    {
      float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
      GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
    }
    GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(f10, f10, f10);
    GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
    GL11.glNormal3f(f10, 0.0F, 0.0F);
    tessellator.startDrawingQuads();
    tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f6, f8);
    tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f7, f8);
    tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f7, f9);
    tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f6, f9);
    tessellator.draw();
    GL11.glNormal3f(-f10, 0.0F, 0.0F);
    tessellator.startDrawingQuads();
    tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f6, f8);
    tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f7, f8);
    tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f7, f9);
    tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f6, f9);
    tessellator.draw();
    for (int j = 0; j < 4; j++)
    {
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      GL11.glNormal3f(0.0F, 0.0F, f10);
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, f2, f4);
      tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, f3, f4);
      tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, f3, f5);
      tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, f2, f5);
      tessellator.draw();
    }
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  public void doRender(Entity entity, double p_76986_2_, double p_76986_3_, double p_76986_4_, float p_76986_5_, float p_76986_6_)
  {
    doRender((EntityDynamite)entity, p_76986_2_, p_76986_3_, p_76986_4_, p_76986_5_, p_76986_6_);
  }
  
  protected ResourceLocation getEntityTexture(EntityDynamite p_110775_1_)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity p_110775_1_)
  {
    return getEntityTexture((EntityDynamite)p_110775_1_);
  }
}
