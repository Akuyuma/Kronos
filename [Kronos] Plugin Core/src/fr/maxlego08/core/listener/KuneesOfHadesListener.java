package fr.maxlego08.core.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KuneesOfHadesListener implements Listener {

	
	
	@EventHandler
	public void onClick(PlayerInteractEvent e){
		
		Player player = e.getPlayer();
		if (player.getItemInHand().getType().equals(Material.KUNEES_OF_HADES)){
			if (e.getAction().equals(Action.LEFT_CLICK_AIR) ||e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			
				for(PotionEffect type : player.getActivePotionEffects()){
					if (!type.getType().equals(PotionEffectType.ANTIBACKAP)) player.removePotionEffect(type.getType());
				}
				
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability()+1)); // apply durability
				if (player.getItemInHand().getDurability() > 3) player.getInventory().remove(player.getItemInHand());
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onClick(PlayerInteractEntityEvent e){
		
		Player player = e.getPlayer();
		
		if (player.getItemInHand().getType().equals(Material.KUNEES_OF_HADES)){
			
			if (e.getRightClicked() instanceof Player){
				
				Player clickedEntity = (Player)e.getRightClicked();
				
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability()+1)); // apply durability
				if (player.getItemInHand().getDurability() > 3) player.getInventory().remove(player.getItemInHand());
				
				for(PotionEffect type : clickedEntity.getActivePotionEffects()){
					if (!type.getType().equals(PotionEffectType.ANTIBACKAP)) {
						clickedEntity.removePotionEffect(type.getType());
						player.addPotionEffect(type);
					}
				}
				
				
			}
			
		}
		
	}
	
}
