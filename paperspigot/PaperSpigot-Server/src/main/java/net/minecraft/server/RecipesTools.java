package net.minecraft.server;

public class RecipesTools
{
  private String[][] a = { { "XXX", " # ", " # " }, { "X", "#", "#" }, { "XX", "X#", " #" }, { "XX", " #", " #" } };
  private Object[][] b = { { Blocks.WOOD, Blocks.COBBLESTONE, Items.IRON_INGOT, Items.DIAMOND, Items.GOLD_INGOT, Items.ANGELITE, Items.CELESTINE, Items.MALACHITE, Items.CELENITE},
		  { Items.WOOD_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.GOLD_PICKAXE, Items.ANGELITE_PICKAXE, Items.CELESTINE_PICKAXE, Items.MALACHITE_PICKAXE, Items.CELENITE_PICKAXE}, 
		  { Items.WOOD_SPADE, Items.STONE_SPADE, Items.IRON_SPADE, Items.DIAMOND_SPADE, Items.GOLD_SPADE, Items.ANGELITE_SHOVEL, Items.CELESTINE_SHOVEL, Items.MALACHITE_SHOVEL, Items.CELENITE_SHOVEL}, 
		  { Items.WOOD_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.GOLD_AXE, Items.ANGELITE_AXE, Items.CELESTINE_AXE, Items.MALACHITE_AXE, Items.CELENITE_AXE}, 
		  { Items.WOOD_HOE, Items.STONE_HOE, Items.IRON_HOE, Items.DIAMOND_HOE, Items.GOLD_HOE, Items.ANGELITE_HOE, Items.CELESTINE_HOE, Items.MALACHITE_HOE, Items.CELENITE_HOE}};
  
  public void a(CraftingManager paramCraftingManager)
  {
    for (int i = 0; i < this.b[0].length; i++)
    {
      Object localObject = this.b[0][i];
      for (int j = 0; j < this.b.length - 1; j++)
      {
        Item localItem = (Item)this.b[(j + 1)][i];
        paramCraftingManager.registerShapedRecipe(new ItemStack(localItem), new Object[] { this.a[j], Character.valueOf('#'), Items.STICK, Character.valueOf('X'), localObject });
      }
    }
    paramCraftingManager.registerShapedRecipe(new ItemStack(Items.SHEARS), new Object[] { " #", "# ", Character.valueOf('#'), Items.IRON_INGOT });
  }
}
