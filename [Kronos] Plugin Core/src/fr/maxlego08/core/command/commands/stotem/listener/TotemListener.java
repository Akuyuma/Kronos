package fr.maxlego08.core.command.commands.stotem.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.totem.TotemManager;

public class TotemListener implements Listener {

	private fr.maxlego08.core.command.commands.stotem.TotemManager totem;
	
	public TotemListener(fr.maxlego08.core.command.commands.stotem.TotemManager t) {
		this.totem = t;
	}

	@EventHandler
	public void e(BlockBreakEvent e){
		
		if (totem.getTotem().isActive()){
			if (totem.getTotem().getLocations().contains(e.getBlock().getLocation()) && e.getBlock().getType().equals(Material.OBSIDIAN)){
				if (e.getPlayer().getItemInHand().getType().equals(Material.MALACHITE_PICKAXE)){
					
					FPlayer f = FPlayers.i.get(e.getPlayer());
					

					totem.getTotem().getLocations().remove(e.getBlock().getLocation());										
					if (totem.getTotem().getLocations().size() == 0){
						totem.getTotem().setActive(true);
						totem.getTotem().delete();
						totem.giveLoot(e.getPlayer());
						Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §eLe joueur §6" + e.getPlayer().getName() + " §ede la faction §6"+f.getFaction().getTag()+" §evient de gagner le totem §7!");									
						return;
					}
		
					
				}else{
					e.setCancelled(true);
					e.getPlayer().sendMessage(Kronos.k().getArrow() + " §eVous devez avoir une épée en §6celenite§e pour casser le totem.");
				}
			}
		}
		
	}
	
}
