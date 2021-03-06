package fr.maxlego08.kronos.utils;

import java.util.IdentityHashMap;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Maps;

import fr.maxlego08.kronos.utils.IItemRenderer.ItemRenderType;
import fr.maxlego08.kronos.utils.IItemRenderer.ItemRendererHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RenderItemHelper {

    private static IdentityHashMap<Item, IItemRenderer> customItemRenderers = Maps.newIdentityHashMap();

    public static void registerItemRenderer(Item item, IItemRenderer renderer)
    {
        customItemRenderers.put(item, renderer);
    }
    
    public static boolean renderInventoryItem(RenderBlocks renderBlocks, TextureManager engine, ItemStack item, boolean inColor, float zLevel, float x, float y)
    {
        IItemRenderer customRenderer = getItemRenderer(item, ItemRenderType.INVENTORY);
        if (customRenderer == null)
        {
            return false;
        }

        engine.bindTexture(item.getItemSpriteNumber() == 0 ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
        if (customRenderer.shouldUseRenderHelper(ItemRenderType.INVENTORY, item, ItemRendererHelper.INVENTORY_BLOCK))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(x - 2, y + 3, -3.0F + zLevel);
            GL11.glScalef(10F, 10F, 10F);
            GL11.glTranslatef(1.0F, 0.5F, 1.0F);
            GL11.glScalef(1.0F, 1.0F, -1F);
            GL11.glRotatef(210F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);

            if(inColor)
            {
                int color = item.getItem().getColorFromItemStack(item, 0);
                float r = (float)(color >> 16 & 0xff) / 255F;
                float g = (float)(color >> 8 & 0xff) / 255F;
                float b = (float)(color & 0xff) / 255F;
                GL11.glColor4f(r, g, b, 1.0F);
            }

            GL11.glRotatef(-90F, 0.0F, 1.0F, 0.0F);
            renderBlocks.useInventoryTint = inColor;
            customRenderer.renderItem(ItemRenderType.INVENTORY, item, renderBlocks);
            renderBlocks.useInventoryTint = true;
            GL11.glPopMatrix();
        }
        else
        {
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glPushMatrix();
            GL11.glTranslatef(x, y, -3.0F + zLevel);

            if (inColor)
            {
                int color = item.getItem().getColorFromItemStack(item, 0);
                float r = (float)(color >> 16 & 255) / 255.0F;
                float g = (float)(color >> 8 & 255) / 255.0F;
                float b = (float)(color & 255) / 255.0F;
                GL11.glColor4f(r, g, b, 1.0F);
            }

            customRenderer.renderItem(ItemRenderType.INVENTORY, item, renderBlocks);
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
        }

        return true;
    }
	
    public static boolean renderEntityItem(EntityItem entity, ItemStack item, float bobing, float rotation, Random random, TextureManager engine, RenderBlocks renderBlocks, int count)
    {
        IItemRenderer customRenderer = getItemRenderer(item, ItemRenderType.ENTITY);
        if (customRenderer == null)
        {
            return false;
        }

        if (customRenderer.shouldUseRenderHelper(ItemRenderType.ENTITY, item, ItemRendererHelper.ENTITY_ROTATION))
        {
            GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        }
        if (!customRenderer.shouldUseRenderHelper(ItemRenderType.ENTITY, item, ItemRendererHelper.ENTITY_BOBBING))
        {
            GL11.glTranslatef(0.0F, -bobing, 0.0F);
        }
        boolean is3D = customRenderer.shouldUseRenderHelper(ItemRenderType.ENTITY, item, ItemRendererHelper.BLOCK_3D);

        engine.bindTexture(item.getItemSpriteNumber() == 0 ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
        Block block = item.getItem() instanceof ItemBlock ? Block.getBlockFromItem(item.getItem()) : null;
        if (is3D || (block != null && RenderBlocks.renderItemIn3d(block.getRenderType())))
        {
            int renderType = (block != null ? block.getRenderType() : 1);
            float scale = (renderType == 1 || renderType == 19 || renderType == 12 || renderType == 2 ? 0.5F : 0.25F);
            boolean blend = block != null && block.getRenderBlockPass() > 0;

            if (RenderItem.renderInFrame)
            {
                GL11.glScalef(1.25F, 1.25F, 1.25F);
                GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (blend)
            {
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            }

            GL11.glScalef(scale, scale, scale);

            for(int j = 0; j < count; j++)
            {
                GL11.glPushMatrix();
                if (j > 0)
                {
                    GL11.glTranslatef(
                        ((random.nextFloat() * 2.0F - 1.0F) * 0.2F) / scale,
                        ((random.nextFloat() * 2.0F - 1.0F) * 0.2F) / scale,
                        ((random.nextFloat() * 2.0F - 1.0F) * 0.2F) / scale);
                }
                customRenderer.renderItem(ItemRenderType.ENTITY, item, renderBlocks, entity);
                GL11.glPopMatrix();
            }

            if (blend)
            {
                GL11.glDisable(GL11.GL_BLEND);
            }
        }
        else
        {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            customRenderer.renderItem(ItemRenderType.ENTITY, item, renderBlocks, entity);
        }
        return true;
    }
    
    public static void renderEquippedItem(ItemRenderType type, IItemRenderer customRenderer, RenderBlocks renderBlocks, EntityLivingBase entity, ItemStack item)
    {
        if (customRenderer.shouldUseRenderHelper(type, item, ItemRendererHelper.EQUIPPED_BLOCK))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            customRenderer.renderItem(type, item, renderBlocks, entity);
            GL11.glPopMatrix();
        }
        else
        {
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glTranslatef(0.0F, -0.3F, 0.0F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
            customRenderer.renderItem(type, item, renderBlocks, entity);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }
    
    public static IItemRenderer getItemRenderer(ItemStack item, ItemRenderType type)
    {
        IItemRenderer renderer = customItemRenderers.get(item.getItem());
        if (renderer != null && renderer.handleRenderType(item, type))
        {
            return renderer;
        }
        return null;
    }    
}
