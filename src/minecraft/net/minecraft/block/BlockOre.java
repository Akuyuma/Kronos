package net.minecraft.block;

import java.util.Random;

import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.init.Items;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOre extends Block
{
    private static final String __OBFID = "CL_00000282";

    public BlockOre()
    {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
    	
        return this == Blocks.coal_ore ? Items.coal : 
        	(this == Blocks.diamond_ore ? Items.diamond : 
        	(this == Blocks.lapis_ore ? Items.dye : 
        	(this == Blocks.emerald_ore ? Items.emerald : 
        		
        	(this == Blocks.ANGELITE_ORE ? Items.ANGELITE_INGOT : 
        	(this == Blocks.CELESTINE_ORE ? Items.CELESTINE : 
       		(this == Blocks.MALACHITE_ORE ? Items.MALACHITE : 
       		(this == Blocks.CELENITE_ORE ? Items.CELENITE : 
       		(this == Blocks.XP_ORE ? Item.getItemFromBlock(Blocks.air): 
       		(this == Blocks.RADNOM_ORE ? getRandomItem() : 
        		
        		
        	(this == Blocks.quartz_ore ? Items.quartz : Item.getItemFromBlock(this)))))))))));
    }

    private Item getRandomItem(){
    	Random random = new Random();
    	int r = random.nextInt(100);
    	if (r <= 1){
    		return Items.ANGELITE;
    	}else if (r <= 4){
    		return Items.ANGELITE_INGOT;
    		
    	}else if (r <= 10){
    		return Items.CELESTINE;
    		
    	}else if (r <= 40){
    		return Items.MALACHITE;		
    	}else{
    		return Items.CELENITE;		
    		
    	}
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return this == Blocks.lapis_ore ? 4 + p_149745_1_.nextInt(5) : 1;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        if (p_149679_1_ > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, p_149679_2_, p_149679_1_))
        {
            int var3 = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;

            if (var3 < 0)
            {
                var3 = 0;
            }

            return this.quantityDropped(p_149679_2_) * (var3 + 1);
        }
        else
        {
            return this.quantityDropped(p_149679_2_);
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);

        if (this.getItemDropped(p_149690_5_, p_149690_1_.rand, p_149690_7_) != Item.getItemFromBlock(this))
        {
            int var8 = 0;

            if (this == Blocks.coal_ore)
            {
                var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 0, 2);
            }
            else if (this == Blocks.diamond_ore)
            {
                var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 3, 7);
            }
            else if (this == Blocks.emerald_ore)
            {
                var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 3, 7);
            }
            else if (this == Blocks.lapis_ore)
            {
                var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 2, 5);
            }
            else if (this == Blocks.quartz_ore)
            {
                var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 2, 5);
            }
            else if (this == Blocks.XP_ORE)
            {
            	var8 = MathHelper.getRandomIntegerInRange(p_149690_1_.rand, 10, 15);
            }

            this.dropXpOnBlockBreak(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, var8);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return this == Blocks.lapis_ore ? 4 : 0;
    }
}
