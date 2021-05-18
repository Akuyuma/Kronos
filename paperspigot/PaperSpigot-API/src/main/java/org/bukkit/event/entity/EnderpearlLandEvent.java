package org.bukkit.event.entity;

import org.bukkit.entity.EnderPearl;
import org.bukkit.event.HandlerList;

public class EnderpearlLandEvent extends EntityEvent {

    private static final HandlerList handlers = new HandlerList();
    private final Reason reason;

    public EnderpearlLandEvent(final EnderPearl enderPearl, final Reason reason) {
        super(enderPearl);
        this.reason = reason;
    }

    @Override
    public EnderPearl getEntity() {
        return (EnderPearl) entity;
    }
    
    public Reason getReason() {
        return reason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum Reason {
        /**
         * Called when the pearl hits a block
         */
        BLOCK,
        /**
         * Called when the pearl hits an entity
         */
        ENTITY
    }
}
