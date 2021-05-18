package fr.maxlego08.core.command.commands.koth.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.core.utils.ZPlugin;

public class KothData {

	private final Location pos1;
	private final Location pos2;
	private final String name;
	private int timeCap;
	private List<String> locations = new ArrayList<>();
	private List<ItemStack> loots;
	private boolean isActive;	
	private String player;
	
	public KothData(Location pos1, Location pos2, String name, int timeCap, List<ItemStack> loots) {
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.name = name;
		this.timeCap = timeCap;
		this.loots = loots;
		this.isActive = false;
		this.player = "Personne";
		if (pos1 == null){
			ZPlugin.z().suicide("Pos1 of koth is null !");
			return;
		}
		if (pos2 == null){
			ZPlugin.z().suicide("Pos2 of koth is null !");
			return;
		}
		this.loadLocations();
	}

	private void loadLocations(){
		int minX = getPos1().getBlockX();
		int minY = getPos1().getBlockY();
		int minZ = getPos1().getBlockZ();	
	
		int maxX = getPos2().getBlockX();
		int maxY = getPos2().getBlockY();
		int maxZ = getPos2().getBlockZ();	
		
		if (getPos2().getBlockX() < getPos1().getBlockX()){
		
			minX = getPos2().getBlockX();		
			maxX = getPos1().getBlockX();
		}
		if (getPos2().getBlockY() < getPos1().getBlockY()){
			
			minY = getPos2().getBlockY();
			maxY = getPos1().getBlockY();
		}
		if (getPos2().getBlockZ() < getPos1().getBlockZ()){
			minZ = getPos2().getBlockZ();		
			maxZ = getPos1().getBlockZ();	
		}
		World w = getPos1().getWorld();
		for(int y = minY; y != maxY+1; y++){
			for(int x = minX; x != maxX+1; x++){
				for(int z = minZ; z != maxZ +1; z++){
					locations.add(changeLocationToString(new Location(w, x, y, z)));
				}
			}			
		}	
	}
	
	public void setActive(boolean b){
		isActive = b;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public Location getPos1() {
		return pos1;
	}
	
	public Location getPos2() {
		return pos2;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTimeCap() {
		return timeCap;
	}
	
	public void setTimeCap(int timeCap) {
		this.timeCap = timeCap;
	}
	
	public List<ItemStack> getLoots() {
		return loots;
	}
	
	public void setLoots(List<ItemStack> loots) {
		this.loots = loots;
	}
	
	public List<String> getLocations() {
		return locations;
	}
	
	public boolean isIn(Player player){
		return locations.contains(changeLocationToString(player.getLocation()));
	}
	
	public boolean isIn(Location location){
		return locations.contains(changeLocationToString(location));
	}
	
	private String changeLocationToString(Location location){
		if (location == null){
			System.err.println("WTF LA LOC EST NULl");
		}
		String ret = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
		return ret;
	}		
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
}
