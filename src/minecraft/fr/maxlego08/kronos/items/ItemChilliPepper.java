package fr.maxlego08.kronos.items;

import fr.maxlego08.kronos.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChilliPepper extends ItemFood{

	public ItemChilliPepper(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3) {
		super(p_i45339_1_, p_i45339_2_, p_i45339_3);
	}

	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {

        Block var11 = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
        int var12 = p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_);

        if (var11 == Blocks.log2 && BlockLog.func_150165_c(var12) == 2)
        {
            if (p_77648_7_ == 0)
            {
                return false;
            }

            if (p_77648_7_ == 1)
            {
                return false;
            }

            if (p_77648_7_ == 2)
            {
                --p_77648_6_;
            }

            if (p_77648_7_ == 3)
            {
                ++p_77648_6_;
            }

            if (p_77648_7_ == 4)
            {
                --p_77648_4_;
            }

            if (p_77648_7_ == 5)
            {
                ++p_77648_4_;
            }

            if (p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_))
            {
                int var13 = Blocks.CHILLI_PEPPER.onBlockPlaced(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);
                p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.CHILLI_PEPPER, var13, 2);

                if (!p_77648_2_.capabilities.isCreativeMode)
                {
                    --p_77648_1_.stackSize;
                }
            }

            return true;
        }
        return false;
    }
	
    public static boolean func_150919_a(ItemStack p_150919_0_, World p_150919_1_, int p_150919_2_, int p_150919_3_, int p_150919_4_)
    {
        Block var5 = p_150919_1_.getBlock(p_150919_2_, p_150919_3_, p_150919_4_);

        if (var5 instanceof IGrowable)
        {
            IGrowable var6 = (IGrowable)var5;

            if (var6.func_149851_a(p_150919_1_, p_150919_2_, p_150919_3_, p_150919_4_, p_150919_1_.isClient))
            {
                if (!p_150919_1_.isClient)
                {
                    if (var6.func_149852_a(p_150919_1_, p_150919_1_.rand, p_150919_2_, p_150919_3_, p_150919_4_))
                    {
                        var6.func_149853_b(p_150919_1_, p_150919_1_.rand, p_150919_2_, p_150919_3_, p_150919_4_);
                    }

                    --p_150919_0_.stackSize;
                }

                return true;
            }
        }

        return false;
    }
	
}
