package fr.maxlego08.core.command.commands.airdrop.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.airdrop.manager.AirDropManager;
import fr.maxlego08.core.command.commands.koth.manager.KothManager;

public class AirDropListener implements Listener {

	private Kronos main;
	private AirDropManager koth;
	
	public AirDropListener(Kronos main) {
		this.main = main;
		this.koth = main.getA();
	}
	
	@EventHandler
	public void a(PlayerInteractEvent e){
		
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			if (e.getClickedBlock().getType().equals(Material.CHEST)){
				
					if (koth.isChest(e.getClickedBlock().getLocation())) koth.z(e.getClickedBlock().getLocation(), e.getPlayer());
				
			}
		}
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		if (e.getInventory().getName().equals("§eAirdrop loot")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		if (e.getInventory().getName().startsWith("§6Loots")){
			ItemStack[] items = e.getInventory().getContents();
			List<ItemStack> it = new ArrayList<>();
			for(int a = 0; a != items.length; a++){
				if (items[a] != null){
					it.add(items[a]);
				}
			}
			koth.setLoots(it);;
			((Player)e.getPlayer()).sendMessage(main.getPrefix() + " §eVous venez d'éditer les loots !");
		}
	}
	
}
