package org.bukkit.craftbukkit.block;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

import fr.maxlego08.kronos.craftbukkit.block.AngeliteChest;
import fr.maxlego08.kronos.tileentity.TileEntityAngeliteChest;

public class CraftAngeliteChest extends CraftBlockState implements AngeliteChest{
    private final CraftWorld world;
    private final TileEntityAngeliteChest farm;

    public CraftAngeliteChest(final Block block) {
        super(block);

        world = (CraftWorld) block.getWorld();
        farm = (TileEntityAngeliteChest) world.getTileEntityAt(getX(), getY(), getZ());
    }

    public Inventory getBlockInventory() {
        return new CraftInventory(farm);
    }

    public Inventory getInventory() {
        int x = getX();
        int y = getY();
        int z = getZ();
        
        // The logic here is basically identical to the logic in Blockfarm.interact
        CraftInventory inventory = new CraftInventory(farm);

        return inventory;
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        boolean result = super.update(force, applyPhysics);

        if (result) {
            farm.update();
        }

        return result;
    }
}
