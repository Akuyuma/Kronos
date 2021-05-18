package fr.maxlego08.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class NetherPortal implements Listener {

	@EventHandler
	public void a(PortalCreateEvent e){
		e.setCancelled(true);
	}
	
}
