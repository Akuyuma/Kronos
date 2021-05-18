package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.PluginManager;
import org.github.paperspigot.PaperSpigotWorldConfig;
import org.spigotmc.AntiXray;
import org.spigotmc.SpigotWorldConfig;

public class Explosion
{
  public boolean a;
  public boolean b = true;
  private int i = 16;
  private Random j = new Random();
  private World world;
  public double posX;
  public double posY;
  public double posZ;
  public Entity source;
  public float size;
  public List blocks = new ArrayList();
  private Map l = new HashMap();
  public boolean wasCanceled = false;
  public boolean dynamite = false;
  
  public Explosion(World world, Entity entity, double d0, double d1, double d2, float f)
  {
    this.world = world;
    this.source = entity;
    this.size = ((float)Math.max(f, 0.0D));
    this.posX = d0;
    this.posY = d1;
    this.posZ = d2;
  }
  
  public Explosion(World world, Entity entity, double d0, double d1, double d2, float f, boolean dynamite)
  {
    this.world = world;
    this.source = entity;
    this.size = ((float)Math.max(f, 0.0D));
    this.posX = d0;
    this.posY = d1;
    this.posZ = d2;
    this.dynamite = dynamite;
  }
  
  public void a()
  {
    if (this.size < 0.1F) {
      return;
    }
    float f = this.size;
    HashSet hashset = new HashSet();
    for (int i = 0; i < this.i; i++) {
      for (int j = 0; j < this.i; j++) {
        for (int k = 0; k < this.i; k++) {
          if ((i == 0) || (i == this.i - 1) || (j == 0) || (j == this.i - 1) || (k == 0) || (k == this.i - 1))
          {
            double d3 = i / (this.i - 1.0F) * 2.0F - 1.0F;
            double d4 = j / (this.i - 1.0F) * 2.0F - 1.0F;
            double d5 = k / (this.i - 1.0F) * 2.0F - 1.0F;
            double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
            
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            float f1 = this.size * (0.7F + this.world.random.nextFloat() * 0.6F);
            
            double d0 = this.posX;
            double d1 = this.posY;
            double d2 = this.posZ;
            for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F)
            {
              int l = MathHelper.floor(d0);
              int i1 = MathHelper.floor(d1);
              int j1 = MathHelper.floor(d2);
              Block block = this.world.getType(l, i1, j1);
              if (block.getMaterial() != Material.AIR) {
                if (!this.dynamite)
                {
                  float f3 = this.source != null ? this.source.a(this, this.world, l, i1, j1, block) : block.a(this.source);
                  f1 -= (f3 + 0.3F) * f2;
                }
                else if (block != Block.getById(186))
                {
                  float f3 = this.source != null ? this.source.a(this, this.world, l, i1, j1, block) : block.a(this.source);
                  f1 -= (f3 + 0.3F) * f2;
                }
              }
              if ((f1 > 0.0F) && ((this.source == null) || (this.source.a(this, this.world, l, i1, j1, block, f1))) && (i1 < 256) && (i1 >= 0)) {
                hashset.add(new ChunkPosition(l, i1, j1));
              }
              d0 += d3 * f2;
              d1 += d4 * f2;
              d2 += d5 * f2;
            }
          }
        }
      }
    }
    this.blocks.addAll(hashset);
    this.size *= 2.0F;
    i = MathHelper.floor(this.posX - this.size - 1.0D);
    int j = MathHelper.floor(this.posX + this.size + 1.0D);
    int k = MathHelper.floor(this.posY - this.size - 1.0D);
    int k1 = MathHelper.floor(this.posY + this.size + 1.0D);
    int l1 = MathHelper.floor(this.posZ - this.size - 1.0D);
    int i2 = MathHelper.floor(this.posZ + this.size + 1.0D);
    
