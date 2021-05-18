package fr.maxlego08.kronos.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.player.PlayerTeleportEvent;

import net.minecraft.server.DamageSource;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityProjectile;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.World;

public class EntityGodPearl extends EntityProjectile{
	
	  public EntityGodPearl(World world)
	  {
	    super(world);
	    this.loadChunks = world.paperSpigotConfig.loadUnloadedEnderPearls;
	  }
	  
	  public EntityGodPearl(World world, EntityLiving entityliving)
	  {
	    super(world, entityliving);
	    this.loadChunks = world.paperSpigotConfig.loadUnloadedEnderPearls;
	  }

	  protected void a(MovingObjectPosition movingobjectposition)
	  {
	    if (movingobjectposition.entity != null) {
	      movingobjectposition.entity.damageEntity(DamageSource.projectile(this, getShooter()), 0.0F);
	    }
	    if ((this.inUnloadedChunk) && (this.world.paperSpigotConfig.removeUnloadedEnderPearls)) {
	      die();
	    }
	    for (int i = 0; i < 32; i++) {
	      this.world.addParticle("portal", this.locX, this.locY + this.random.nextDouble() * 2.0D, this.locZ, this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
	    }
	    if (!this.world.isStatic)
	    {
	      if ((getShooter() != null) && ((getShooter() instanceof EntityPlayer)))
	      {
	        EntityPlayer entityplayer = (EntityPlayer)getShooter();
	        if ((entityplayer.playerConnection.b().isConnected()) && (entityplayer.world == this.world))
	        {
	          CraftPlayer player = entityplayer.getBukkitEntity();
	          Location location = getBukkitEntity().getLocation();
	          location.setPitch(player.getLocation().getPitch());
	          location.setYaw(player.getLocation().getYaw());
	          
	          PlayerTeleportEvent teleEvent = new PlayerTeleportEvent(player, player.getLocation(), location, PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
	          Bukkit.getPluginManager().callEvent(teleEvent);
	          if ((!teleEvent.isCancelled()) && (!entityplayer.playerConnection.isDisconnected()))
	          {
	            if (getShooter().am()) {
	              getShooter().mount((Entity)null);
	            }
	            entityplayer.playerConnection.teleport(teleEvent.getTo());
	            getShooter().fallDistance = 0.0F;
	            CraftEventFactory.entityDamage = this;
	            getShooter().damageEntity(DamageSource.FALL, 5.0F);
	            CraftEventFactory.entityDamage = null;
	          }
	        }
	      }
	      die();
	    }
	  }

	
	
}
