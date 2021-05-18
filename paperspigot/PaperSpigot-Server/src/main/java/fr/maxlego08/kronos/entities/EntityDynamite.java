package fr.maxlego08.kronos.entities;

import fr.maxlego08.kronos.utils.EntityCustomProjectile;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.MathHelper;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.World;

public class EntityDynamite
  extends EntityCustomProjectile
{
  private int explodefuse;
  private boolean extinguished;
  public boolean stuck = true;
  private boolean dynamite = false;
  
  public EntityDynamite(World world)
  {
    super(world);
    setPickupMode(0);
    this.extinguished = false;
    this.explodefuse = 500;
  }
  
  public EntityDynamite(World world, double p_76986_1_, double p_76986_2_, double p_76986_3_)
  {
    this(world);
    setPosition(p_76986_1_, p_76986_2_, p_76986_3_);
  }
  
  public EntityDynamite(World world, EntityLiving entityliving, int i, boolean dynamite)
  {
    this(world);
    setLocation(entityliving.locX, entityliving.locY + entityliving.getHeadHeight(), entityliving.locZ, entityliving.yaw, entityliving.pitch);
    this.locX -= MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F;
    this.locY -= 0.1D;
    this.locZ -= MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F;
    setPosition(this.locX, this.locY, this.locZ);
    this.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
    this.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
    this.motY = (-MathHelper.sin(this.pitch / 180.0F * 3.141593F));
    shoot(this.motX, this.motY, this.motZ, 0.7F, 4.0F);
    this.explodefuse = i;
    this.dynamite = dynamite;
  }
  
  public void h()
  {
    super.h();
    if ((!this.inGround) && (!this.beenInGround)) {
      this.pitch -= 50.0F;
    } else {
      this.pitch = 180.0F;
    }
    if ((M()) && (!this.extinguished))
    {
      this.extinguished = true;
      this.world.makeSound(this, "random.fizz", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
      for (int i = 0; i < 8; i++)
      {
        float f = 0.25F;
        this.world.addParticle("explode", this.locX - this.motX * f, this.locY - this.motY * f, this.locZ - this.motZ * f, this.motX, this.motY, this.motZ);
      }
    }
    this.explodefuse -= 1;
    if (this.explodefuse <= 0)
    {
      detonate();
      die();
    }
    else if (this.explodefuse > 0)
    {
      this.world.addParticle("smoke", this.locX, this.locY, this.locZ, 0.0D, 0.0D, 0.0D);
    }
  }
  
  public void onGroundHit(MovingObjectPosition movingobjectposition)
  {
    this.xTile = movingobjectposition.b;
    this.yTile = movingobjectposition.c;
    this.zTile = movingobjectposition.d;
    this.block = this.world.getType(this.xTile, this.yTile, this.zTile);
    this.motX = ((float)(movingobjectposition.pos.a - this.locX));
    this.motY = ((float)(movingobjectposition.pos.b - this.locY));
    this.motZ = ((float)(movingobjectposition.pos.c - this.locZ));
    float f1 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
    this.locX -= this.motX / f1 * 0.05D;
    this.locY -= this.motY / f1 * 0.05D;
    this.locZ -= this.motZ / f1 * 0.05D;
    
    this.motX *= -0.2D;
    this.motZ *= -0.2D;
    if (movingobjectposition.face == 1)
    {
      this.inGround = true;
      this.beenInGround = true;
    }
    else
    {
      this.inGround = false;
      this.world.makeSound(this, "randomom.fizz", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
    }
    if (this.block != null) {
      this.block.a(this.world, this.xTile, this.yTile, this.zTile, this);
    }
  }
  
  private void detonate()
  {
    if (!this.world.isStatic) {
      if (!this.dynamite)
      {
        float f = 3.5F;
        this.world.createExplosion(this, this.locX, this.locY, this.locZ, f, false, true);
      }
      else
      {
        float f = 2.5F;
        this.world.createExplosionCustom(this, this.locX, this.locY, this.locZ, f, true, true);
      }
    }
  }
  
  public boolean aimRotation()
  {
    return false;
  }
  
  public int getMaxArrowShake()
  {
    return 0;
  }
}

