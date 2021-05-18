package fr.maxlego08.kronos.items;

import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.github.paperspigot.PaperSpigotConfig;

import net.minecraft.server.Block;
import net.minecraft.server.Blocks;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumMovingObjectType;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Items;
import net.minecraft.server.Material;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.World;

public class ItemWaterCapsule extends Item {

    private Block a;

    public ItemWaterCapsule(Block block) {
        // PaperSpigot start - Stackable Buckets
        if (
                (block == Blocks.WATER && PaperSpigotConfig.stackableWaterBuckets)) {
            this.maxStackSize = org.bukkit.Material.WATER_CAPSULE_EMPTY.getMaxStackSize();
        } else {
            this.maxStackSize = 1;
        }
        // PaperSpigot end
        this.a = block;
        this.a(CreativeModeTab.f);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        boolean flag = this.a == Blocks.AIR;
        MovingObjectPosition movingobjectposition = this.a(world, entityhuman, flag);

        if (movingobjectposition == null) {
            return itemstack;
        } else {
            if (movingobjectposition.type == EnumMovingObjectType.BLOCK) {
                int i = movingobjectposition.b;
                int j = movingobjectposition.c;
                int k = movingobjectposition.d;

                if (!world.a(entityhuman, i, j, k)) {
                    return itemstack;
                }

                if (flag) {
                    if (!entityhuman.a(i, j, k, movingobjectposition.face, itemstack)) {
                        return itemstack;
                    }

                    Material material = world.getType(i, j, k).getMaterial();
                    int l = world.getData(i, j, k);

                    if (material == Material.WATER && l == 0) {
                        // CraftBukkit start
                        PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, i, j, k, -1, itemstack, Items.WATER_CAPSULE);

                        if (event.isCancelled()) {
                            return itemstack;
                        }
                        // CraftBukkit end
                        world.setAir(i, j, k);
                        return this.a(itemstack, entityhuman, Items.WATER_CAPSULE, event.getItemStack()); // CraftBukkit - added Event stack
                    }
                } else {
                    if (this.a == Blocks.AIR) {
                    	
                    	return new ItemStack(Items.WATER_CAPSULE_EMPTY);
  
                    }

                    int clickedX = i, clickedY = j, clickedZ = k;
                    // CraftBukkit end

                    if (movingobjectposition.face == 0) {
                        --j;
                    }

                    if (movingobjectposition.face == 1) {
                        ++j;
                    }

                    if (movingobjectposition.face == 2) {
                        --k;
                    }

                    if (movingobjectposition.face == 3) {
                        ++k;
                    }

                    if (movingobjectposition.face == 4) {
                        --i;
                    }

                    if (movingobjectposition.face == 5) {
                        ++i;
                    }

                    if (!entityhuman.a(i, j, k, movingobjectposition.face, itemstack)) {
                        return itemstack;
                    }

                    // CraftBukkit start
                    PlayerBucketEmptyEvent event = CraftEventFactory.callPlayerBucketEmptyEvent(entityhuman, clickedX, clickedY, clickedZ, movingobjectposition.face, itemstack);

                    if (event.isCancelled()) {
                        return itemstack;
                    }
                    // CraftBukkit end

                    if (this.a(world, i, j, k) && !entityhuman.abilities.canInstantlyBuild) {
                        // PaperSpigot start - Stackable Buckets
                        if ((this == Items.WATER_CAPSULE && PaperSpigotConfig.stackableLavaBuckets) ) {
                            --itemstack.count;
                            if (itemstack.count <= 0) {
                                return CraftItemStack.asNMSCopy(event.getItemStack());
                            }
                            if (!entityhuman.inventory.pickup(CraftItemStack.asNMSCopy(event.getItemStack()))) {
                                entityhuman.drop(CraftItemStack.asNMSCopy(event.getItemStack()), false);
                            }
                            return itemstack;
                        }
                        // PaperSpigot end
                        return CraftItemStack.asNMSCopy(event.getItemStack()); // CraftBukkit
                    }
                }
            }

            return itemstack;
        }
    }

    // CraftBukkit - added ob.ItemStack result - TODO: Is this... the right way to handle this?
    private ItemStack a(ItemStack itemstack, EntityHuman entityhuman, Item item, org.bukkit.inventory.ItemStack result) {
        if (entityhuman.abilities.canInstantlyBuild) {
            return itemstack;
        } else if (--itemstack.count <= 0) {
            return CraftItemStack.asNMSCopy(result); // CraftBukkit
        } else {
            if (!entityhuman.inventory.pickup(CraftItemStack.asNMSCopy(result))) { // CraftBukkit
                entityhuman.drop(CraftItemStack.asNMSCopy(result), false); // CraftBukkit
            }

            return itemstack;
        }
    }

    public boolean a(World world, int i, int j, int k) {
        if (this.a == Blocks.AIR) {
            return false;
        } else {
            Material material = world.getType(i, j, k).getMaterial();
            boolean flag = !material.isBuildable();

            if (!world.isEmpty(i, j, k) && !flag) {
                return false;
            } else {
                if (world.worldProvider.f && this.a == Blocks.WATER) {
                    world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l) {
                        world.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                } else {
                    if (!world.isStatic && flag && !material.isLiquid()) {
                        world.setAir(i, j, k, true);
                    }

                    world.setTypeAndData(i, j, k, this.a, 0, 3);
                }

                return true;
            }
        }
    }
}