    List list = this.world.getEntities(this.source, AxisAlignedBB.a(i, k, l1, j, k1, i2), new IEntitySelector()
    {
      public boolean a(Entity entity)
      {
        return !entity.dead;
      }
    });
    Vec3D vec3d = Vec3D.a(this.posX, this.posY, this.posZ);
    for (int j2 = 0; j2 < list.size(); j2++)
    {
      Entity entity = (Entity)list.get(j2);
      double d7 = entity.f(this.posX, this.posY, this.posZ) / this.size;
      if (d7 <= 1.0D)
      {
        double d0 = entity.locX - this.posX;
        double d1 = entity.locY + entity.getHeadHeight() - this.posY;
        double d2 = entity.locZ - this.posZ;
        double d8 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        if (d8 != 0.0D)
        {
          d0 /= d8;
          d1 /= d8;
          d2 /= d8;
          double d9 = getBlockDensity(vec3d, entity.boundingBox);
          double d10 = (1.0D - d7) * d9;
          
          CraftEventFactory.entityDamage = this.source;
          entity.damageEntity(DamageSource.explosion(this), (int)((d10 * d10 + d10) / 2.0D * 8.0D * this.size + 1.0D));
          
          CraftEventFactory.entityDamage = null;
          
          double d11 = ((entity instanceof EntityHuman)) && (this.world.paperSpigotConfig.disableExplosionKnockback) ? 0.0D : EnchantmentProtection.a(entity, d10);
          
          entity.motX += d0 * d11;
          entity.motY += d1 * d11;
          entity.motZ += d2 * d11;
          if (((entity instanceof EntityHuman)) && (!this.world.paperSpigotConfig.disableExplosionKnockback)) {
            this.l.put((EntityHuman)entity, Vec3D.a(d0 * d10, d1 * d10, d2 * d10));
          }
        }
      }
    }
    this.size = f;
  }
  
  public void a(boolean flag)
  {
    float volume = (this.source instanceof EntityTNTPrimed) ? this.world.paperSpigotConfig.tntExplosionVolume : 4.0F;
    this.world.makeSound(this.posX, this.posY, this.posZ, "random.explode", volume, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
    if ((this.size >= 2.0F) && (this.b)) {
      this.world.addParticle("hugeexplosion", this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
    } else {
      this.world.addParticle("largeexplode", this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
    }
    if (this.b)
    {
      org.bukkit.World bworld = this.world.getWorld();
      org.bukkit.entity.Entity explode = this.source == null ? null : this.source.getBukkitEntity();
      Location location = new Location(bworld, this.posX, this.posY, this.posZ);
      
      List<org.bukkit.block.Block> blockList = new ArrayList();
      org.bukkit.block.Block bblock;
      for (int i1 = this.blocks.size() - 1; i1 >= 0; i1--)
      {
        ChunkPosition cpos = (ChunkPosition)this.blocks.get(i1);
        bblock = bworld.getBlockAt(cpos.x, cpos.y, cpos.z);
        if (bblock.getType() != org.bukkit.Material.AIR) {
          blockList.add(bblock);
        }
      }
      EntityExplodeEvent event = new EntityExplodeEvent(explode, location, blockList, 0.3F);
      this.world.getServer().getPluginManager().callEvent(event);
      
      this.blocks.clear();
      for (org.bukkit.block.Block bblock1 : event.blockList())
      {
        ChunkPosition coords = new ChunkPosition(bblock1.getX(), bblock1.getY(), bblock1.getZ());
        this.blocks.add(coords);
      }
      if (event.isCancelled())
      {
        this.wasCanceled = true;
        return;
      }
      Iterator iterator = this.blocks.iterator();
      while (iterator.hasNext())
      {
        ChunkPosition chunkposition = (ChunkPosition)iterator.next();
        int i = chunkposition.x;
        int j = chunkposition.y;
        int k = chunkposition.z;
        Block block = this.world.getType(i, j, k);
        this.world.spigotConfig.antiXrayInstance.updateNearbyBlocks(this.world, i, j, k);
        if (flag)
        {
          double d0 = i + this.world.random.nextFloat();
          double d1 = j + this.world.random.nextFloat();
          double d2 = k + this.world.random.nextFloat();
          double d3 = d0 - this.posX;
          double d4 = d1 - this.posY;
          double d5 = d2 - this.posZ;
          double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
          
          d3 /= d6;
          d4 /= d6;
          d5 /= d6;
          double d7 = 0.5D / (d6 / this.size + 0.1D);
          
          d7 *= (this.world.random.nextFloat() * this.world.random.nextFloat() + 0.3F);
          d3 *= d7;
          d4 *= d7;
          d5 *= d7;
          this.world.addParticle("explode", (d0 + this.posX * 1.0D) / 2.0D, (d1 + this.posY * 1.0D) / 2.0D, (d2 + this.posZ * 1.0D) / 2.0D, d3, d4, d5);
          this.world.addParticle("smoke", d0, d1, d2, d3, d4, d5);
        }
        if (block.getMaterial() != Material.AIR)
        {
          if (block.a(this)) {
            block.dropNaturally(this.world, i, j, k, this.world.getData(i, j, k), event.getYield(), 0);
          }
          this.world.setTypeAndData(i, j, k, Blocks.AIR, 0, 3);
          block.wasExploded(this.world, i, j, k, this);
        }
      }
    }
    if (this.a)
    {
      Iterator iterator = this.blocks.iterator();
      while (iterator.hasNext())
      {
        ChunkPosition chunkposition = (ChunkPosition)iterator.next();
        int i = chunkposition.x;
        int j = chunkposition.y;
        int k = chunkposition.z;
        Block block = this.world.getType(i, j, k);
        Block block1 = this.world.getType(i, j - 1, k);
        if ((block.getMaterial() == Material.AIR) && (block1.j()) && (this.j.nextInt(3) == 0)) {
          if (!CraftEventFactory.callBlockIgniteEvent(this.world, i, j, k, this).isCancelled()) {
            this.world.setTypeUpdate(i, j, k, Blocks.FIRE);
          }
        }
      }
    }
  }
  
  public Map b()
  {
    return this.l;
  }
  
  public EntityLiving c()
  {
    return (this.source instanceof EntityLiving) ? (EntityLiving)this.source : (this.source instanceof EntityTNTPrimed) ? ((EntityTNTPrimed)this.source).getSource() : this.source == null ? null : null;
  }
  
  private float getBlockDensity(Vec3D vec3d, AxisAlignedBB aabb)
  {
    if (!this.world.paperSpigotConfig.optimizeExplosions) {
      return this.world.a(vec3d, aabb);
    }
    CacheKey key = new CacheKey(this, aabb);
    Float blockDensity = (Float)this.world.explosionDensityCache.get(key);
    if (blockDensity == null)
    {
      blockDensity = Float.valueOf(this.world.a(vec3d, aabb));
      this.world.explosionDensityCache.put(key, blockDensity);
    }
    return blockDensity.floatValue();
  }
  
  static class CacheKey
  {
    private final World world;
    private final double posX;
    private final double posY;
    private final double posZ;
    private final double minX;
    private final double minY;
    private final double minZ;
    private final double maxX;
    private final double maxY;
    private final double maxZ;
    
    public CacheKey(Explosion explosion, AxisAlignedBB aabb)
    {
      this.world = explosion.world;
      this.posX = explosion.posX;
      this.posY = explosion.posY;
      this.posZ = explosion.posZ;
      this.minX = aabb.a;
      this.minY = aabb.b;
      this.minZ = aabb.c;
      this.maxX = aabb.d;
      this.maxY = aabb.e;
      this.maxZ = aabb.f;
    }
    
    public boolean equals(Object o)
    {
      if (this == o) {
        return true;
      }
      if ((o == null) || (getClass() != o.getClass())) {
        return false;
      }
      CacheKey cacheKey = (CacheKey)o;
      if (Double.compare(cacheKey.posX, this.posX) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.posY, this.posY) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.posZ, this.posZ) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.minX, this.minX) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.minY, this.minY) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.minZ, this.minZ) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.maxX, this.maxX) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.maxY, this.maxY) != 0) {
        return false;
      }
      if (Double.compare(cacheKey.maxZ, this.maxZ) != 0) {
        return false;
      }
      return this.world.equals(cacheKey.world);
    }
    
    public int hashCode()
    {
      int result = this.world.hashCode();
      long temp = Double.doubleToLongBits(this.posX);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.posY);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.posZ);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.minX);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.minY);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.minZ);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.maxX);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.maxY);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.maxZ);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      return result;
    }
  }
}
