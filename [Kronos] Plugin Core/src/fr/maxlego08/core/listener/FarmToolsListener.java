package fr.maxlego08.core.listener;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class FarmToolsListener implements Listener {

	/*
	 * Pourquoi faire cela via un plugin ?
	 * 
	 * -> Plus rapide et plus simple
	 * 
	 * */
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		
		if (e.getBlock().getType().equals(Material.POTATO) || e.getBlock().getType().equals(Material.CARROT) || e.getBlock().getType().equals(Material.CROPS)
				&& e.getBlock().getData() == 7
				){
			
			if (e.getPlayer().getItemInHand().getType().equals(Material.FARMTOOLS)){
				
			}else{
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				Random random = new Random();
				int r = random.nextInt(3);
				if (r == 0){
					r = 1;
				}
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(get(e.getBlock().getType()),r));
			}
			
		}
		
	}

	@SuppressWarnings("incomplete-switch")
	private Material get(Material m){
		switch (m) {
		case POTATO:
			return Material.POTATO_ITEM;
		case CARROT:
			return Material.CARROT_ITEM;
		case CROPS:
			return Material.WHEAT;
		}
		return m;
	}
	
}
