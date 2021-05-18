package fr.maxlego08.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class BanSwordListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		
		if (e.getEntity() instanceof Player){
			
			Player player = (Player)e.getEntity();
			
			if (player.getKiller() != null && player.getKiller() instanceof Player){
				
				Player killer = player.getKiller();
				
				if (killer.getItemInHand().getType().equals(Material.BAN_SWORD)){
					
					killer.setItemInHand(new ItemStack(Material.AIR));
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + player.getName() + " 2h Bannis par les dieux !");
					
				}
				
				
			}
			
		}
		
	}
	
}
