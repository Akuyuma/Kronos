package net.minecraft.item.crafting;

import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesArmor
{
    private String[][] recipePatterns = new String[][] {{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
    private Object[][] recipeItems;
    private static final String __OBFID = "CL_00000080";

    public RecipesArmor()
    {
        this.recipeItems = new Object[][] {{Items.leather, Blocks.fire, Items.iron_ingot, Items.diamond, Items.gold_ingot, Items.ANGELITE, Items.CELESTINE, Items.MALACHITE, Items.CELENITE},
        {Items.leather_helmet, Items.chainmail_helmet, Items.iron_helmet, Items.diamond_helmet, Items.golden_helmet, Items.ANGELITE_HELMET, Items.CELESTINE_HELMET, Items.MALACHITE_HELMET, Items.CELENITE_HELMET}, 
        {Items.leather_chestplate, Items.chainmail_chestplate, Items.iron_chestplate, Items.diamond_chestplate, Items.golden_chestplate, Items.ANGELITE_CHESTPLATE, Items.CELESTINE_CHESTPLATE, Items.MALACHITE_CHESTPLATE, Items.CELENITE_CHESTPLATE}, 
        {Items.leather_leggings, Items.chainmail_leggings, Items.iron_leggings, Items.diamond_leggings, Items.golden_leggings, Items.ANGELITE_LEGGINGS, Items.CELESTINE_LEGGINGS, Items.MALACHITE_LEGGINGS, Items.CELENITE_LEGGINGS}, 
        {Items.leather_boots, Items.chainmail_boots, Items.iron_boots, Items.diamond_boots, Items.golden_boots, Items.ANGELITE_BOOTS, Items.CELESTINE_BOOTS, Items.MALACHITE_BOOTS, Items.CELENITE_BOOTS}};
    }

    /**
     * Adds the armor recipes to the CraftingManager.
     */
    public void addRecipes(CraftingManager p_77609_1_)
    {
        for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2)
        {
            Object var3 = this.recipeItems[0][var2];

            for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4)
            {
                Item var5 = (Item)this.recipeItems[var4 + 1][var2];
                p_77609_1_.addRecipe(new ItemStack(var5), new Object[] {this.recipePatterns[var4], 'X', var3});
            }
        }
    }
}
