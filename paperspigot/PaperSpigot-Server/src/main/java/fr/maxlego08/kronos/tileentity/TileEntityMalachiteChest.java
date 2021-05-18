package fr.maxlego08.kronos.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.HumanEntity;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Blocks;
import net.minecraft.server.ContainerChest;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.TileEntity;

public class TileEntityMalachiteChest extends TileEntity
implements IInventory
{
	private ItemStack[] items = new ItemStack[54];
	public boolean a;
	public TileEntityMalachiteChest i;
	public TileEntityMalachiteChest j;
	public TileEntityMalachiteChest k;
	public TileEntityMalachiteChest l;
	public float m;
	public float n;
	public int o;
	private int ticks;
	private int r = -1;
	private String s;
	public List<HumanEntity> transaction = new ArrayList();
	private int maxStack = 64;

public ItemStack[] getContents()
{
  return this.items;
}

public void onOpen(CraftHumanEntity who)
{
  this.transaction.add(who);
}

public void onClose(CraftHumanEntity who)
{
  this.transaction.remove(who);
}

public List<HumanEntity> getViewers()
{
  return this.transaction;
}

public void setMaxStackSize(int size)
{
  this.maxStack = size;
}

public int getSize()
{
  return 36;
}

public ItemStack getItem(int i)
{
  return this.items[i];
}

public ItemStack splitStack(int i, int j)
{
  if (this.items[i] != null)
  {
    if (this.items[i].count <= j)
    {
      ItemStack itemstack = this.items[i];
      this.items[i] = null;
      update();
      return itemstack;
    }
    ItemStack itemstack = this.items[i].a(j);
    if (this.items[i].count == 0) {
      this.items[i] = null;
    }
    update();
    return itemstack;
  }
  return null;
}

public ItemStack splitWithoutUpdate(int i)
{
  if (this.items[i] != null)
  {
    ItemStack itemstack = this.items[i];
    
    this.items[i] = null;
    return itemstack;
  }
  return null;
}

public void setItem(int i, ItemStack itemstack)
{
  this.items[i] = itemstack;
  if ((itemstack != null) && (itemstack.count > getMaxStackSize())) {
    itemstack.count = getMaxStackSize();
  }
  update();
}

public String getInventoryName()
{
  return k_() ? this.s : "container.malachitechest";
}

public boolean k_()
{
  return (this.s != null) && (this.s.length() > 0);
}

public void a(String s)
{
  this.s = s;
}

public void a(NBTTagCompound nbttagcompound)
{
  super.a(nbttagcompound);
  NBTTagList nbttaglist = nbttagcompound.getList("Items", 10);
  
  this.items = new ItemStack[getSize()];
  if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
    this.s = nbttagcompound.getString("CustomName");
  }
  for (int i = 0; i < nbttaglist.size(); i++)
  {
    NBTTagCompound nbttagcompound1 = nbttaglist.get(i);
    int j = nbttagcompound1.getByte("Slot") & 0xFF;
    if ((j >= 0) && (j < this.items.length)) {
      this.items[j] = ItemStack.createStack(nbttagcompound1);
    }
  }
}

public void b(NBTTagCompound nbttagcompound)
{
  super.b(nbttagcompound);
  NBTTagList nbttaglist = new NBTTagList();
  for (int i = 0; i < this.items.length; i++) {
    if (this.items[i] != null)
    {
      NBTTagCompound nbttagcompound1 = new NBTTagCompound();
      
      nbttagcompound1.setByte("Slot", (byte)i);
      this.items[i].save(nbttagcompound1);
      nbttaglist.add(nbttagcompound1);
    }
  }
  nbttagcompound.set("Items", nbttaglist);
  if (k_()) {
    nbttagcompound.setString("CustomName", this.s);
  }
}

public int getMaxStackSize()
{
  return this.maxStack;
}

public boolean a(EntityHuman entityhuman)
{
  if (this.world == null) {
    return true;
  }
  return this.world.getTileEntity(this.x, this.y, this.z) == this;
}

public void u()
{
  super.u();
  this.a = false;
}

private boolean a(int i, int j, int k)
{
  if (this.world == null) {
    return false;
  }
  return true;
}

public void h()
{
  super.h();
  if (this.world == null) {
    return;
  }
  this.ticks += 1;
  if ((!this.world.isStatic) && (this.o != 0) && ((this.ticks + this.x + this.y + this.z) % 10 == 0))
  {
    this.o = 0;
    float f = 5.0F;
    List list = this.world.a(EntityHuman.class, AxisAlignedBB.a(this.x - f, this.y - f, this.z - f, this.x + 1 + f, this.y + 1 + f, this.z + 1 + f));
    Iterator iterator = list.iterator();
    while (iterator.hasNext())
    {
      EntityHuman entityhuman = (EntityHuman)iterator.next();
      if ((entityhuman.activeContainer instanceof ContainerChest))
      {
        IInventory iinventory = ((ContainerChest)entityhuman.activeContainer).e();
        if ((iinventory == this) || (((iinventory instanceof InventoryLargeChest)) && (((InventoryLargeChest)iinventory).a(this)))) {
          this.o += 1;
        }
      }
    }
  }
  this.n = this.m;
}

public boolean c(int i, int j)
{
  if (i == 1)
  {
    this.o = j;
    return true;
  }
  return super.c(i, j);
}

public void startOpen()
{
  if (this.o < 0) {
    this.o = 0;
  }
  int oldPower = Math.max(0, Math.min(15, this.o));
  
  this.o += 1;
  if (this.world == null) {
    return;
  }
  this.world.playBlockAction(this.x, this.y, this.z, q(), 1, this.o);
  if ((this.o > 0) && (this.m == 0.0F) && (this.i == null) && (this.k == null))
  {
    double d1 = this.x + 0.5D;
    
    double d0 = this.z + 0.5D;
    if (this.l != null) {
      d0 += 0.5D;
    }
    if (this.j != null) {
      d1 += 0.5D;
    }
    this.world.makeSound(d1, this.y + 0.5D, d0, "random.chestopen", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
  }
  if (q() == Blocks.TRAPPED_CHEST)
  {
    int newPower = Math.max(0, Math.min(15, this.o));
    if (oldPower != newPower) {
      CraftEventFactory.callRedstoneChange(this.world, this.x, this.y, this.z, oldPower, newPower);
    }
  }
  this.world.applyPhysics(this.x, this.y, this.z, q());
  this.world.applyPhysics(this.x, this.y - 1, this.z, q());
}

public void closeContainer()
{
  int oldPower = Math.max(0, Math.min(15, this.o));
  this.o -= 1;
  if (this.world == null) {
    return;
  }
  this.world.playBlockAction(this.x, this.y, this.z, q(), 1, this.o);
  if ((this.o == 0) && (this.i == null) && (this.k == null))
  {
    double d0 = this.x + 0.5D;
    double d2 = this.z + 0.5D;
    if (this.l != null) {
      d2 += 0.5D;
    }
    if (this.j != null) {
      d0 += 0.5D;
    }
    this.world.makeSound(d0, this.y + 0.5D, d2, "random.chestclosed", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
  }
  this.world.applyPhysics(this.x, this.y, this.z, q());
  this.world.applyPhysics(this.x, this.y - 1, this.z, q());
}

public boolean b(int i, ItemStack itemstack)
{
  return true;
}

public void s()
{
  super.s();
  u();
}
	
}
