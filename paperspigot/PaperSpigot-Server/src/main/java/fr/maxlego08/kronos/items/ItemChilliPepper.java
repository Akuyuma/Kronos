package fr.maxlego08.kronos.items;

import net.minecraft.server.Block;
import net.minecraft.server.BlockLogAbstract;
import net.minecraft.server.Blocks;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IBlockFragilePlantElement;
import net.minecraft.server.ItemFood;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class ItemChilliPepper extends ItemFood{

	public ItemChilliPepper() {
		super(4, 0.5F, false);
	}
	
	@Override
    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        if (!entityhuman.a(i, j, k, l, itemstack)) {
            return false;
        } else {
        	
            Block block = world.getType(i, j, k);
            int i1 = world.getData(i, j, k);

            if (block == Blocks.LOG2 && BlockLogAbstract.c(i1) == 2) {
                if (l == 0) {
                    return false;
                }

                if (l == 1) {
                    return false;
                }

                if (l == 2) {
                    --k;
                }

                if (l == 3) {
                    ++k;
                }

                if (l == 4) {
                    --i;
                }

                if (l == 5) {
                    ++i;
                }

                if (world.isEmpty(i, j, k)) {
                    int j1 = Blocks.CHILLI_PEPPER.getPlacedData(world, i, j, k, l, f, f1, f2, 0);

                    world.setTypeAndData(i, j, k, Blocks.CHILLI_PEPPER, j1, 2);
                    if (!entityhuman.abilities.canInstantlyBuild) {
                        --itemstack.count;
                    }
                }

                return true;
            }
        }
        return false;
    }
	
    public static boolean a(ItemStack itemstack, World world, int i, int j, int k) {
        Block block = world.getType(i, j, k);

        if (block instanceof IBlockFragilePlantElement) {
            IBlockFragilePlantElement iblockfragileplantelement = (IBlockFragilePlantElement) block;

            if (iblockfragileplantelement.a(world, i, j, k, world.isStatic)) {
                if (!world.isStatic) {
                    if (iblockfragileplantelement.a(world, world.random, i, j, k)) {
                        iblockfragileplantelement.b(world, world.random, i, j, k);
                    }

                    --itemstack.count;
                }

                return true;
            }
        }

        return false;
    }
}
