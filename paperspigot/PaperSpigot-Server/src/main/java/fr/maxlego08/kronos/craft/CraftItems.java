package fr.maxlego08.kronos.craft;

import net.minecraft.server.Blocks;
import net.minecraft.server.Enchantment;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Items;

public enum CraftItems {

	ANGELITE_INTGOT_TO_ANGELITE(new ItemStack(Items.ANGELITE, 1), new Object[] {"###", "###", "###", '#', Items.ANGELITE_INGOT}),
	ANGELITE_TO_ANGELITE_INGOT(new ItemStack(Items.ANGELITE_INGOT, 9), new Object[] {"#", '#', Items.ANGELITE}),
	
	SOUL_OF_ANGELIC_SPAWNER(get(), new Object[] {"#", "#", "P", Character.valueOf('#'), Items.CARROT, Character.valueOf('P'), Items.STICK}),
	
	
	;
	
	public static ItemStack get(){
		
		ItemStack t = new ItemStack(Items.GALAAD_SWORD, 1);
		t.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
		return t;
		
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