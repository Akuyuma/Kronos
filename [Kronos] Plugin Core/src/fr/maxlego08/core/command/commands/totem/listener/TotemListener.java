package fr.maxlego08.core.command.commands.totem.listener;

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

	private TotemManager totem;
	
	public TotemListener(TotemManager t) {
		this.totem = t;
	}

	@EventHandler
	public void e(BlockBreakEvent e){
		
		if (totem.getTotem().isActive()){
			if (totem.getTotem().getLocations().contains(e.getBlock().getLocation()) && e.getBlock().getType().equals(Material.QUARTZ_BLOCK)){
				if (e.getPlayer().getItemInHand().getType().equals(Material.CELENITE_SWORD)){
					
					FPlayer f = FPlayers.i.get(e.getPlayer());
					
					if (f.getFaction().getTag().equals(totem.getTotem().getFaction())){
						
						totem.getTotem().getLocations().remove(e.getBlock().getLocation());										
						if (totem.getTotem().getLocations().size() == 0){
							totem.getTotem().setActive(true);
							totem.getTotem().delete();
							totem.giveLoot(e.getPlayer());
							Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §eLe joueur §6" + e.getPlayer().getName() + " §ede la faction §6"+f.getFaction().getTag()+" §evient de gagner le totem §7!");									
							return;
						}
						e.getBlock().setType(Material.AIR);
						Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §eLe joueur §6" + e.getPlayer().getName() + " §ede la faction §6"+f.getFaction().getTag()+" §evient de casser un block du totem §8(§7Plus que "+totem.getTotem().getLocations().size()+"§8)§7!");					
					
						
					}else if (!totem.getTotem().getFaction().equals("Aucune")){
						
						totem.getTotem().create();
						totem.getTotem().setFaction("Aucune");
						Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §eLe joueur §6" + e.getPlayer().getName() + " §evient de bloquer la faction §6" + totem.getTotem().getFaction() +" §e!");
						totem.getTotem().getLocations().remove(e.getBlock().getLocation());e.getBlock().setType(Material.AIR);
						
					}else{
						
						Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §eLe joueur §6" + e.getPlayer().getName() + " §ede la faction §6"+f.getFaction().getTag()+" §ecommencer à casser le totem §7!");					
						totem.getTotem().setFaction(f.getFaction().getTag()); //Changer ici aussi
						totem.getTotem().getLocations().remove(e.getBlock().getLocation());
						e.getBlock().setType(Material.AIR);
						
					}
					
					
				}else{
					e.setCancelled(true);
					e.getPlayer().sendMessage(Kronos.k().getArrow() + " §eVous devez avoir une épée en §6celenite§e pour casser le totem.");
				}
			}
		}
		
	}
	
}
