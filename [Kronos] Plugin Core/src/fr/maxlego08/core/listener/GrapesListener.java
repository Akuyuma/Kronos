package fr.maxlego08.core.listener;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GrapesListener implements Listener {

	/*
	 * Pourquoi faire les effets des raisans via plugin ?
	 * Plus rapide, plus simple :x
	 * Mais c'est faisable de faire ça dans spigot directement
	 * 
	 * */
	
	private List<Integer> bad = Arrays.asList(2,4,7,9,15,17,18,19,20);
	
	@EventHandler
	public void onEaten(PlayerItemConsumeEvent e){
		
		if (e.getItem().getType().equals(Material.POSITIVE_GRAPES)){
			
			for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
				if (bad.contains(effect.getType().getId())){
					e.getPlayer().removePotionEffect(effect.getType());
				}
			}
			
		}else if (e.getItem().getType().equals(Material.GOLDEN_GRAPES)){
			
			for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
				if (effect.getType().equals(PotionEffectType.ANTIBACKAP)){
					e.getPlayer().removePotionEffect(effect.getType());
				}
			}
			
		}
		
	}
	
	
}
