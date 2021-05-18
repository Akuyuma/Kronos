package fr.maxlego08.kronos.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemStickOfGods extends Item{

	public ItemStickOfGods() {
		setMaxDamage(4);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		
		if (!world.isClient){
			
			item.damageItem(1, player);
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 5*20, 100));
			player.addPotionEffect(new PotionEffect(Potion.nofall.id, 20*20, 0));
			
		}
		
		return super.onItemRightClick(item, world, player);
	}
	
}
