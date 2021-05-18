package fr.maxlego08.core.spawners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class Spawner {

	private Location location;
	private final String owner;
	private EntityType type;
	private boolean powerUp;
	
	public Spawner(Location location, String owner, EntityType type, boolean powerUp) {
		this.location = location;
		this.owner = owner;
		this.type = type;
		this.powerUp = powerUp;
	}

	private String changeLocationToString(Location location){String ret = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();return ret;}	
	
	public EntityType getType() {
		return type;
	}
	
	public boolean isPlaced(){
		return location != null;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getOwner() {
		return owner;
	}
	
	public boolean isPowerUp() {
		return powerUp;
	}
	
	public void setPowerUp() {
		this.powerUp = true;
	}
	
	public String getLocationInString(){
		return changeLocationToString(getLocation());
	}
	
	public void removeSpawner(){
		getLocation().getBlock().setType(Material.AIR);
		location = null;
	}
	
	public void setType(EntityType type) {
		this.type = type;
	}
	
}
