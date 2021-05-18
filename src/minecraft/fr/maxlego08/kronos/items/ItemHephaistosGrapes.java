package fr.maxlego08.kronos.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHephaistosGrapes  extends ItemFood{

	public ItemHephaistosGrapes(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3) {
		super(p_i45339_1_, p_i45339_2_, p_i45339_3);
	}

	
	@Override
	protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
		
		if (!w.isClient){
			p.addPotionEffect(new PotionEffect(Potion.nofall.id, 3*60*20, 0));
		}
		
	}
}
