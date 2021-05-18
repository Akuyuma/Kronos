package fr.maxlego08.kronos.items;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemFood;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MobEffect;
import net.minecraft.server.MobEffectList;
import net.minecraft.server.World;

public class ItemHephaistosGrapes  extends ItemFood
{
	  public ItemHephaistosGrapes(int i, float f, boolean flag)
	  {
	    super(i, f, flag);
	    a(true);
	  }

	  
	  protected void c(ItemStack itemstack, World world, EntityHuman entityhuman)
	  {
	    if (!world.isStatic) {
	      entityhuman.addEffect(new MobEffect(MobEffectList.NOFALL.id, 3*60*20, 0));
	    }
	  }
}