package net.minecraft.block;

import java.util.Random;

import fr.maxlego08.kronos.init.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockStone extends Block
{
    private static final String __OBFID = "CL_00000317";

    public BlockStone()
    {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(Blocks.cobblestone);
    }
}
