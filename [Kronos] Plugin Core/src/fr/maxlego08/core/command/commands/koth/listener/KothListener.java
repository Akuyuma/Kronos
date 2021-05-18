package fr.maxlego08.core.command.commands.koth.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.koth.data.KothData;
import fr.maxlego08.core.command.commands.koth.manager.KothManager;

public class KothListener implements Listener {

	private Kronos main;
	private KothManager koth;
	
	public KothListener(Kronos main) {
		this.main = main;
		this.koth = main.getKothManager();
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if (koth.hasEnableKoth()){
			if (koth.getEnableKoth().getPlayer().equals("Personne")){
				if (koth.getEnableKoth().isIn(e.getPlayer())){
					task(e.getPlayer(), koth.getEnableKoth());
				}
			}else if (koth.getEnableKoth().getPlayer().equals(e.getPlayer().getName())){
				if (!koth.getEnableKoth().isIn(e.getPlayer())){
					koth.getEnableKoth().setPlayer("Personne");
				}
			}
		}
	}
	
	private void task(Player player, KothData data){
		data.setPlayer(player.getName());
		Bukkit.broadcastMessage(main.getPrefix() + " §eLe joueur §6" + player.getName() + " §evient de commencer le koth ! §8(§b"+data.getTimeCap()+"s§8)");
		new BukkitRunnable() {
			int timer = data.getTimeCap() * 2;
			
			@Override
			public void run() {
				if (!data.getPlayer().equals(player.getName())){
					cancel();
					Bukkit.broadcastMessage(main.getPrefix() + " §eLe joueur §6" + player.getName() + " §evient de perdre le koth !");
				}
				if (!data.isActive()){
					cancel();
				}
				if (timer == 0){
					cancel();
					Bukkit.broadcastMessage(main.getPrefix() + " §eLe joueur §6" + player.getName() +" §evient de gagner le koth !");
					ItemStack it = new ItemStack(Material.CHEST);
					ItemMeta itM = it.getItemMeta();
					itM.setDisplayName("§6» §eKoth Loot");
					List<String> l = new ArrayList<>();
					l.add("§6» §eKoth gagné§e: §6" + data.getName());
					itM.setLore(l);
					it.setItemMeta(itM);
					if (koth.hasInventoryFull(player)){
						player.getWorld().dropItem(player.getLocation(), it);
					}else{
						player.getInventory().addItem(it);
					}
					
					data.setActive(false);
				}
				timer--;
				
			}
		}.runTaskTimer(main, 0, 10l);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		if (e.getPlayer().getItemInHand().hasItemMeta() && e.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6» §eKoth Loot")){
			e.setCancelled(true);
			koth.giveLoot(e.getPlayer());
		}		
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		if (e.getInventory().getName().equals("§eKoth loot")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		if (e.getInventory().getName().startsWith("§6Koth Loot §6")){
			ItemStack[] items = e.getInventory().getContents();
			List<ItemStack> it = new ArrayList<>();
			for(int a = 0; a != items.length; a++){
				if (items[a] != null){
					it.add(items[a]);
				}
			}
			koth.getKoth(e.getInventory().getName().replace("§6Koth Loot §6", "")).setLoots(it);;
			((Player)e.getPlayer()).sendMessage(main.getPrefix() + " §eVous venez d'éditer les loots !");
		}
	}
}
