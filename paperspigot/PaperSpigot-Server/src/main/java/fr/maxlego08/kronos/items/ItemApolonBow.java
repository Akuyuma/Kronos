package fr.maxlego08.kronos.items;

import org.bukkit.event.entity.EntityCombustEvent;

import net.minecraft.server.Enchantment;
import net.minecraft.server.EnchantmentManager;
import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemBow;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Items;
import net.minecraft.server.World;

public class ItemApolonBow extends ItemBow{

	public ItemApolonBow() {
		setMaxDurability(1036);
	}
	
    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i) {
        boolean flag = entityhuman.abilities.canInstantlyBuild || EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_INFINITE.id, itemstack) > 0;

        if (flag || entityhuman.inventory.b(Items.ARROW)) {
            int j = this.d_(itemstack) - i;
            float f = (float) j / 20.0F;

            f = (f * f + f * 2.0F) / 3.0F;
            if ((double) f < 0.1D) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(world, entityhuman, f * 2.0F);

            if (f == 1.0F) {
                entityarrow.setCritical(true);
            }

            int k = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_DAMAGE.id, itemstack);

            if (k > 0) {
                entityarrow.b(entityarrow.e() + (double) k * 0.5D + 0.5D);
            }

            int l = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK.id, itemstack);

            if (l > 0) {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_FIRE.id, itemstack) > 0) {
                // CraftBukkit start - call EntityCombustEvent
                EntityCombustEvent event = new EntityCombustEvent(entityarrow.getBukkitEntity(), 100);
                entityarrow.world.getServer().getPluginManager().callEvent(event);

                if (!event.isCancelled()) {
                    entityarrow.setOnFire(event.getDuration());
                }
                // CraftBukkit end
            }

            // CraftBukkit start
            org.bukkit.event.entity.EntityShootBowEvent event = org.bukkit.craftbukkit.event.CraftEventFactory.callEntityShootBowEvent(entityhuman, itemstack, entityarrow, f);
            if (event.isCancelled()) {
                event.getProjectile().remove();
                return;
            }

            if (event.getProjectile() == entityarrow.getBukkitEntity()) {
                world.addEntity(entityarrow);
            }
            // CraftBukkit end

            itemstack.damage(1, entityhuman);
            world.makeSound(entityhuman, "random.bow", 1.0F, 1.0F / (g.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (flag) {
                entityarrow.fromPlayer = 2;
            } else {
                entityhuman.inventory.a(Items.ARROW);
            }

            if (!world.isStatic) {
                // world.addEntity(entityarrow); // CraftBukkit - moved up
            }
        }
    }
	
}
