package fr.maxlego08.core.command.commands.airdrop.data;

import org.bukkit.Location;

public class AirDrop {

	private final Location location;
	private final String name;
	
	public AirDrop(Location location, String name) {
		this.location = location;
		this.name = name;
	}
	
	public Location getLocation() {
		return location;
	}
	public String getName() {
		return name;
	}
	
	
	
}
