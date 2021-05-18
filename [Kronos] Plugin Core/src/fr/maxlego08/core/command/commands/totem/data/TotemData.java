package fr.maxlego08.core.command.commands.totem.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class TotemData {

	private Location location;
	private String faction;
	private boolean isActive;
	private List<Location> locations;
	
	public TotemData() {
		this.faction = "Aucune";
		this.isActive = false;
		this.locations = new ArrayList<>();
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean b){
		isActive = b;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getFaction() {
		return faction;
	}
	
	public void setFaction(String faction) {
		this.faction = faction;
	}
	
	public void create(){
		Block b = new Location(getLocation().getWorld(), getLocation().getX(), getLocation().getY(), getLocation().getZ()).getBlock();
		locations.clear();
		isActive = !isActive;
		for (int a = 0; a != 5; a++){
			locations.add(b.getLocation());
			b.setType(Material.QUARTZ_BLOCK);
			b = b.getRelative(BlockFace.UP);	
		}

	}
	
	public void delete(){
		isActive = !isActive;
		Block b = new Location(getLocation().getWorld(), getLocation().getX(), getLocation().getY(), getLocation().getZ()).getBlock();
		for (int a = 0; a != 5; a++){
			b.setType(Material.AIR);
			b = b.getRelative(BlockFace.UP);			
		}
	}
	
	public List<Location> getLocations() {
		return locations;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String changeLocationToString(Location location){String ret = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();return ret;}	
	public Location changeStringToLocation(String s){String[] a = s.split(",");World w = Bukkit.getServer().getWorld(a[0]);float x = Float.parseFloat(a[1]); float y = Float.parseFloat(a[2]);float z = Float.parseFloat(a[3]); return new Location(w, x, y, z);}		
	
	public String z(){
		return changeLocationToString(location);
	}
	
	public void w(String z){
		location = changeStringToLocation(z);
	}
	
}
