package fr.maxlego08.kronos.items;

import net.minecraft.server.Block;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityInsentient;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityTypes;
import net.minecraft.server.Facing;
import net.minecraft.server.GroupDataEntity;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MathHelper;
import net.minecraft.server.MobEffect;
import net.minecraft.server.MobEffectList;
import net.minecraft.server.World;

public class ItemCreeperDivin extends Item{

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
       
        if (world.isStatic || itemstack.getData() == 48 || itemstack.getData() == 49 || itemstack.getData() == 63 || itemstack.getData() == 64) {
            return true;
        } else {
            Block block = world.getType(i, j, k);

            i += Facing.b[l];
            j += Facing.c[l];
            k += Facing.d[l];
            double d0 = 0.0D;

            if (l == 1 && block.b() == 11) {
                d0 = 0.5D;
            }

            Entity entity = a(world, 2, (double) i + 0.5D, (double) j + d0, (double) k + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLiving && itemstack.hasName()) {
                    ((EntityInsentient) entity).setCustomName("§eCreeper §6Divin");
                    ((EntityInsentient) entity).addEffect(new MobEffect(MobEffectList.SLOWER_MOVEMENT.id, 9999999, 100));
                }

            }
            --itemstack.count;

            return true;
        }
    }
    
    public static Entity a(World world, int i, double d0, double d1, double d2) {
        return spawnCreature(world, i, d0, d1, d2, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.SPAWNER_EGG);
    }

    public static Entity spawnCreature(World world, int i, double d0, double d1, double d2, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason spawnReason) {
            Entity entity = null;

            for (int j = 0; j < 1; ++j) {
                entity = EntityTypes.a(50, world);
                if (entity != null && entity instanceof EntityLiving) {
                    EntityInsentient entityinsentient = (EntityInsentient) entity;

                    entity.setPositionRotation(d0, d1, d2, MathHelper.g(world.random.nextFloat() * 360.0F), 0.0F);
                    entityinsentient.aO = entityinsentient.yaw;
                    entityinsentient.aM = entityinsentient.yaw;
                    entityinsentient.prepare((GroupDataEntity) null);
                    world.addEntity(entity, spawnReason);
                    entityinsentient.r();
                }
            }

            return entity;
        }
	
}
