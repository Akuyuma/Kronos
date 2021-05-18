package fr.maxlego08.kronos.items;

import fr.maxlego08.kronos.entities.EntityDynamite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDynamite extends Item{

	
	public ItemDynamite()
	{
		setFull3D();
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (player.inventory.consumeInventoryItem(this))
		{
			world.playSoundAtEntity(player, "game.tnt.primed", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			world.spawnEntityInWorld(new EntityDynamite(world, player, 40 + itemRand.nextInt(10), false));
		}
		return stack;
	}	
	
}
