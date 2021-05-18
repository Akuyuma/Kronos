package fr.maxlego08.kronos.craft;

import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.init.Items;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public enum CraftItems {

	ANGELITE_INTGOT_TO_ANGELITE(new ItemStack(Items.ANGELITE, 1), new Object[] {"###", "###", "###", Character.valueOf('#'), Items.ANGELITE_INGOT}),
	ANGELITE_TO_ANGELITE_INGOT(new ItemStack(Items.ANGELITE_INGOT, 9), new Object[] {"#", Character.valueOf('#'), Items.ANGELITE}),
	
	
	
	SOUL_OF_ANGELIC_SPAWNER(get(), new Object[] {"#", "#", "P", Character.valueOf('#'), Items.carrot, Character.valueOf('P'), Items.stick}),
	
	;
	
	public static ItemStack get(){
		ItemStack itemd = new ItemStack(Items.GALAAD_SWORD, 1);
		itemd.addEnchantment(Enchantment.looting, 3);
		return itemd;
	}
	
	
	private final ItemStack item;
	private final Object[] craft;
	
	private CraftItems(ItemStack item, Object[] craft) {
		this.item = item;
		this.craft = craft;
	}
	
	public Object[] getCraft() {
		return craft;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	
}
