package fr.maxlego08.kronos.items;

import java.util.Set;

import com.google.common.collect.Sets;


import net.minecraft.server.Block;
import net.minecraft.server.Blocks;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemTool;
import net.minecraft.server.MathHelper;
import net.minecraft.server.World;

public class ItemHammer extends ItemTool{

    private static final Set field_150915_c = Sets.newHashSet(new Block[] {Blocks.COBBLESTONE, Blocks.STONE});
	
    protected ItemHammer(EnumToolMaterial p_i45347_1_)
    {
        super(2.0F, p_i45347_1_, field_150915_c);
    }
    
    @Override
    public boolean a(ItemStack item, World world, Block block, int x, int y, int z, EntityLiving arg6) {
    	
    	switch (determineOrientation(world, x, y, z, arg6)) {
    		
	    	case 0:
                for (int x1 = -1; x1 < 2; x1++) {
                    for (int z1 = -1; z1 < 2; z1++) {
                    	if (world.getType(x + x1, y, z+z1).f(world, x + x1, y, z+z1) >= 0.0F){
                    		
                    		if (world.getType(x + x1, y, z+z1) == Blocks.STONE || world.getType(x + x1, y, z+z1) == Blocks.COBBLESTONE){
                    			
                    			world.getType(x + x1, y, z+z1).a(world, (EntityHuman)arg6, x + x1, y, z+z1, world.getData(x + x1, y, z+z1));
                    			world.setAir(x + x1, y, z+z1);
                    		}
                    		
                    	}
                    }
                }
	    		break;
	    	case 1:
                for (int y1 = -1; y1 < 2; y1++) {
                    for (int z1 = -1; z1 < 2; z1++) {
	    				if (world.getType(x, y + y1, z+z1).f(world, x, y + y1, z+z1) >= 0.0F){
	    					
	    					if (world.getType(x , y + y1, z+z1) == Blocks.STONE || world.getType(x, y + y1, z+z1) == Blocks.COBBLESTONE){
	    						
	    						world.getType(x, y  + y1, z+z1).a(world, (EntityHuman)arg6, x,  y + y1, z+z1, world.getData(x, y + y1, z+z1));
	    						world.setAir(x , y + y1, z+z1);
	    					}
	    					
	    				}
	    			}
	    		}
	    		break;
	    	case 2:
                for (int x1 = -1; x1 < 2; x1++) {
                    for (int y1 = -1; y1 < 2; y1++) {
	    				if (world.getType(x + x1, y + y1, z).f(world, x+x1, y + y1, z) >= 0.0F){
	    					
	    					if (world.getType(x + x1 , y + y1, z) == Blocks.STONE || world.getType(x + x1, y + y1, z) == Blocks.COBBLESTONE){
	    						
	    						world.getType(x + x1, y  + y1, z).a(world, (EntityHuman)arg6, x + x1,  y + y1, z, world.getData(x + x1, y + y1, z));
	    						world.setAir(x + x1 , y + y1, z);
	    					}
	    					
	    				}
	    			}
	    		}
	    		break;
    	
    	}
    	
    	
    	return super.a(item, world, block, x, y, z, arg6);
    }
 
    public int determineOrientation(World world, int x, int y, int z, EntityLiving living) {
        if (MathHelper.abs((float) living.locX - x) < 2.0F && MathHelper.abs((float) living.locZ - z) < 2.0F) {
            double d0 = living.locY + 1.82D - (double) living.height;
 
            if (d0 - y > 2.0D || y - d0 > 0.0D) {
                return 0;
            }
        }
        float rotation = MathHelper.abs(living.yaw);
        return (rotation > 45F && rotation < 135F) || (rotation > 225F && rotation < 315F) ? 1 : 2;
    }
 
    public boolean canDestroySpecialBlock(Block paramBlock)
    {
      if (paramBlock == Blocks.STONE){
    	  return true;
      }
      if (paramBlock == Blocks.COBBLESTONE){
    	  return true;
      }
      
      return false;
    }
    
}
