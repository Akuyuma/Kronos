package fr.maxlego08.kronos.items;

import java.util.Set;

import com.google.common.collect.Sets;

import fr.maxlego08.kronos.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemMultiTool extends ItemTool{

    private static final Set field_150915_c = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium , Blocks.melon_block, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
    private static final String __OBFID = "CL_00000053";

    protected ItemMultiTool(Item.ToolMaterial p_i45347_1_)
    {
        super(2.0F, p_i45347_1_, field_150915_c);
    }

    public boolean func_150897_b(Block p_150897_1_)
    {
        return p_150897_1_ == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (p_150897_1_ != Blocks.diamond_block && p_150897_1_ != Blocks.diamond_ore ? (p_150897_1_ != Blocks.emerald_ore && p_150897_1_ != Blocks.emerald_block ? (p_150897_1_ != Blocks.gold_block && p_150897_1_ != Blocks.gold_ore ? (p_150897_1_ != Blocks.iron_block && p_150897_1_ != Blocks.iron_ore ? (p_150897_1_ != Blocks.lapis_block && p_150897_1_ != Blocks.lapis_ore ? (p_150897_1_ != Blocks.redstone_ore && p_150897_1_ != Blocks.lit_redstone_ore ? (p_150897_1_.getMaterial() == Material.rock ? true : (p_150897_1_.getMaterial() == Material.iron ? true : p_150897_1_.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_.getMaterial() != Material.wood && p_150893_2_.getMaterial() != Material.plants && p_150893_2_.getMaterial() != Material.vine ? super.func_150893_a(p_150893_1_, p_150893_2_) : p_150893_2_.getMaterial() != Material.iron && p_150893_2_.getMaterial() != Material.anvil && p_150893_2_.getMaterial() != Material.rock ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.efficiencyOnProperMaterial;
    }
    
    
    
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
    
    	
    	
    	return true;
    }
    
}
