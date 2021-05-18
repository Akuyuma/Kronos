package fr.maxlego08.kronos.blocks;

import java.util.Iterator;
import java.util.Random;

import fr.maxlego08.kronos.tileentity.TileEntityAngeliteChest;
import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockContainer;
import net.minecraft.server.Container;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityItem;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityOcelot;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.MathHelper;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.TileEntity;
import net.minecraft.server.World;

public class BlockAngeliteChest extends BlockContainer
{
	  private final Random b = new Random();
	  public final int a;
	  
	  protected BlockAngeliteChest(int i)
	  {
	    super(Material.STONE);
	    this.a = i;
	    a(CreativeModeTab.c);
	    a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	    c(3.0F);
	    b(10.0F);
	  }
	  
	  public boolean c()
	  {
	    return false;
	  }
	  
	  public boolean d()
	  {
	    return false;
	  }
	  
	  public int b()
	  {
	    return 22;
	  }
	  
	  public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
	  {
	    if (iblockaccess.getType(i, j, k - 1) == this) {
	      a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
	    } else if (iblockaccess.getType(i, j, k + 1) == this) {
	      a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
	    } else if (iblockaccess.getType(i - 1, j, k) == this) {
	      a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	    } else if (iblockaccess.getType(i + 1, j, k) == this) {
	      a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
	    } else {
	      a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	    }
	  }
	  
	  private boolean n(World world, int i, int j, int k)
	  {
	    return world.getType(i, j, k) == this;
	  }
	  
	  public void doPhysics(World world, int i, int j, int k, Block block)
	  {
	    super.doPhysics(world, i, j, k, block);
	    TileEntityAngeliteChest TileEntityAsphateChest = (TileEntityAngeliteChest)world.getTileEntity(i, j, k);
	    if (TileEntityAsphateChest != null) {
	      TileEntityAsphateChest.u();
	    }
	  }
	  
	  public void remove(World world, int i, int j, int k, Block block, int l)
	  {
	    TileEntityAngeliteChest TileEntityAsphateChest = (TileEntityAngeliteChest)world.getTileEntity(i, j, k);
	    if (TileEntityAsphateChest != null)
	    {
	      for (int i1 = 0; i1 < TileEntityAsphateChest.getSize(); i1++)
	      {
	        ItemStack itemstack = TileEntityAsphateChest.getItem(i1);
	        if (itemstack != null)
	        {
	          float f = this.b.nextFloat() * 0.8F + 0.1F;
	          float f1 = this.b.nextFloat() * 0.8F + 0.1F;
	          EntityItem entityitem;
	          for (float f2 = this.b.nextFloat() * 0.8F + 0.1F; itemstack.count > 0; world.addEntity(entityitem))
	          {
	            int j1 = this.b.nextInt(21) + 10;
	            if (j1 > itemstack.count) {
	              j1 = itemstack.count;
	            }
	            itemstack.count -= j1;
	            entityitem = new EntityItem(world, i + f, j + f1, k + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getData()));
	            float f3 = 0.05F;
	            
	            entityitem.motX = ((float)this.b.nextGaussian() * f3);
	            entityitem.motY = ((float)this.b.nextGaussian() * f3 + 0.2F);
	            entityitem.motZ = ((float)this.b.nextGaussian() * f3);
	            if (itemstack.hasTag()) {
	              entityitem.getItemStack().setTag((NBTTagCompound)itemstack.getTag().clone());
	            }
	          }
	        }
	      }
	      world.updateAdjacentComparators(i, j, k, block);
	    }
	    super.remove(world, i, j, k, block, l);
	  }
	  
	  public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2)
	  {
	    if (world.isStatic) {
	      return true;
	    }
	    IInventory iinventory = m(world, i, j, k);
	    if (iinventory != null) {
	      entityhuman.openContainer(iinventory);
	    }
	    return true;
	  }
	  
	  public IInventory m(World world, int i, int j, int k)
	  {
	    Object object = (TileEntityAngeliteChest)world.getTileEntity(i, j, k);
	    return (IInventory)object;
	  }
	  
	  public TileEntity a(World world, int i)
	  {
	    TileEntityAngeliteChest TileEntityAsphateChest = new TileEntityAngeliteChest();
	    
	    return TileEntityAsphateChest;
	  }
	  
	  public boolean isPowerSource()
	  {
	    return this.a == 1;
	  }
	  
	  public int b(IBlockAccess iblockaccess, int i, int j, int k, int l)
	  {
	    if (!isPowerSource()) {
	      return 0;
	    }
	    int i1 = ((TileEntityAngeliteChest)iblockaccess.getTileEntity(i, j, k)).o;
	    
	    return MathHelper.a(i1, 0, 15);
	  }
	  
	  public int c(IBlockAccess iblockaccess, int i, int j, int k, int l)
	  {
	    return l == 1 ? b(iblockaccess, i, j, k, l) : 0;
	  }
	  
	  private static boolean o(World world, int i, int j, int k)
	  {
	    Iterator iterator = world.a(EntityOcelot.class, AxisAlignedBB.a(i, j + 1, k, i + 1, j + 2, k + 1)).iterator();
	    EntityOcelot entityocelot;
	    do
	    {
	      if (!iterator.hasNext()) {
	        return false;
	      }
	      Entity entity = (Entity)iterator.next();
	      
	      entityocelot = (EntityOcelot)entity;
	    } while (!entityocelot.isSitting());
	    return true;
	  }
	  
	  public boolean isComplexRedstone()
	  {
	    return true;
	  }
	  
	  public int g(World world, int i, int j, int k, int l)
	  {
	    return Container.b(m(world, i, j, k));
	  }
	  
	  public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack)
	  {
	    byte b0 = 0;
	    int l = MathHelper.floor(entityliving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
	    if (l == 0) {
	      b0 = 2;
	    }
	    if (l == 1) {
	      b0 = 5;
	    }
	    if (l == 2) {
	      b0 = 3;
	    }
	    if (l == 3) {
	      b0 = 4;
	    }
	    world.setData(i, j, k, b0, 3);
	  }
	
}
