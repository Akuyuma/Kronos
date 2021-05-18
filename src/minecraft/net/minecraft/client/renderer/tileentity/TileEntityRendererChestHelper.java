package net.minecraft.client.renderer.tileentity;

import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.tileentity.TileEntityAngeliteChest;
import fr.maxlego08.kronos.tileentity.TileEntityCeleniteChest;
import fr.maxlego08.kronos.tileentity.TileEntityCelestineChest;
import fr.maxlego08.kronos.tileentity.TileEntityMalachiteChest;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;

public class TileEntityRendererChestHelper
{
    public static TileEntityRendererChestHelper instance = new TileEntityRendererChestHelper();
    private TileEntityChest field_147717_b = new TileEntityChest(0);
    private TileEntityChest field_147718_c = new TileEntityChest(1);
    private TileEntityEnderChest field_147716_d = new TileEntityEnderChest();
    
    /*
     * Maxlego08
     * */
    
    private TileEntityAngeliteChest aC = new TileEntityAngeliteChest();
    private TileEntityCelestineChest cC = new TileEntityCelestineChest();
    private TileEntityMalachiteChest mC = new TileEntityMalachiteChest();
    private TileEntityCeleniteChest ccC = new TileEntityCeleniteChest();
    
    
    private static final String __OBFID = "CL_00000946";

    public void func_147715_a(Block p_147715_1_, int p_147715_2_, float p_147715_3_)
    {
        if (p_147715_1_ == Blocks.ender_chest)
        {
            TileEntityRendererDispatcher.instance.func_147549_a(this.field_147716_d, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else if (p_147715_1_ == Blocks.trapped_chest)
        {
            TileEntityRendererDispatcher.instance.func_147549_a(this.field_147718_c, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else if (p_147715_1_ == Blocks.ANGELITE_CHEST)
        {
        	TileEntityRendererDispatcher.instance.func_147549_a(this.aC, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else if (p_147715_1_ == Blocks.CELESTINE_CHEST)
        {
        	TileEntityRendererDispatcher.instance.func_147549_a(this.cC, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else if (p_147715_1_ == Blocks.MALACHITE_CHEST)
        {
        	TileEntityRendererDispatcher.instance.func_147549_a(this.mC, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else if (p_147715_1_ == Blocks.CELENITE_CHEST)
        {
        	TileEntityRendererDispatcher.instance.func_147549_a(this.ccC, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else
        {
            TileEntityRendererDispatcher.instance.func_147549_a(this.field_147717_b, 0.0D, 0.0D, 0.0D, 0.0F);
        }
    }
}
