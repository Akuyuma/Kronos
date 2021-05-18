package net.minecraft.server;

import java.util.Set;
import net.minecraft.util.com.google.common.collect.Sets;

public class ItemPickaxe
  extends ItemTool
{
  private static final Set c = Sets.newHashSet(new Block[] { Blocks.COBBLESTONE, Blocks.DOUBLE_STEP, Blocks.STEP, Blocks.STONE, Blocks.SANDSTONE, Blocks.MOSSY_COBBLESTONE, Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.COAL_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK, Blocks.ICE, Blocks.NETHERRACK, Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.GLOWING_REDSTONE_ORE, Blocks.RAILS, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.ACTIVATOR_RAIL });
  
  public ItemPickaxe(EnumToolMaterial paramEnumToolMaterial)
  {
    super(2.0F, paramEnumToolMaterial, c);
  }
  
  public boolean canDestroySpecialBlock(Block paramBlock)
  {
    if (paramBlock == Blocks.OBSIDIAN) {
      return this.b.d() == 3;
    }
    if ((paramBlock == Blocks.DIAMOND_BLOCK) || (paramBlock == Blocks.DIAMOND_ORE)) {
      return this.b.d() >= 2;
    }
    if ((paramBlock == Blocks.EMERALD_ORE) || (paramBlock == Blocks.EMERALD_BLOCK)) {
      return this.b.d() >= 2;
    }
    if ((paramBlock == Blocks.GOLD_BLOCK) || (paramBlock == Blocks.GOLD_ORE)) {
      return this.b.d() >= 2;
    }
    if ((paramBlock == Blocks.IRON_BLOCK) || (paramBlock == Blocks.IRON_ORE)) {
      return this.b.d() >= 1;
    }
    if ((paramBlock == Blocks.LAPIS_BLOCK) || (paramBlock == Blocks.LAPIS_ORE)) {
      return this.b.d() >= 1;
    }
    if ((paramBlock == Blocks.REDSTONE_ORE) || (paramBlock == Blocks.GLOWING_REDSTONE_ORE)) {
      return this.b.d() >= 2;
    }
    if (paramBlock.getMaterial() == Material.STONE) {
      return true;
    }
    if (paramBlock.getMaterial() == Material.ORE) {
      return true;
    }
    if (paramBlock.getMaterial() == Material.HEAVY) {
      return true;
    }
    return false;
  }
  
  public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock)
  {
    if ((paramBlock.getMaterial() == Material.ORE) || (paramBlock.getMaterial() == Material.HEAVY) || (paramBlock.getMaterial() == Material.STONE)) {
      return this.a;
    }
    return super.getDestroySpeed(paramItemStack, paramBlock);
  }
}
