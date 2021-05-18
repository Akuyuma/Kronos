package fr.maxlego08.kronos.items;

import java.util.Set;

import com.google.common.collect.Sets;

import fr.maxlego08.kronos.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemFarmTools extends ItemTool
{
    private static final Set field_150917_c = Sets.newHashSet(new Block[] {
    		Blocks.pumpkin, Blocks.melon_block, Blocks.lit_pumpkin});
    private static final String __OBFID = "CL_00001770";

    public ItemFarmTools(Item.ToolMaterial p_i45327_1_)
    {
        super(3.0F, p_i45327_1_, field_150917_c);
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_.getMaterial() != Material.wood && p_150893_2_.getMaterial() != Material.plants && p_150893_2_.getMaterial() != Material.vine ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.efficiencyOnProperMaterial;
    }
}
