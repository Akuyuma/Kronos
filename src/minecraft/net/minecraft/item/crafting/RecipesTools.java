package net.minecraft.item.crafting;

import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesTools
{
    private String[][] recipePatterns = new String[][] {{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
    private Object[][] recipeItems;
    private static final String __OBFID = "CL_00000096";

    public RecipesTools()
    {
        this.recipeItems = new Object[][] {{
        	Blocks.planks, Blocks.cobblestone, Items.iron_ingot, Items.diamond, Items.gold_ingot, Items.ANGELITE, Items.CELESTINE, Items.MALACHITE, Items.CELENITE}, 
        	{Items.wooden_pickaxe, Items.stone_pickaxe, Items.iron_pickaxe, Items.diamond_pickaxe, Items.golden_pickaxe, Items.ANGELITE_PICKAXE, Items.CELESTINE_PICKAXE, Items.MALACHITE_PICKAXE, Items.CELENITE_PICKAXE}, 
        	{Items.wooden_shovel, Items.stone_shovel, Items.iron_shovel, Items.diamond_shovel, Items.golden_shovel, Items.ANGELITE_SHOVEL, Items.CELESTINE_SHOVEL, Items.MALACHITE_SHOVEL, Items.CELENITE_SHOVEL}, 
        	{Items.wooden_axe, Items.stone_axe, Items.iron_axe, Items.diamond_axe, Items.golden_axe, Items.ANGELITE_AXE, Items.CELESTINE_AXE, Items.MALACHITE_AXE, Items.CELENITE_AXE}, 
        	{Items.wooden_hoe, Items.stone_hoe, Items.iron_hoe, Items.diamond_hoe, Items.golden_hoe, Items.ANGELITE_HOE, Items.CELESTINE_HOE, Items.MALACHITE_HOE, Items.CELENITE_HOE}};
    }

    /**
     * Adds the tool recipes to the CraftingManager.
     */
    public void addRecipes(CraftingManager p_77586_1_)
    {
        for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2)
        {
            Object var3 = this.recipeItems[0][var2];

            for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4)
            {
                Item var5 = (Item)this.recipeItems[var4 + 1][var2];
                p_77586_1_.addRecipe(new ItemStack(var5), new Object[] {this.recipePatterns[var4], '#', Items.stick, 'X', var3});
            }
        }

        p_77586_1_.addRecipe(new ItemStack(Items.shears), new Object[] {" #", "# ", '#', Items.iron_ingot});
    }
}
