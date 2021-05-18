package fr.maxlego08.core.listener;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.maxlego08.core.utils.timer.CooldownBuilder;
import fr.maxlego08.core.utils.timer.TimerBuilder;

public class PowerOfHades implements Listener {

	
	public PowerOfHades() {
		CooldownBuilder.getCreatedCooldown("xp");
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		Player player = e.getPlayer();
		
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_AIR)){
			
			if (e.getPlayer().getItemInHand().getType().equals(Material.POWER_OF_HADES)){
				
				if (!CooldownBuilder.isCooldown("xp", player)){
					
					try {
						CooldownBuilder.addCooldown("xp", player, 3600+1800);
						player.sendMessage("§6» §eActivation du pouvoir d'§6Hades §e!");
						player.getInventory().remove(player.getItemInHand());
					} catch (IOException e1) { }
					
				}else{
					
					player.sendMessage("§6» §eVous avez déjà votre pouvoir d'activé ! Il vous reste §6" + TimerBuilder.getFormatLongHours(CooldownBuilder.getCooldownPlayerLong("xp", player))+"§e.");
					
				}
				
			}
			
		}		
		
	}
	
	@EventHandler
	public void onBlock(BlockBreakEvent e){
		
		Player player = e.getPlayer();
		
		if (CooldownBuilder.isCooldown("xp", player)){
			
			e.setExpToDrop((int) (e.getExpToDrop() * 1.5));
			
		}
		
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
		
		if (e.getEntity().getKiller() != null){
			
			Player player = e.getEntity().getKiller();
			
			if (CooldownBuilder.isCooldown("xp", player)){
				
				e.setDroppedExp((int) (e.getDroppedExp() * 1.5));
				
			}
			
			
		}
		
		
	}
	
}
