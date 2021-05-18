package fr.maxlego08.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtils {

	protected String changeLocationToString(Location location){
		if (location == null){
			System.out.println("La location est null wtf oO");
		}
		String ret = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
		return ret;
	}	
	
	protected Location changeStringLocationToLocation(String s){
		String[] a = s.split(",");
		World w = Bukkit.getServer().getWorld(a[0]);
		float x = Float.parseFloat(a[1]);
		float y = Float.parseFloat(a[2]);
		float z = Float.parseFloat(a[3]); 
		return new Location(w, x, y, z);
	}

	
}
