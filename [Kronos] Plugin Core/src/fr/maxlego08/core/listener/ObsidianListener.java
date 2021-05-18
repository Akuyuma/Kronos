package fr.maxlego08.core.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.maxlego08.core.Kronos;

public class ObsidianListener implements Listener {

	private int durability = 5;
	
	private Map<Location, Integer> obsdians = new HashMap<>();
	
	private final Kronos kronos;

	public Map<Location, Integer> getObsdians() {
		return obsdians;
	}
	
	public void setObsdians(Map<Location, Integer> obsdians) {
		this.obsdians = obsdians;
	}
	
	public ObsidianListener(Kronos kronos) {
		this.kronos = kronos;
	};
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player player = e.getPlayer();
		
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_AIR)) return;
		
		if (!e.getClickedBlock().getType().equals(Material.OBSIDIAN) && !player.getItemInHand().getType().equals(Material.STICK_OF_TARTARE)) return; // check item in hand) return;
		
		if (!player.getItemInHand().getType().equals(Material.STICK_OF_TARTARE)) return;
		
		
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			
			player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability()+1)); // apply durability
			if (player.getItemInHand().getDurability() > durability) player.getInventory().remove(player.getItemInHand());
			
			if (obsdians.containsKey(e.getClickedBlock().getLocation())){
				int how = obsdians.get(e.getClickedBlock().getLocation())-1;
				if (how == 0){
					e.getClickedBlock().setType(Material.AIR);
					obsdians.remove(e.getClickedBlock().getLocation());
					return;
				}					
				obsdians.put(e.getClickedBlock().getLocation(), how);				
			}else{
				obsdians.put(e.getClickedBlock().getLocation(), 4);
			}
			
		}else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			int life = 5;
			if (obsdians.containsKey(e.getClickedBlock().getLocation())) life = obsdians.get(e.getClickedBlock().getLocation());
			player.sendMessage(kronos.getArrow() + " §7Vie de l'obsidienne§8: §a" + life + "§7/§25"); 
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if (e.getBlock().getType().equals(Material.OBSIDIAN) && obsdians.containsKey(e.getBlock().getLocation())) obsdians.remove(e.getBlock().getLocation());
	}
	
	
}
