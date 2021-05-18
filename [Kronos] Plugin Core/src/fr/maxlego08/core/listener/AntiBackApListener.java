package fr.maxlego08.core.listener;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.worldguardEvent.events.RegionLeaveEvent;

public class AntiBackApListener implements Listener {

	private Kronos kronos;
	
	public AntiBackApListener(Kronos kronos) {
		this.kronos = kronos;
	}

	@EventHandler
	public void onRejoinQuit(RegionLeaveEvent e){
		
		Player player = e.getPlayer();
		
		if (e.getRegion().getId().equalsIgnoreCase("warzone")){		
			for(PotionEffect effect : player.getActivePotionEffects()){
				if (effect.getType().equals(PotionEffectType.ANTIBACKAP)){
					//e.setCancelled(true);
					player.teleport(getTeleportLocation(player.getLocation()));
					player.sendMessage("§6» §eVous ne pouvez pas quitter la WarZone quand vous avez l'effet d'§6AntiBack§e !");
				}
			}
		}
		
	}
	
	/*
	 * On va Chercher la location pour téléporter le joueur
	 * */
	
	private Location getTeleportLocation(Location location){
		
		World world = location.getWorld();
		double y = location.getY();
		double x = location.getX();
		double z = location.getZ();
		float pitch = location.getPitch();
		float yaw = location.getYaw();
		
		Location l1 = new Location(world, x + 2, y, z, yaw, pitch);
		Location l2 = new Location(world, x - 2, y, z, yaw, pitch);
		Location l3 = new Location(world, x, y, z + 2, yaw, pitch);
		Location l4 = new Location(world, x, y, z - 2, yaw, pitch);
		
		if (isReallyWarzone(l1)){
			return l1;
		}
		else if (isReallyWarzone(l2)){
			return l2;
		}
		else if (isReallyWarzone(l3)){
			return l3;
		}
		return l4;
		
		
	}
	
	private boolean isReallyWarzone(Location location){
		
		World world = location.getWorld();
		int y = location.getBlockY();
		int x = location.getBlockX();
		int z = location.getBlockZ();
		
		Location l1 = new Location(world, x + 1, y, z);
		Location l2 = new Location(world, x - 1, y, z);
		Location l3 = new Location(world, x, y, z + 1);
		Location l4 = new Location(world, x, y, z - 1);
	
		return isWarzone(l1) && isWarzone(l2) && isWarzone(l3) && isWarzone(l4);
	}
	
	private boolean isWarzone(Location location){
		for(ProtectedRegion r : WGBukkit.getRegionManager(location.getWorld()).getApplicableRegions(location)) {
			if (r.getId().equalsIgnoreCase("Warzone")){
				return true;
			}
		}
		return false;
	}
	
}
