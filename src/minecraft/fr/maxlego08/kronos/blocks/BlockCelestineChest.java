package fr.maxlego08.kronos.blocks;

import java.util.Iterator;
import java.util.Random;

import fr.maxlego08.kronos.tileentity.TileEntityCelestineChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCelestineChest extends BlockContainer
{
    private final Random field_149955_b = new Random();
    public final int field_149956_a;
 
    public BlockCelestineChest(int p_i45397_1_)
    {
        super(Material.wood);
        this.field_149956_a = p_i45397_1_;
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }
 
    public boolean isOpaqueCube()
    {
        return false;
    }
 
    public boolean renderAsNormalBlock()
    {
        return false;
    }
 
    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 22;
    }
 
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        if (p_149719_1_.getBlock(p_149719_2_, p_149719_3_, p_149719_4_ - 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (p_149719_1_.getBlock(p_149719_2_, p_149719_3_, p_149719_4_ + 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (p_149719_1_.getBlock(p_149719_2_ - 1, p_149719_3_, p_149719_4_) == this)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (p_149719_1_.getBlock(p_149719_2_ + 1, p_149719_3_, p_149719_4_) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        else
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }
 
   
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        Block var7 = p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_ - 1);
        Block var8 = p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_ + 1);
        Block var9 = p_149689_1_.getBlock(p_149689_2_ - 1, p_149689_3_, p_149689_4_);
        Block var10 = p_149689_1_.getBlock(p_149689_2_ + 1, p_149689_3_, p_149689_4_);
        byte var11 = 0;
        int var12 = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
 
        if (var12 == 0)
        {
            var11 = 2;
        }
 
        if (var12 == 1)
        {
            var11 = 5;
        }
 
        if (var12 == 2)
        {
            var11 = 3;
        }
 
        if (var12 == 3)
        {
            var11 = 4;
        }
 
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, var11, 0);
    }
 
 
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        TileEntityCelestineChest var6 = (TileEntityCelestineChest)p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);
 
        if (var6 != null)
        {
            var6.updateContainingBlockInfo();
        }
    }
 
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        TileEntityCelestineChest var7 = (TileEntityCelestineChest)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
 
        if (var7 != null)
        {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
            {
                ItemStack var9 = var7.getStackInSlot(var8);
 
                if (var9 != null)
                {
                    float var10 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    float var11 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    EntityItem var14;
 
                    for (float var12 = this.field_149955_b.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; p_149749_1_.spawnEntityInWorld(var14))
                    {
                        int var13 = this.field_149955_b.nextInt(21) + 10;
 
                        if (var13 > var9.stackSize)
                        {
                            var13 = var9.stackSize;
                        }
 
                        var9.stackSize -= var13;
                        var14 = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + var10), (double)((float)p_149749_3_ + var11), (double)((float)p_149749_4_ + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                        float var15 = 0.05F;
                        var14.motionX = (double)((float)this.field_149955_b.nextGaussian() * var15);
                        var14.motionY = (double)((float)this.field_149955_b.nextGaussian() * var15 + 0.2F);
                        var14.motionZ = (double)((float)this.field_149955_b.nextGaussian() * var15);
 
                        if (var9.hasTagCompound())
                        {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }
                    }
                }
            }
 
            p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
        }
 
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
 
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (p_149727_1_.isClient)
        {
            return true;
        }
        else
        {
            IInventory var10 = this.func_149951_m(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
 
            if (var10 != null)
            {
                p_149727_5_.displayGUIChest(var10);
            }
 
            return true;
        }
    }
 
    public IInventory func_149951_m(World p_149951_1_, int p_149951_2_, int p_149951_3_, int p_149951_4_)
    {
        Object var5 = (TileEntityCelestineChest)p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_);
 
        if (var5 == null)
        {
            return null;
        }
        else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_ + 1, p_149951_4_).isNormalCube())
        {
            return null;
        }
        else if (func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_))
        {
            return null;
        }
        else if (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_ + 1, p_149951_4_).isNormalCube() || func_149953_o(p_149951_1_, p_149951_2_ - 1, p_149951_3_, p_149951_4_)))
        {
            return null;
        }
        else if (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_ + 1, p_149951_4_).isNormalCube() || func_149953_o(p_149951_1_, p_149951_2_ + 1, p_149951_3_, p_149951_4_)))
        {
            return null;
        }
        else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this && (p_149951_1_.getBlock(p_149951_2_, p_149951_3_ + 1, p_149951_4_ - 1).isNormalCube() || func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ - 1)))
        {
            return null;
        }
        else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this && (p_149951_1_.getBlock(p_149951_2_, p_149951_3_ + 1, p_149951_4_ + 1).isNormalCube() || func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ + 1)))
        {
            return null;
        }
       
        return (IInventory)var5;
       
    }
 
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
    	TileEntityCelestineChest var3 = new TileEntityCelestineChest();
        return var3;
    }
 
    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return this.field_149956_a == 1;
    }
 
    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
    {
        if (!this.canProvidePower())
        {
            return 0;
        }
        else
        {
            int var6 = ((TileEntityCelestineChest)p_149709_1_.getTileEntity(p_149709_2_, p_149709_3_, p_149709_4_)).field_145987_o;
            return MathHelper.clamp_int(var6, 0, 15);
        }
    }
 
    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
    {
        return p_149748_5_ == 1 ? this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_) : 0;
    }
 
    private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_)
    {
        Iterator var4 = p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1))).iterator();
        EntityOcelot var6;
 
        do
        {
            if (!var4.hasNext())
            {
                return false;
            }
 
            EntityOcelot var5 = (EntityOcelot)var4.next();
            var6 = (EntityOcelot)var5;
        }
        while (!var6.isSitting());
 
        return true;
    }
 
    public boolean hasComparatorInputOverride()
    {
        return true;
    }
 
    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        return Container.calcRedstoneFromInventory(this.func_149951_m(p_149736_1_, p_149736_2_, p_149736_3_, p_149736_4_));
    }
 
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("planks_oak");
    }
}
