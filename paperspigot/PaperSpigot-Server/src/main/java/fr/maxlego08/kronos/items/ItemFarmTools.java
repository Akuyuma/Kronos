package fr.maxlego08.kronos.items;

import java.util.Set;

import net.minecraft.server.Block;
import net.minecraft.server.Blocks;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemTool;
import net.minecraft.server.Material;
import net.minecraft.util.com.google.common.collect.Sets;

public class ItemFarmTools extends ItemTool {

    @SuppressWarnings("rawtypes")
	private static final Set c = Sets.newHashSet(new Block[] { Blocks.MELON, Blocks.JACK_O_LANTERN, Blocks.PUMPKIN});

    public ItemFarmTools(EnumToolMaterial enumtoolmaterial) {
        super(3.0F, enumtoolmaterial, c);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block.getMaterial() != Material.WOOD && block.getMaterial() != Material.PLANT && block.getMaterial() != Material.REPLACEABLE_PLANT ? super.getDestroySpeed(itemstack, block) : this.a;
    }
}
