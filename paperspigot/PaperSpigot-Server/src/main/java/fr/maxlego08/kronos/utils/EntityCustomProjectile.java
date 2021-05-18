package fr.maxlego08.kronos.utils;

import java.util.List;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.IProjectile;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MathHelper;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.Vec3D;
import net.minecraft.server.World;

public class EntityCustomProjectile
  extends Entity
  implements IProjectile
{
  protected int xTile;
  protected int yTile;
  protected int zTile;
  protected Block block;
  protected int inData;
  protected boolean inGround;
  public int pickupMode;
  protected int ticksInGround;
  protected int ticksInAir;
  public boolean beenInGround;
  public float extraDamage;
  public int knockBack;
  
  public EntityCustomProjectile(World world)
  {
    super(world);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    this.block = null;
    this.inData = 0;
    this.inGround = false;
    this.ticksInAir = 0;
    this.height = 0.0F;
    this.pickupMode = 0;
    this.extraDamage = 0.0F;
    this.knockBack = 0;
    a(0.5F, 0.5F);
  }
  
  protected void c()
  {
    this.datawatcher.add(16, Byte.valueOf((byte)0).byteValue());
  }
  
  protected void setPickupModeFromEntity(EntityLiving entityliving)
  {
    if ((entityliving instanceof EntityPlayer))
    {
      if (((EntityPlayer)entityliving).abilities.canInstantlyBuild) {
        setPickupMode(2);
      }
    }
    else {
      setPickupMode(0);
    }
  }
  
  public void shoot(double p_76986_0_, double p_76986_1_, double p_76986_2_, float p_76986_3_, float p_76986_4_)
  {
    float f2 = MathHelper.sqrt(p_76986_0_ * p_76986_0_ + p_76986_1_ * p_76986_1_ + p_76986_2_ * p_76986_2_);
    p_76986_0_ /= f2;
    p_76986_1_ /= f2;
    p_76986_2_ /= f2;
    p_76986_0_ += this.random.nextGaussian() * 0.007499999832361937D * p_76986_4_;
    p_76986_1_ += this.random.nextGaussian() * 0.007499999832361937D * p_76986_4_;
    p_76986_2_ += this.random.nextGaussian() * 0.007499999832361937D * p_76986_4_;
    p_76986_0_ *= p_76986_3_;
    p_76986_1_ *= p_76986_3_;
    p_76986_2_ *= p_76986_3_;
    this.motX = p_76986_0_;
    this.motY = p_76986_1_;
    this.motZ = p_76986_2_;
    float f3 = MathHelper.sqrt(p_76986_0_ * p_76986_0_ + p_76986_2_ * p_76986_2_);
    this.lastYaw = (this.yaw = (float)(Math.atan2(p_76986_0_, p_76986_2_) * 180.0D / 3.141592653589793D));
    this.lastPitch = (this.pitch = (float)(Math.atan2(p_76986_1_, f3) * 180.0D / 3.141592653589793D));
    this.ticksInGround = 0;
  }
  
  public void h()
  {
    C();
  }
  
  public void C()
  {
    super.C();
    if (aimRotation())
    {
      float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
      this.lastYaw = (this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592653589793D));
      this.lastPitch = (this.pitch = (float)(Math.atan2(this.motY, f) * 180.0D / 3.141592653589793D));
    }
    Block i = this.world.getType(this.xTile, this.yTile, this.zTile);
    if (i != null)
    {
      i.updateShape(this.world, this.xTile, this.yTile, this.zTile);
      AxisAlignedBB axisalignedbb = i.a(this.world, this.xTile, this.yTile, this.zTile);
      if ((axisalignedbb != null) && (axisalignedbb.a(Vec3D.a(this.locX, this.locY, this.locZ)))) {
        this.inGround = true;
      }
    }
    if (this.inGround)
    {
      Block j = this.world.getType(this.xTile, this.yTile, this.zTile);
      int k = this.world.getData(this.xTile, this.yTile, this.zTile);
      if ((j == this.block) && (k == this.inData))
      {
        this.ticksInGround += 1;
        int t = getMaxLifetime();
        if ((t != 0) && (this.ticksInGround >= t)) {
          die();
        }
      }
      else
      {
        this.inGround = false;
        this.motX *= this.random.nextFloat() * 0.2F;
        this.motY *= this.random.nextFloat() * 0.2F;
        this.motZ *= this.random.nextFloat() * 0.2F;
        this.ticksInGround = 0;
        this.ticksInAir = 0;
      }
      return;
    }
    this.ticksInAir += 1;
    Vec3D Vec3 = Vec3D.a(this.locX, this.locY, this.locZ);
    
    Vec3D Vec31 = Vec3D.a(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
    MovingObjectPosition movingobjectposition = this.world.rayTrace(Vec3, Vec31, false, true, false);
    Vec3 = Vec3D.a(this.locX, this.locY, this.locZ);
    Vec31 = Vec3D.a(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
    if (movingobjectposition != null) {
      Vec31 = Vec3D.a(movingobjectposition.pos.a, movingobjectposition.pos.b, movingobjectposition.pos.c);
    }
    Entity entity = null;
    List<Entity> list = this.world.getEntities(this, this.boundingBox.a(this.motX, this.motY, this.motZ).grow(1.0D, 1.0D, 1.0D));
    double d = 0.0D;
    for (int l = 0; l < list.size(); l++)
    {
      Entity entity1 = (Entity)list.get(l);
      if ((entity1.R()) && (this.ticksInAir >= 5))
      {
        float f4 = 0.3F;
        AxisAlignedBB axisalignedbb1 = entity1.boundingBox.grow(f4, f4, f4);
        MovingObjectPosition movingobjectposition1 = axisalignedbb1.a(Vec3, Vec31);
        if (movingobjectposition1 != null)
        {
          double d1 = Vec3.d(movingobjectposition1.pos);
          if ((d1 < d) || (d == 0.0D))
          {
            entity = entity1;
            d = d1;
          }
        }
      }
    }
    if (entity != null) {
      movingobjectposition = new MovingObjectPosition(entity);
    }
    if (movingobjectposition != null) {
      if (movingobjectposition.entity != null) {
        onEntityHit(movingobjectposition.entity);
      } else {
        onGroundHit(movingobjectposition);
      }
    }
    if (getIsCritical()) {
      for (int i1 = 0; i1 < 2; i1++) {
        this.world.addParticle("crit", this.locX + this.motX * i1 / 4.0D, this.locY + this.motY * i1 / 4.0D, this.locZ + this.motZ * i1 / 4.0D, -this.motX, -this.motY + 0.2D, -this.motZ);
      }
    }
    this.locX += this.motX;
    this.locY += this.motY;
    this.locZ += this.motZ;
    if (aimRotation())
    {
      float f2 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
      this.yaw = ((float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592653589793D));
      for (this.pitch = ((float)(Math.atan2(this.motY, f2) * 180.0D / 3.141592653589793D)); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F) {}
      while (this.pitch - this.lastPitch >= 180.0F) {
        this.lastPitch += 360.0F;
      }
      while (this.yaw - this.lastYaw < -180.0F) {
        this.lastYaw -= 360.0F;
      }
      while (this.yaw - this.lastYaw >= 180.0F) {
        this.lastYaw += 360.0F;
      }
      this.pitch = (this.lastPitch + (this.pitch - this.lastPitch) * 0.2F);
      this.yaw = (this.lastYaw + (this.yaw - this.lastYaw) * 0.2F);
    }
    float resistance = getAirResistance();
    float gravity = getGravity();
    this.motX *= resistance;
    this.motY *= resistance;
    this.motZ *= resistance;
    this.motY -= gravity;
    setPosition(this.locX, this.locY, this.locZ);
    I();
  }
  
  public void onEntityHit(Entity entity)
  {
    bounceBack();
  }
  
  public void onGroundHit(MovingObjectPosition movingobjectposition)
  {
    this.xTile = movingobjectposition.b;
    this.yTile = movingobjectposition.c;
    this.zTile = movingobjectposition.d;
    this.block = this.world.getType(this.xTile, this.yTile, this.zTile);
    this.inData = this.world.getData(this.xTile, this.yTile, this.zTile);
    this.motX = (movingobjectposition.pos.a - this.locX);
    this.motY = (movingobjectposition.pos.b - this.locY);
    this.motZ = (movingobjectposition.pos.c - this.locZ);
    float f1 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
    this.locX -= this.motX / f1 * 0.05D;
    this.locY -= this.motY / f1 * 0.05D;
    this.locZ -= this.motZ / f1 * 0.05D;
    this.inGround = true;
    this.beenInGround = true;
    setIsCritical(false);
    playHitSound();
    if (this.block != null) {
      this.block.a(this.world, this.xTile, this.yTile, this.zTile, this);
    }
  }
  
  protected void bounceBack()
  {
    this.motX *= -0.1D;
    this.motY *= -0.1D;
    this.motZ *= -0.1D;
    this.yaw += 180.0F;
    this.lastYaw += 180.0F;
    this.ticksInAir = 0;
  }
  
  public double getTotalVelocity()
  {
    return Math.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
  }
  
  public boolean aimRotation()
  {
    return true;
  }
  
  public int getMaxLifetime()
  {
    return 1200;
  }
  
  public ItemStack getPickupItem()
  {
    return null;
  }
  
  public float getAirResistance()
  {
    return 0.99F;
  }
  
  public float getGravity()
  {
    return 0.05F;
  }
  
  public int getMaxArrowShake()
  {
    return 7;
  }
  
  public boolean canBeCritical()
  {
    return false;
  }
  
  public void playHitSound() {}
  
  public void setIsCritical(boolean p_76986_0_)
  {
    if (canBeCritical()) {
      this.datawatcher.watch(16, Byte.valueOf((byte)(p_76986_0_ ? 1 : 0)));
    }
  }
  
  public boolean getIsCritical()
  {
    return (canBeCritical()) && (this.datawatcher.getByte(16) != 0);
  }
  
  public void setExtraDamage(float p_76986_0_)
  {
    this.extraDamage = p_76986_0_;
  }
  
  public void setKnockbackStrength(int p_76986_0_)
  {
    this.knockBack = p_76986_0_;
  }
  
  public void setPickupMode(int p_76986_0_)
  {
    this.pickupMode = p_76986_0_;
  }
  
  public int getPickupMode()
  {
    return this.pickupMode;
  }
  
  public boolean canPickup(EntityPlayer player)
  {
    if (this.pickupMode == 1) {
      return true;
    }
    if (this.pickupMode == 2) {
      return player.abilities.canInstantlyBuild;
    }
    return false;
  }
  
  public void onCollideWithPlayer(EntityPlayer player)
  {
    if ((this.inGround) && (canPickup(player)) && (!this.world.isStatic))
    {
      ItemStack item = getPickupItem();
      if (item == null) {
        return;
      }
      if (((this.pickupMode == 2) && (player.abilities.canInstantlyBuild)) || (player.inventory.pickup(item)))
      {
        this.world.makeSound(this, "randomom.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        die();
      }
    }
  }
  
  public float getShadowSize()
  {
    return 0.0F;
  }
  
  protected boolean canTriggerWalking()
  {
    return false;
  }
  
  public void a(NBTTagCompound nbttagcompound)
  {
    nbttagcompound.setShort("xTile", (short)this.xTile);
    nbttagcompound.setShort("yTile", (short)this.yTile);
    nbttagcompound.setShort("zTile", (short)this.zTile);
    nbttagcompound.setByte("inTile", (byte)Block.getId(this.block));
    nbttagcompound.setByte("inData", (byte)this.inData);
    nbttagcompound.setBoolean("inGround", this.inGround);
    nbttagcompound.setBoolean("beenInGround", this.beenInGround);
    nbttagcompound.setByte("pickup", (byte)this.pickupMode);
  }
  
  public void b(NBTTagCompound nbttagcompound)
  {
    this.xTile = nbttagcompound.getShort("xTile");
    this.yTile = nbttagcompound.getShort("yTile");
    this.zTile = nbttagcompound.getShort("zTile");
    this.block = Block.getById(nbttagcompound.getByte("inTile") & 0xFF);
    this.inData = (nbttagcompound.getByte("inData") & 0xFF);
    this.inGround = nbttagcompound.getBoolean("inGround");
    this.beenInGround = nbttagcompound.getBoolean("beenInGrond");
    this.pickupMode = nbttagcompound.getByte("pickup");
  }
}
