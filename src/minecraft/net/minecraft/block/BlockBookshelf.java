package net.minecraft.block;

import java.util.Random;

import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.init.Items;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockBookshelf extends Block
{
    private static final String __OBFID = "CL_00000206";

    public BlockBookshelf()
    {
        super(Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ != 1 && p_149691_1_ != 0 ? super.getIcon(p_149691_1_, p_149691_2_) : Blocks.planks.getBlockTextureFromSide(p_149691_1_);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return 3;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Items.book;
    }
}
