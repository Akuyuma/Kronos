package fr.maxlego08.core.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BaguetteXrayListener implements Listener {

	@EventHandler
	public void onItemUse(PlayerInteractEvent e){
		
		Player player = e.getPlayer();
		int durability = 20;
		
		if (player.getItemInHand().getType().equals(Material.BAGUETTE_XRAY)){
			
			e.setCancelled(true);
			
			player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability()+1)); // apply durability
			if (player.getItemInHand().getDurability() > durability) player.getInventory().remove(player.getItemInHand());
			
			Location location = player.getLocation();
			World world = player.getWorld();
			
			for(int a = -1; a != 2; a++){
				for(int b = -1; b != 2; b++){
										
					player.sendBlockChange(new Location(world, location.getX()+a, location.getY()-1, location.getZ()+b), Material.XRAY, (byte)1);
					
				}
			}
			
		}
		
	}
	
}
