package fr.maxlego08.core.worldguardEvent.events;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.maxlego08.core.worldguardEvent.RegionEvent;
import fr.maxlego08.core.worldguardEvent.WorldGuardListener.MovementWay;

public class RegionEnteredEvent
extends RegionEvent
{
public RegionEnteredEvent(ProtectedRegion region, Player player, MovementWay movement, PlayerEvent parent)
{
  super(region, player, movement, parent);
}
}
