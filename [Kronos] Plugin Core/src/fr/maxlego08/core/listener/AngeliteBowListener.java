package fr.maxlego08.core.listener;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AngeliteBowListener implements Listener {

	@EventHandler
	public void onShoot(EntityDamageByEntityEvent e){
		
		if (e.getCause().equals(DamageCause.PROJECTILE)){
			
			if (e.getDamager() instanceof Arrow){
				
				Arrow arrow = (Arrow)e.getDamager();
				
				if (arrow.getShooter() instanceof Player){
					
					Player player = (Player)arrow.getShooter();
					
					if (player.getItemInHand().getType().equals(Material.ANGELITE_BOW)){
						
						if (e.getEntity() instanceof Player){
							
							Player victime = (Player)e.getEntity();
							victime.addPotionEffect(new PotionEffect(PotionEffectType.ANTIBACKAP, 20*60, 0));
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
