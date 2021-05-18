package fr.maxlego08.kronos.items;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemFood;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MobEffect;
import net.minecraft.server.MobEffectList;
import net.minecraft.server.World;

public class ItemBunch extends ItemFood{

	  public ItemBunch(int i, float f, boolean flag)
	  {
	    super(i, f, flag);
	    a(true);
	  }

	  
	  protected void c(ItemStack itemstack, World world, EntityHuman entityhuman)
	  {
	    if (!world.isStatic) {
	      entityhuman.addEffect(new MobEffect(MobEffectList.INCREASE_DAMAGE.id, 8*60*20, 1));
	      entityhuman.addEffect(new MobEffect(MobEffectList.FASTER_MOVEMENT.id, 8*60*20, 1));
	      entityhuman.addEffect(new MobEffect(MobEffectList.FIRE_RESISTANCE.id, 8*60*20, 0));
	    }
	  }
	
}
