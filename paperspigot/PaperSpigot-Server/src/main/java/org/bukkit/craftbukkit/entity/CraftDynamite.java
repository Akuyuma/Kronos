package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Dynamite;
import org.bukkit.entity.EntityType;

import fr.maxlego08.kronos.entities.EntityDynamite;

public class CraftDynamite
  extends CraftEntity
  implements Dynamite
{
  public CraftDynamite(CraftServer server, EntityDynamite entity)
  {
    super(server, entity);
  }
  
  public EntityDynamite getHandle()
  {
    return (EntityDynamite)this.entity;
  }
  
  public String toString()
  {
    return "CraftDynamite";
  }
  
  public EntityType getType()
  {
    return EntityType.DYNAMITE;
  }
}