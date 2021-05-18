package fr.maxlego08.kronos.items;

import fr.maxlego08.kronos.entities.EntityDynamite;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class ItemDynamite
  extends Item
{
  public ItemStack a(ItemStack stack, World world, EntityHuman player)
  {
    if (player.inventory.a(this))
    {
      world.makeSound(player, "game.tnt.primed", 1.0F, 1.0F / (g.nextFloat() * 0.4F + 0.8F));
      world.addEntity(new EntityDynamite(world, player, 40, false));
    }
    return stack;
  }
}
