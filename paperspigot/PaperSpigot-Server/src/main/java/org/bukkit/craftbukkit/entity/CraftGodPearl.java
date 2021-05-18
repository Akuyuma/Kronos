package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;

import fr.maxlego08.kronos.entities.EntityGodPearl;
import net.minecraft.server.EntityEnderPearl;

public class CraftGodPearl extends CraftProjectile implements EnderPearl{

    public CraftGodPearl(CraftServer server, EntityGodPearl entity) {
        super(server, entity);
    }

    @Override
    public EntityGodPearl getHandle() {
        return (EntityGodPearl) entity;
    }

    @Override
    public String toString() {
        return "CraftGodPearl";
    }

    public EntityType getType() {
        return EntityType.ENDER_PEARL;
    }

}
