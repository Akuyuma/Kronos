package fr.maxlego08.kronos.craftbukkit.block;

import org.bukkit.block.BlockState;
import org.bukkit.block.ContainerBlock;
import org.bukkit.inventory.Inventory;

public interface AngeliteChest  extends BlockState, ContainerBlock{

    /**
     * Returns the chest's inventory. If this is a double chest, it returns
     * just the portion of the inventory linked to this half of the chest.
     *
     * @return The inventory.
     */
    Inventory getBlockInventory();
	
}
