package fr.maxlego08.kronos.blocks;

import java.util.Random;

import org.bukkit.craftbukkit.event.CraftEventFactory;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockDirectional;
import net.minecraft.server.BlockLogAbstract;
import net.minecraft.server.Blocks;
import net.minecraft.server.Direction;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.IBlockFragilePlantElement;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Items;
import net.minecraft.server.Material;
import net.minecraft.server.MathHelper;
import net.minecraft.server.World;

public class BlockChilliPepper extends BlockDirectional implements IBlockFragilePlantElement {

    public BlockChilliPepper() {
        super(Material.PLANT);
        this.a(true);
    }

    @Override
    public void a(World world, int i, int j, int k, Random random) {
        if (!this.j(world, i, j, k)) {
            this.b(world, i, j, k, world.getData(i, j, k), 0);
            world.setTypeAndData(i, j, k, getById(0), 0, 2);
        } else if (world.random.nextInt(5) == 0) {
            int l = world.getData(i, j, k);
            int i1 = c(l);

            if (i1 < 2) {
                ++i1;
                // CraftBukkit
                CraftEventFactory.handleBlockGrowEvent(world, i, j, k, this, i1 << 2 | l(l));
            }
        }
    }
    @Override
    public boolean j(World world, int i, int j, int k) {
        int l = l(world.getData(i, j, k));

        i += Direction.a[l];
        k += Direction.b[l];
        Block block = world.getType(i, j, k);

        return block == Blocks.LOG2 && BlockLogAbstract.c(world.getData(i, j, k)) == 2;
    }

    @Override
    public int b() {
        return 28;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public AxisAlignedBB a(World world, int i, int j, int k) {
        this.updateShape(world, i, j, k);
        return super.a(world, i, j, k);
    }

    @Override
    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);
        int i1 = l(l);
        int j1 = c(l);
        int k1 = 4 + j1 * 2;
        int l1 = 5 + j1 * 2;
        float f = (float) k1 / 2.0F;

        switch (i1) {
        case 0:
            this.a((8.0F - f) / 16.0F, (12.0F - (float) l1) / 16.0F, (15.0F - (float) k1) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
            break;

        case 1:
            this.a(0.0625F, (12.0F - (float) l1) / 16.0F, (8.0F - f) / 16.0F, (1.0F + (float) k1) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
            break;

        case 2:
            this.a((8.0F - f) / 16.0F, (12.0F - (float) l1) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + (float) k1) / 16.0F);
            break;

        case 3:
            this.a((15.0F - (float) k1) / 16.0F, (12.0F - (float) l1) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
        }
    }
    @Override
    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        int l = ((MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) + 0) % 4;

        world.setData(i, j, k, l, 2);
    }
    @Override
    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        if (l == 1 || l == 0) {
            l = 2;
        }

        return Direction.f[Direction.e[l]];
    }
    @Override
    public void doPhysics(World world, int i, int j, int k, Block block) {
        if (!this.j(world, i, j, k)) {
            this.b(world, i, j, k, world.getData(i, j, k), 0);
            world.setTypeAndData(i, j, k, getById(0), 0, 2);
        }
    }
    
    public static int c(int i) {
        return (i & 12) >> 2;
    }
    
    @Override
    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        int j1 = c(l);
        byte b0 = 1;

        if (j1 >= 2) {
            b0 = 3;
        }

        for (int k1 = 0; k1 < b0; ++k1) {
            this.a(world, i, j, k, new ItemStack(Items.CHILLI_PEPPER_ITEM));
        }
    }
    @Override
    public int getDropData(World world, int i, int j, int k) {
        return 3;
    }
    @Override
    public boolean a(World world, int i, int j, int k, boolean flag) {
        int l = world.getData(i, j, k);
        int i1 = c(l);

        return i1 < 2;
    }
    @Override
    public boolean a(World world, Random random, int i, int j, int k) {
        return true;
    }
    @Override
    public void b(World world, Random random, int i, int j, int k) {
        int l = world.getData(i, j, k);
        int i1 = BlockDirectional.l(l);
        int j1 = c(l);

        ++j1;
        CraftEventFactory.handleBlockGrowEvent(world, i, j, k, this, j1 << 2 | i1); // CraftBukkit
    }
}
