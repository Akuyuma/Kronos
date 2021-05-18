package fr.maxlego08.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.maxlego08.core.Kronos;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class NoInsulteListener implements Listener {

	private Kronos main = Kronos.k();
	
	@EventHandler
	public void AsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		
		if (e.getPlayer().isOnline()) {
			String[] a = e.getMessage().split(" ");
			for(int b = 0; b != a.length ;b++) {	
				String[] c = a[b].split("");int lenth = 0;
				for(int d = 0; d != c.length ;d++) {
					char ch = c[d].charAt(0);
					if (Character.isUpperCase(ch)){lenth++;}
				}
				if ((a[b].length() / 2) < lenth && lenth >= 3 && !isPseudo(a[b])) {
					TextComponent message = new TextComponent(main.getArrow() + " §eLe joueur §6" + e.getPlayer().getName() + " §eutilise des majuscules sur le mot §6");
					TextComponent text = new TextComponent("§6"+a[b]);
			        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Message§8: §f" + e.getMessage().replace(a[b], "§a"+a[b]+"§f")).create()));
			        message.addExtra(text);
			        message.addExtra("§7.");						
					for(Player t : Bukkit.getOnlinePlayers()){
						if (t.hasPermission("info.vultaria")){
							t.spigot().sendMessage(message);
						}
					}						
					e.getPlayer().sendMessage(main.getArrow() + " §eVous ne pouvez pas mettre autant de majuscule dans le mot §6"+a[b]+" §e!");
					e.setCancelled(true);return;
				}
			}
		}
		
		String[] a = e.getMessage().split(" ");
		for(int b = 0; b != a.length ;b++) {
			if (main.getInsultes().contains(a[b])) {
				e.getPlayer().sendMessage(main.getArrow() + " §eVous ne pouvez pas utiliser l'insulte §6"+a[b]+" §eici !");
				e.setCancelled(true);
				TextComponent message = new TextComponent(main.getArrow() + " §eLe joueur §6" + e.getPlayer().getName() + " §eutilise l'insulte §6");
				TextComponent text = new TextComponent("§6"+a[b]);
		        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eMessage§8: §f" + e.getMessage().replace(a[b], "§a"+a[b]+"§f")).create()));
		        message.addExtra(text);
		        message.addExtra("§7.");	        
				for(Player t : Bukkit.getOnlinePlayers()){
					if (t.hasPermission("info.vultaria")){
						t.spigot().sendMessage(message);
					}
				}					
			}			
		}	
		
		
	}
	
	@SuppressWarnings("deprecation")
	private boolean isPseudo(String name){
		for(Player p : Bukkit.getOnlinePlayers()){
			if (p.getName().equals(name)){return true;}
		}
		return false;
	}
	
}
