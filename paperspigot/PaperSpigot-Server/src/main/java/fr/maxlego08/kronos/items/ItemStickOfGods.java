package fr.maxlego08.kronos.items;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MobEffect;
import net.minecraft.server.MobEffectList;
import net.minecraft.server.World;

public class ItemStickOfGods extends Item{

	
	public ItemStickOfGods() {
		e(1);
		setMaxDurability(4);
	}
	
	 public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		
		 itemstack.damage(1, entityhuman);
		 
		entityhuman.addEffect(new MobEffect(MobEffectList.JUMP.id, 5*20, 100));
		entityhuman.addEffect(new MobEffect(MobEffectList.NOFALL.id, 20*20, 100));
		
		return true;
	}
	
	
}
