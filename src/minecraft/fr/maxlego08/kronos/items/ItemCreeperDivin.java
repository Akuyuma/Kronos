package fr.maxlego08.kronos.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemCreeperDivin extends Item{

	public ItemCreeperDivin() {
        this.setMaxStackSize(64);
	}
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        par1ItemStack.damageItem(1, par2EntityPlayer);
        if (par3World.isClient)
        {
            return true;
        }
        else
        {
            Block var11 = par3World.getBlock(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double var12 = 0.0D;
 
            if (par7 == 1 && var11.getRenderType() == 11)
            {
                var12 = 0.5D;
            }
 
            Entity var14 = spawnCreature(par3World, 2, (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D);
 
            if (var14 != null)
            {
                if (var14 instanceof EntityLivingBase)
                {
                    ((EntityLiving)var14).setCustomNameTag("§2Creeper Divin");
                    ((EntityLiving)var14).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 999999, 100));
                }            	
            }
            if (!par2EntityPlayer.capabilities.isCreativeMode)
            {
                --par1ItemStack.stackSize;
            }            
 
            return true;
        }
    }
   
   
    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6)
    {
            Entity var8 = null;
            for (int var9 = 0; var9 < 1; ++var9)
            {
                var8 = EntityList.createEntityByID(50, par0World);
 
                if (var8 != null && var8 instanceof EntityLivingBase)
                {
                    EntityLiving var10 = (EntityLiving)var8;
                    var8.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
                    var10.rotationYawHead = var10.rotationYaw;
                    var10.renderYawOffset = var10.rotationYaw;
                    var10.onSpawnWithEgg((IEntityLivingData)null);
                    par0World.spawnEntityInWorld(var8);
                    var10.playLivingSound();
                }
            }
 
            return var8;
        }
	
}
