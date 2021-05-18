package fr.maxlego08.kronos.items;

import fr.maxlego08.kronos.entity.EntityGodPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGodPearl extends Item
{

    public ItemGodPearl()
    {
        setMaxStackSize(16);
    }

    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        p_77659_1_.stackSize--;
        p_77659_2_.playSoundAtEntity(p_77659_3_, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        
        if(!p_77659_2_.isClient)
        	
            p_77659_2_.spawnEntityInWorld(new EntityGodPearl(p_77659_2_, p_77659_3_));
        
        return p_77659_1_;
    }

    private static final String __OBFID = "CL_00000027";
}
