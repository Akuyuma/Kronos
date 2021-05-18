package fr.maxlego08.core.listener;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.maxlego08.core.utils.timer.CooldownBuilder;
import fr.maxlego08.core.utils.timer.TimerBuilder;
import net.brcdev.shopgui.api.event.ShopPreTransactionEvent;
import net.brcdev.shopgui.shop.ShopManager.ShopAction;

public class PowerOfHermes implements Listener {

	public PowerOfHermes() {
		CooldownBuilder.getCreatedCooldown("shop");
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		Player player = e.getPlayer();
		
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_AIR)){
			
			if (e.getPlayer().getItemInHand().getType().equals(Material.POWER_OF_HERMES)){
				
				if (!CooldownBuilder.isCooldown("shop", player)){
					
					try {
						CooldownBuilder.addCooldown("shop", player, 3600+1800);
						player.sendMessage("§6» §eActivation du pouvoir d'§6Hermes §e!");
						player.getInventory().remove(player.getItemInHand());
					} catch (IOException e1) { }
					
				}else{
					
					player.sendMessage("§6» §eVous avez déjà votre pouvoir d'activé ! Il vous reste §6" + TimerBuilder.getFormatLongHours(CooldownBuilder.getCooldownPlayerLong("shop", player))+"§e.");
					
				}
				
			}
			
		}		
		
	}
	
	@EventHandler
	public void onShop(ShopPreTransactionEvent event){
		
		Player player = event.getPlayer();
		
		if (CooldownBuilder.isCooldown("shop", player)){
			
			if(event.getShopAction().equals(ShopAction.SELL) || event.getShopAction().equals(ShopAction.SELL_ALL)){
				
				event.setPrice(event.getPrice() * 1.5);
				
			}
			
		}
		
	}
	
}
