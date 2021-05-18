package fr.maxlego08.core.worldguardEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerWalkEvent;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.worldguardEvent.events.RegionEnterEvent;
import fr.maxlego08.core.worldguardEvent.events.RegionEnteredEvent;
import fr.maxlego08.core.worldguardEvent.events.RegionLeaveEvent;

public class WorldGuardListener implements Listener {

	public enum MovementWay
	{
	  MOVE,  TELEPORT,  SPAWN,  DISCONNECT;
	  
	  private MovementWay() {}
	}
	
	private Kronos main;
	
	private WorldGuardPlugin wgPlugin;
	private Map<Player, Set<ProtectedRegion>> playerRegions;
	
	public WorldGuardListener(Kronos main) {
		this.main = main;
		wgPlugin = main.getWorldguard();
		this.playerRegions = new HashMap<>();
	}
	
	  @EventHandler
	  public void onPlayerKick(PlayerKickEvent e)
	  {
	    Set<ProtectedRegion> regions = (Set<ProtectedRegion>)this.playerRegions.remove(e.getPlayer());
	    if (regions != null) {
	      for (ProtectedRegion region : regions)
	      {
	        RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
	        
	        this.main.getServer().getPluginManager().callEvent(leaveEvent);
	      }
	    }
	  }
	  
	  @EventHandler
	  public void onPlayerQuit(PlayerQuitEvent e)
	  {
		Set<ProtectedRegion> regions = (Set<ProtectedRegion>)this.playerRegions.remove(e.getPlayer());
	    if (regions != null) {
	      for (ProtectedRegion region : regions)
	      {
	        RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
	        
	        this.main.getServer().getPluginManager().callEvent(leaveEvent);
	      }
	    }
	  }
	  
	  @EventHandler
	  public void onPlayerMove(PlayerWalkEvent e)
	  {
	    e.setCancelled(updateRegions(e.getPlayer(), MovementWay.MOVE, e.getTo(), e));
	  }
	  
	  
	  @EventHandler
	  public void onPlayerJoin(PlayerJoinEvent e)
	  {
	    updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getPlayer().getLocation(), e);
	  }
	  
	  @EventHandler
	  public void onPlayerRespawn(PlayerRespawnEvent e)
	  {
	    updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getRespawnLocation(), e);
	  }

	  @SuppressWarnings({ "unchecked", "rawtypes" })
	private synchronized boolean updateRegions(final Player player, final MovementWay movement, Location to, final PlayerEvent event)
	  {
	    Set<ProtectedRegion> regions;
	    if (this.playerRegions.get(player) == null) {
	      regions = new HashSet<>();
	    } else {
	      regions = new HashSet<>((Collection)this.playerRegions.get(player));
	    }
	    Set<ProtectedRegion> oldRegions = new HashSet<>(regions);
	    
	    RegionManager rm = this.wgPlugin.getRegionManager(to.getWorld());
	    if (rm == null) {
	      return false;
	    }
	    HashSet<ProtectedRegion> appRegions = new HashSet<>(rm.getApplicableRegions(to).getRegions());
	    ProtectedRegion globalRegion = rm.getRegion("__global__");
	    if (globalRegion != null) {
	      appRegions.add(globalRegion);
	    }
	    for (final ProtectedRegion region : appRegions) {
	      if (!regions.contains(region))
	      {
	        RegionEnterEvent e = new RegionEnterEvent(region, player, movement, event);
	        
	        this.main.getServer().getPluginManager().callEvent(e);
	        if (e.isCancelled())
	        {
	          regions.clear();
	          regions.addAll(oldRegions);
	          
	          return true;
	        }
	        Bukkit.getScheduler().runTaskLater(this.main, new Runnable()
	        {
	          public void run()
	          {
	            RegionEnteredEvent e = new RegionEnteredEvent(region, player, movement, event);
	            
	            WorldGuardListener.this.main.getServer().getPluginManager().callEvent(e);
	          }
	        }, 1L);
	        
	        regions.add(region);
	      }
	    }
	    Object itr = regions.iterator();
	    while (((Iterator)itr).hasNext())
	    {
	      final ProtectedRegion region = (ProtectedRegion)((Iterator)itr).next();
	      if (!appRegions.contains(region)) {
	        if (rm.getRegion(region.getId()) != region)
	        {
	          ((Iterator)itr).remove();
	        }
	        else
	        {
	          RegionLeaveEvent e = new RegionLeaveEvent(region, player, movement, event);
	          
	          this.main.getServer().getPluginManager().callEvent(e);
	          if (e.isCancelled())
	          {
	            regions.clear();
	            regions.addAll(oldRegions);
	            return true;
	          }
	          
	          ((Iterator)itr).remove();
	        }
	      }
	    }
	    this.playerRegions.put(player, regions);
	    return false;
	  }
	  
}
