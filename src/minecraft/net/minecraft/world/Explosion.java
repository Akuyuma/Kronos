package net.minecraft.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.maxlego08.kronos.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Explosion
{
  public boolean isFlaming;
  public boolean isSmoking = true;
  private int field_77289_h = 16;
  private Random explosionRNG = new Random();
  private World worldObj;
  public double explosionX;
  public double explosionY;
  public double explosionZ;
  public Entity exploder;
  public float explosionSize;
  public boolean dynamite = false;
  public List affectedBlockPositions = new ArrayList();
  private Map field_77288_k = new HashMap();
  private static final String __OBFID = "CL_00000134";
  
  public Explosion(World p_i1948_1_, Entity p_i1948_2_, double p_i1948_3_, double p_i1948_5_, double p_i1948_7_, float p_i1948_9_)
  {
    this.worldObj = p_i1948_1_;
    this.exploder = p_i1948_2_;
    this.explosionSize = p_i1948_9_;
    this.explosionX = p_i1948_3_;
    this.explosionY = p_i1948_5_;
    this.explosionZ = p_i1948_7_;
  }
  
  public Explosion(World p_i1948_1_, Entity p_i1948_2_, double p_i1948_3_, double p_i1948_5_, double p_i1948_7_, float p_i1948_9_, boolean dynamite)
  {
    this.worldObj = p_i1948_1_;
    this.exploder = p_i1948_2_;
    this.explosionSize = p_i1948_9_;
    this.explosionX = p_i1948_3_;
    this.explosionY = p_i1948_5_;
    this.explosionZ = p_i1948_7_;
    this.dynamite = dynamite;
  }
  
  public void doExplosionA()
  {
    float var1 = this.explosionSize;
    HashSet var2 = new HashSet();
    for (int var3 = 0; var3 < this.field_77289_h; var3++) {
      for (int var4 = 0; var4 < this.field_77289_h; var4++) {
        for (int var5 = 0; var5 < this.field_77289_h; var5++) {
          if ((var3 == 0) || (var3 == this.field_77289_h - 1) || (var4 == 0) || (var4 == this.field_77289_h - 1) || (var5 == 0) || (var5 == this.field_77289_h - 1))
          {
            double var6 = var3 / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
            double var8 = var4 / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
            double var10 = var5 / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
            double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
            var6 /= var12;
            var8 /= var12;
            var10 /= var12;
            float var14 = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
            double var15 = this.explosionX;
            double var17 = this.explosionY;
            double var19 = this.explosionZ;
            for (float var21 = 0.3F; var14 > 0.0F; var14 -= var21 * 0.75F)
            {
              int var22 = MathHelper.floor_double(var15);
              int var23 = MathHelper.floor_double(var17);
              int var24 = MathHelper.floor_double(var19);
              Block var25 = this.worldObj.getBlock(var22, var23, var24);
              if (var25.getMaterial() != Material.air) {
                if (!this.dynamite)
                {
                  float var26 = this.exploder != null ? this.exploder.func_145772_a(this, this.worldObj, var22, var23, var24, var25) : var25.getExplosionResistance(this.exploder);
                  var14 -= (var26 + 0.3F) * var21;
                }
                else if (var25 != Block.getBlockById(186))
                {
                  float var26 = this.exploder != null ? this.exploder.func_145772_a(this, this.worldObj, var22, var23, var24, var25) : var25.getExplosionResistance(this.exploder);
                  var14 -= (var26 + 0.3F) * var21;
                }
              }
              if ((var14 > 0.0F) && ((this.exploder == null) || (this.exploder.func_145774_a(this, this.worldObj, var22, var23, var24, var25, var14)))) {
                var2.add(new ChunkPosition(var22, var23, var24));
              }
              var15 += var6 * var21;
              var17 += var8 * var21;
              var19 += var10 * var21;
            }
          }
        }
      }
    }
    this.affectedBlockPositions.addAll(var2);
    this.explosionSize *= 2.0F;
    double var3 = MathHelper.floor_double(this.explosionX - this.explosionSize - 1.0D);
    int var4 = MathHelper.floor_double(this.explosionX + this.explosionSize + 1.0D);
    int var5 = MathHelper.floor_double(this.explosionY - this.explosionSize - 1.0D);
    int var29 = MathHelper.floor_double(this.explosionY + this.explosionSize + 1.0D);
    int var7 = MathHelper.floor_double(this.explosionZ - this.explosionSize - 1.0D);
    int var30 = MathHelper.floor_double(this.explosionZ + this.explosionSize + 1.0D);
    List var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getBoundingBox(var3, var5, var7, var4, var29, var30));
    Vec3 var31 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);
    for (int var11 = 0; var11 < var9.size(); var11++)
    {
      Entity var32 = (Entity)var9.get(var11);
      double var13 = var32.getDistance(this.explosionX, this.explosionY, this.explosionZ) / this.explosionSize;
      if (var13 <= 1.0D)
      {
        double var15 = var32.posX - this.explosionX;
        double var17 = var32.posY + var32.getEyeHeight() - this.explosionY;
        double var19 = var32.posZ - this.explosionZ;
        double var33 = MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
        if (var33 != 0.0D)
        {
          var15 /= var33;
          var17 /= var33;
          var19 /= var33;
          double var34 = this.worldObj.getBlockDensity(var31, var32.boundingBox);
          double var35 = (1.0D - var13) * var34;
          var32.attackEntityFrom(DamageSource.setExplosionSource(this), (int)((var35 * var35 + var35) / 2.0D * 8.0D * this.explosionSize + 1.0D));
          double var27 = EnchantmentProtection.func_92092_a(var32, var35);
          var32.motionX += var15 * var27;
          var32.motionY += var17 * var27;
          var32.motionZ += var19 * var27;
          if ((var32 instanceof EntityPlayer)) {
            this.field_77288_k.put((EntityPlayer)var32, Vec3.createVectorHelper(var15 * var35, var17 * var35, var19 * var35));
          }
        }
      }
    }
    this.explosionSize = var1;
  }
  
  public void doExplosionB(boolean p_77279_1_)
  {
    this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
    if ((this.explosionSize >= 2.0F) && (this.isSmoking)) {
      this.worldObj.spawnParticle("hugeexplosion", this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
    } else {
      this.worldObj.spawnParticle("largeexplode", this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
    }
    if (this.isSmoking)
    {
      Iterator var2 = this.affectedBlockPositions.iterator();
      while (var2.hasNext())
      {
        ChunkPosition var3 = (ChunkPosition)var2.next();
        int var4 = var3.field_151329_a;
        int var5 = var3.field_151327_b;
        int var6 = var3.field_151328_c;
        Block var7 = this.worldObj.getBlock(var4, var5, var6);
        if (p_77279_1_)
        {
          double var8 = var4 + this.worldObj.rand.nextFloat();
          double var10 = var5 + this.worldObj.rand.nextFloat();
          double var12 = var6 + this.worldObj.rand.nextFloat();
          double var14 = var8 - this.explosionX;
          double var16 = var10 - this.explosionY;
          double var18 = var12 - this.explosionZ;
          double var20 = MathHelper.sqrt_double(var14 * var14 + var16 * var16 + var18 * var18);
          var14 /= var20;
          var16 /= var20;
          var18 /= var20;
          double var22 = 0.5D / (var20 / this.explosionSize + 0.1D);
          var22 *= (this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
          var14 *= var22;
          var16 *= var22;
          var18 *= var22;
          this.worldObj.spawnParticle("explode", (var8 + this.explosionX * 1.0D) / 2.0D, (var10 + this.explosionY * 1.0D) / 2.0D, (var12 + this.explosionZ * 1.0D) / 2.0D, var14, var16, var18);
          this.worldObj.spawnParticle("smoke", var8, var10, var12, var14, var16, var18);
        }
        if (var7.getMaterial() != Material.air)
        {
          if (var7.canDropFromExplosion(this)) {
            var7.dropBlockAsItemWithChance(this.worldObj, var4, var5, var6, this.worldObj.getBlockMetadata(var4, var5, var6), 1.0F / this.explosionSize, 0);
          }
          this.worldObj.setBlock(var4, var5, var6, Blocks.air, 0, 3);
          var7.onBlockDestroyedByExplosion(this.worldObj, var4, var5, var6, this);
        }
      }
    }
    if (this.isFlaming)
    {
      Iterator var2 = this.affectedBlockPositions.iterator();
      while (var2.hasNext())
      {
        ChunkPosition var3 = (ChunkPosition)var2.next();
        int var4 = var3.field_151329_a;
        int var5 = var3.field_151327_b;
        int var6 = var3.field_151328_c;
        Block var7 = this.worldObj.getBlock(var4, var5, var6);
        Block var24 = this.worldObj.getBlock(var4, var5 - 1, var6);
        if ((var7.getMaterial() == Material.air) && (var24.func_149730_j()) && (this.explosionRNG.nextInt(3) == 0)) {
          this.worldObj.setBlock(var4, var5, var6, Blocks.fire);
        }
      }
    }
  }
  
  public Map func_77277_b()
  {
    return this.field_77288_k;
  }
  
  public EntityLivingBase getExplosivePlacedBy()
  {
    return (this.exploder instanceof EntityLivingBase) ? (EntityLivingBase)this.exploder : (this.exploder instanceof EntityTNTPrimed) ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy() : this.exploder == null ? null : null;
  }
}
