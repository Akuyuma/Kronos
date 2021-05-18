package fr.maxlego08.kronos.items;

import fr.maxlego08.kronos.entities.EntityGodPearl;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class ItemGodPearl extends Item{

    public ItemGodPearl()
    {
        maxStackSize = 16;
        a(CreativeModeTab.f);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        if(entityhuman.abilities.canInstantlyBuild)
            return itemstack;
        --itemstack.count;
        world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
        if(!world.isStatic)
            world.addEntity(new EntityGodPearl(world, entityhuman));
        return itemstack;
    }
	
}
