package fr.maxlego08.kronos.entities;

import fr.maxlego08.kronos.utils.EntityCustomProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDynamite
extends EntityCustomProjectile
{
	private int explodefuse;
	private boolean extinguished;
	public boolean stuck = true;
	public boolean dynamite = false;
	
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
	
	public EntityDynamite(World world, EntityLivingBase entityliving, int i, boolean dynamite)
	{
		  this(world);
		  setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		  this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.141593F) * 0.56F;
		  this.posY -= 0.1D;
		  this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.141593F) * 0.56F;
		  setPosition(this.posX, this.posY, this.posZ);
		  this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F));
		  this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F));
		  this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.141593F) * 2);
		  setThrowableHeading(this.motionX, this.motionY, this.motionZ, 0.7F, 4.0F);
		  this.explodefuse = i;
		  this.dynamite = dynamite;
	}
	
	public void onUpdate()
	{
	  super.onUpdate();
	  if ((!this.inGround) && (!this.beenInGround)) {
		  this.rotationPitch += 15.0F;
	  } else {
		  this.rotationPitch = 50.0F;
	  }
	  if ((isInWater()) && (!this.extinguished))
	  {
	    this.extinguished = true;
	    this.worldObj.playSoundAtEntity(this, "random.fizz", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
	    for (int i = 0; i < 8; i++)
	    {
	    	float f = 0.25F;
	      	this.worldObj.spawnParticle("explode", this.posX - this.motionX * f, this.posY - this.motionY * f, this.posZ - this.motionZ * f, this.motionX, this.motionY, this.motionZ);
	    }
	  }
	  this.explodefuse -= 1;
	  if (this.explodefuse <= 0)
	  {
		  detonate();
	    	setDead();
	  }
	  else if (this.explodefuse > 0)
	  {
		  this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	  }
	}
	
	public void onGroundHit(MovingObjectPosition movingobjectposition)
	{
		  this.xTile = movingobjectposition.blockX;
		  this.yTile = movingobjectposition.blockY;
		  this.zTile = movingobjectposition.blockZ;
		  this.block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
		  this.motionX = ((float)(movingobjectposition.hitVec.xCoord - this.posX));
		  this.motionY = ((float)(movingobjectposition.hitVec.yCoord - this.posY));
		  this.motionZ = ((float)(movingobjectposition.hitVec.zCoord - this.posZ));
		  float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		  this.posX -= this.motionX / f1 * 0.05D;
		  this.posY -= this.motionY / f1 * 0.05D;
		  this.posZ -= this.motionZ / f1 * 0.05D;
		  
		  this.motionX *= -0.2D;
		  this.motionZ *= -0.2D;
		  if (movingobjectposition.sideHit == 1)
		  {
			  this.inGround = true;
			  this.beenInGround = true;
		  }
		  else
		  {
			  this.inGround = false;
			  this.worldObj.playSoundAtEntity(this, "random.fizz", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		  }
		  if (this.block != null) {
			  this.block.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
		  }
	}
	
	private void detonate()
	{
	  if (!this.worldObj.isClient) {
	    if (!this.dynamite)
	    {
	      float f = 3.5F;
	      this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, true);
	    }
	    else
	    {
	      float f = 2.5F;
	      this.worldObj.createExplosionCustom(this, this.posX, this.posY, this.posZ, f, true, true);
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
	
	public void playHitSound()
	{
		this.worldObj.playSoundAtEntity(this, "random.fizz", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
	}
	
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		  super.writeEntityToNBT(nbttagcompound);
		  nbttagcompound.setByte("fuse", (byte)this.explodefuse);
		  nbttagcompound.setBoolean("off", this.extinguished);
	}
	
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		  super.readEntityFromNBT(nbttagcompound);
		  this.explodefuse = nbttagcompound.getByte("fuse");
		  this.extinguished = nbttagcompound.getBoolean("off");
	}
}
