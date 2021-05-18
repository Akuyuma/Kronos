package fr.maxlego08.kronos.utils;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

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
    this.yOffset = 0.0F;
    this.pickupMode = 0;
    this.extraDamage = 0.0F;
    this.knockBack = 0;
    setSize(0.5F, 0.5F);
  }
  
  protected void entityInit()
  {
    this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
  }
  
  protected void setPickupModeFromEntity(EntityLivingBase entityliving)
  {
    if ((entityliving instanceof EntityPlayer))
    {
      if (((EntityPlayer)entityliving).capabilities.isCreativeMode) {
        setPickupMode(2);
      }
    }
    else {
      setPickupMode(0);
    }
  }
  
  public void setThrowableHeading(double p_76986_0_, double p_76986_1_, double p_76986_2_, float p_76986_3_, float p_76986_4_)
  {
    float f2 = MathHelper.sqrt_double(p_76986_0_ * p_76986_0_ + p_76986_1_ * p_76986_1_ + p_76986_2_ * p_76986_2_);
    p_76986_0_ /= f2;
    p_76986_1_ /= f2;
    p_76986_2_ /= f2;
    p_76986_0_ += this.rand.nextGaussian() * 0.007499999832361937D * p_76986_4_;
    p_76986_1_ += this.rand.nextGaussian() * 0.007499999832361937D * p_76986_4_;
    p_76986_2_ += this.rand.nextGaussian() * 0.007499999832361937D * p_76986_4_;
    p_76986_0_ *= p_76986_3_;
    p_76986_1_ *= p_76986_3_;
    p_76986_2_ *= p_76986_3_;
    this.motionX = p_76986_0_;
    this.motionY = p_76986_1_;
    this.motionZ = p_76986_2_;
    float f3 = MathHelper.sqrt_double(p_76986_0_ * p_76986_0_ + p_76986_2_ * p_76986_2_);
    this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(p_76986_0_, p_76986_2_) * 180.0D / 3.141592653589793D));
    this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(p_76986_1_, f3) * 180.0D / 3.141592653589793D));
    this.ticksInGround = 0;
  }
  
  public void setVelocity(double p_76986_1_, double p_76986_2_, double p_76986_3_)
  {
    this.motionX = p_76986_1_;
    this.motionY = p_76986_2_;
    this.motionZ = p_76986_3_;
    if ((aimRotation()) && (this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F))
    {
      float f = MathHelper.sqrt_double(p_76986_1_ * p_76986_1_ + p_76986_3_ * p_76986_3_);
      this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(p_76986_1_, p_76986_3_) * 180.0D / 3.141592653589793D));
      this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(p_76986_2_, f) * 180.0D / 3.141592653589793D));
      setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      this.ticksInGround = 0;
    }
  }
  
  public void onUpdate()
  {
    onEntityUpdate();
  }
  
  public void onEntityUpdate()
  {
    super.onEntityUpdate();
    if (aimRotation())
    {
      float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D));
      this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0D / 3.141592653589793D));
    }
    Block i = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
    if (i != null)
    {
      i.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
      AxisAlignedBB axisalignedbb = i.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
      if ((axisalignedbb != null) && (axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))) {
        this.inGround = true;
      }
    }
    if (this.inGround)
    {
      Block j = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
      int k = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
      if ((j == this.block) && (k == this.inData))
      {
        this.ticksInGround += 1;
        int t = getMaxLifetime();
        if ((t != 0) && (this.ticksInGround >= t)) {
          setDead();
        }
      }
      else
      {
        this.inGround = false;
        this.motionX *= this.rand.nextFloat() * 0.2F;
        this.motionY *= this.rand.nextFloat() * 0.2F;
        this.motionZ *= this.rand.nextFloat() * 0.2F;
        this.ticksInGround = 0;
        this.ticksInAir = 0;
      }
      return;
    }
    this.ticksInAir += 1;
    
    Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
    Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec3, vec31, false, true, false);
    vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
    vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    if (movingobjectposition != null) {
      vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
    }
    Entity entity = null;
    List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
    double d = 0.0D;
    for (int l = 0; l < list.size(); l++)
    {
      Entity entity1 = (Entity)list.get(l);
      if ((entity1.canBeCollidedWith()) && (this.ticksInAir >= 5))
      {
        float f4 = 0.3F;
        AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f4, f4, f4);
        MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);
        if (movingobjectposition1 != null)
        {
          double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
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
      if (movingobjectposition.entityHit != null) {
        onEntityHit(movingobjectposition.entityHit);
      } else {
        onGroundHit(movingobjectposition);
      }
    }
    if (getIsCritical()) {
      for (int i1 = 0; i1 < 2; i1++) {
        this.worldObj.spawnParticle("crit", this.posX + this.motionX * i1 / 4.0D, this.posY + this.motionY * i1 / 4.0D, this.posZ + this.motionZ * i1 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
      }
    }
    this.posX += this.motionX;
    this.posY += this.motionY;
    this.posZ += this.motionZ;
    if (aimRotation())
    {
      float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = ((float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D));
      for (this.rotationPitch = ((float)(Math.atan2(this.motionY, f2) * 180.0D / 3.141592653589793D)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {}
      while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
        this.prevRotationPitch += 360.0F;
      }
      while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
        this.prevRotationYaw -= 360.0F;
      }
      while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
        this.prevRotationYaw += 360.0F;
      }
      this.rotationPitch = (this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F);
      this.rotationYaw = (this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F);
    }
    float resistance = getAirResistance();
    float gravity = getGravity();
    this.motionX *= resistance;
    this.motionY *= resistance;
    this.motionZ *= resistance;
    this.motionY -= gravity;
    setPosition(this.posX, this.posY, this.posZ);
    func_145775_I();
  }
  
  public void onEntityHit(Entity entity)
  {
    bounceBack();
  }
  
  public void onGroundHit(MovingObjectPosition movingobjectposition)
  {
    this.xTile = movingobjectposition.blockX;
    this.yTile = movingobjectposition.blockY;
    this.zTile = movingobjectposition.blockZ;
    this.block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
    this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
    this.motionX = (movingobjectposition.hitVec.xCoord - this.posX);
    this.motionY = (movingobjectposition.hitVec.yCoord - this.posY);
    this.motionZ = (movingobjectposition.hitVec.zCoord - this.posZ);
    float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
    this.posX -= this.motionX / f1 * 0.05D;
    this.posY -= this.motionY / f1 * 0.05D;
    this.posZ -= this.motionZ / f1 * 0.05D;
    this.inGround = true;
    this.beenInGround = true;
    setIsCritical(false);
    playHitSound();
    if (this.block != null) {
      this.block.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
    }
  }
  
  protected void bounceBack()
  {
    this.motionX *= -0.1D;
    this.motionY *= -0.1D;
    this.motionZ *= -0.1D;
    this.rotationYaw += 180.0F;
    this.prevRotationYaw += 180.0F;
    this.ticksInAir = 0;
  }
  
  public double getTotalVelocity()
  {
    return Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
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
      this.dataWatcher.updateObject(16, Byte.valueOf((byte)(p_76986_0_ ? 1 : 0)));
    }
  }
  
  public boolean getIsCritical()
  {
    return (canBeCritical()) && (this.dataWatcher.getWatchableObjectByte(16) != 0);
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
      return player.capabilities.isCreativeMode;
    }
    return false;
  }
  
  public void onCollideWithPlayer(EntityPlayer player)
  {
    if ((this.inGround) && (canPickup(player)) && (!this.worldObj.isClient))
    {
      ItemStack item = getPickupItem();
      if (item == null) {
        return;
      }
      if (((this.pickupMode == 2) && (player.capabilities.isCreativeMode)) || (player.inventory.addItemStackToInventory(item)))
      {
        this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        setDead();
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
  
  public void writeEntityToNBT(NBTTagCompound nbttagcompound)
  {
    nbttagcompound.setShort("xTile", (short)this.xTile);
    nbttagcompound.setShort("yTile", (short)this.yTile);
    nbttagcompound.setShort("zTile", (short)this.zTile);
    nbttagcompound.setByte("inTile", (byte)Block.getIdFromBlock(this.block));
    nbttagcompound.setByte("inData", (byte)this.inData);
    nbttagcompound.setBoolean("inGround", this.inGround);
    nbttagcompound.setBoolean("beenInGround", this.beenInGround);
    nbttagcompound.setByte("pickup", (byte)this.pickupMode);
  }
  
  public void readEntityFromNBT(NBTTagCompound nbttagcompound)
  {
    this.xTile = nbttagcompound.getShort("xTile");
    this.yTile = nbttagcompound.getShort("yTile");
    this.zTile = nbttagcompound.getShort("zTile");
    this.block = Block.getBlockById(nbttagcompound.getByte("inTile") & 0xFF);
    this.inData = (nbttagcompound.getByte("inData") & 0xFF);
    this.inGround = nbttagcompound.getBoolean("inGround");
    this.beenInGround = nbttagcompound.getBoolean("beenInGrond");
    this.pickupMode = nbttagcompound.getByte("pickup");
  }
}
