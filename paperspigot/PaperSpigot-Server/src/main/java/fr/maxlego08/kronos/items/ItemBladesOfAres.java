package fr.maxlego08.kronos.items;

import net.minecraft.server.AttributeModifier;
import net.minecraft.server.Block;
import net.minecraft.server.Blocks;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EnumAnimation;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.GenericAttributes;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.MobEffect;
import net.minecraft.server.World;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ItemBladesOfAres  extends Item
{
	  private float damage;
	  private final EnumToolMaterial b;
	  
	  public ItemBladesOfAres(EnumToolMaterial paramEnumToolMaterial)
	  {
	    this.b = paramEnumToolMaterial;
	    this.maxStackSize = 1;
	    setMaxDurability(paramEnumToolMaterial.a());
	    a(CreativeModeTab.j);
	    
	    this.damage = (4.0F + paramEnumToolMaterial.c());
	  }
	  
	  public float i()
	  {
	    return this.b.c();
	  }
	  
	  public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock)
	  {
	    if (paramBlock == Blocks.WEB) {
	      return 15.0F;
	    }
	    Material localMaterial = paramBlock.getMaterial();
	    if ((localMaterial == Material.PLANT) || (localMaterial == Material.REPLACEABLE_PLANT) || (localMaterial == Material.CORAL) || (localMaterial == Material.LEAVES) || (localMaterial == Material.PUMPKIN)) {
	      return 1.5F;
	    }
	    return 1.0F;
	  }
	  
	  public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2)
	  {
	    paramItemStack.damage(1, paramEntityLiving2);
	    return true;
	  }
	  
	  public boolean a(ItemStack paramItemStack, World paramWorld, Block paramBlock, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	  {
	    if (paramBlock.f(paramWorld, paramInt1, paramInt2, paramInt3) != 0.0D) {
	      paramItemStack.damage(2, paramEntityLiving);
	    }
	    return true;
	  }
	  
	  public EnumAnimation d(ItemStack paramItemStack)
	  {
	    return EnumAnimation.BLOCK;
	  }
	  
	  public int d_(ItemStack paramItemStack)
	  {
	    return 72000;
	  }
	  
	  public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	  {
	    paramEntityHuman.a(paramItemStack, d_(paramItemStack));
	    return paramItemStack;
	  }
	  
	  public boolean canDestroySpecialBlock(Block paramBlock)
	  {
	    return paramBlock == Blocks.WEB;
	  }
	  
	  public int c()
	  {
	    return this.b.e();
	  }
	  
	  public String j()
	  {
	    return this.b.toString();
	  }
	  
	  public boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2)
	  {
	    if (this.b.f() == paramItemStack2.getItem()) {
	      return true;
	    }
	    return super.a(paramItemStack1, paramItemStack2);
	  }
	  
	  public Multimap k()
	  {
	    Multimap localMultimap = super.k();
	    
	    localMultimap.put(GenericAttributes.e.getName(), new AttributeModifier(f, "Weapon modifier", this.damage, 0));
	    
	    return localMultimap;
	  }
	  
	  
	  @Override
	public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
		
		  if (entity instanceof EntityPlayer){
			  
			  EntityPlayer player = (EntityPlayer)entity;
			  if (player.getCurrentItem() != null && player.getCurrentItem().getItem() == itemstack.getItem()){
				  
				  player.addEffect(new MobEffect(4, 20, 1));
				  
			  }
			  
		  }
		  
		  
		super.a(itemstack, world, entity, i, flag);
	}
	  
	  
	  
	  
}