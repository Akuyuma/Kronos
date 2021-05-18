package net.minecraft.client.gui.inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fr.maxlego08.kronos.init.Items;

public class GuiContainerCreative extends InventoryEffectRenderer
{
    private static final ResourceLocation creativeInventoryTabs = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
    private static InventoryBasic field_147060_v = new InventoryBasic("tmp", true, 45);
    private static int selectedTabIndex = CreativeTabs.tabBlock.getTabIndex();
    
    private float currentScroll;
    private boolean isScrolling;
    
    private boolean wasClicking;
    
    private GuiTextField searchField;
    
    private List field_147063_B;
    private Slot field_147064_C;
    private boolean field_147057_D;
    private CreativeCrafting field_147059_E;
    
    private static final String __OBFID = "CL_00000752";

    private static int tabPage = 0;
    private int maxPages = 0;
    
    
    public GuiContainerCreative(EntityPlayer p_i1088_1_)
    {
        super(new GuiContainerCreative.ContainerCreative(p_i1088_1_));
        p_i1088_1_.openContainer = this.field_147002_h;
        this.field_146291_p = true;
        this.field_147000_g = 136;
        this.field_146999_f = 195;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        if (!this.mc.playerController.isInCreativeMode())
        {
            this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
        }
    }

    protected void func_146984_a(Slot slotIn, int slotId, int clickedButton, int clickType)
    {
        this.field_147057_D = true;
        boolean var5 = clickType == 1;
        clickType = slotId == -999 && clickType == 0 ? 4 : clickType;
        ItemStack var7;
        InventoryPlayer var11;

        if (slotIn == null && selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && clickType != 5)
        {
            var11 = this.mc.thePlayer.inventory;

            if (var11.getItemStack() != null)
            {
                if (clickedButton == 0)
                {
                    this.mc.thePlayer.dropPlayerItemWithRandomChoice(var11.getItemStack(), true);
                    this.mc.playerController.sendPacketDropItem(var11.getItemStack());
                    var11.setItemStack((ItemStack)null);
                }

                if (clickedButton == 1)
                {
                    var7 = var11.getItemStack().splitStack(1);
                    this.mc.thePlayer.dropPlayerItemWithRandomChoice(var7, true);
                    this.mc.playerController.sendPacketDropItem(var7);

                    if (var11.getItemStack().stackSize == 0)
                    {
                        var11.setItemStack((ItemStack)null);
                    }
                }
            }
        }
        else
        {
            int var10;

            if (slotIn == this.field_147064_C && var5)
            {
                for (var10 = 0; var10 < this.mc.thePlayer.inventoryContainer.getInventory().size(); ++var10)
                {
                    this.mc.playerController.sendSlotPacket((ItemStack)null, var10);
                }
            }
            else
            {
                ItemStack var6;

                if (selectedTabIndex == CreativeTabs.tabInventory.getTabIndex())
                {
                    if (slotIn == this.field_147064_C)
                    {
                        this.mc.thePlayer.inventory.setItemStack((ItemStack)null);
                    }
                    else if (clickType == 4 && slotIn != null && slotIn.getHasStack())
                    {
                        var6 = slotIn.decrStackSize(clickedButton == 0 ? 1 : slotIn.getStack().getMaxStackSize());
                        this.mc.thePlayer.dropPlayerItemWithRandomChoice(var6, true);
                        this.mc.playerController.sendPacketDropItem(var6);
                    }
                    else if (clickType == 4 && this.mc.thePlayer.inventory.getItemStack() != null)
                    {
                        this.mc.thePlayer.dropPlayerItemWithRandomChoice(this.mc.thePlayer.inventory.getItemStack(), true);
                        this.mc.playerController.sendPacketDropItem(this.mc.thePlayer.inventory.getItemStack());
                        this.mc.thePlayer.inventory.setItemStack((ItemStack)null);
                    }
                    else
                    {
                        this.mc.thePlayer.inventoryContainer.slotClick(slotIn == null ? slotId : ((GuiContainerCreative.CreativeSlot)slotIn).field_148332_b.slotNumber, clickedButton, clickType, this.mc.thePlayer);
                        this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
                    }
                }
                else if (clickType != 5 && slotIn.inventory == field_147060_v)
                {
                    var11 = this.mc.thePlayer.inventory;
                    var7 = var11.getItemStack();
                    ItemStack var8 = slotIn.getStack();
                    ItemStack var9;

                    if (clickType == 2)
                    {
                        if (var8 != null && clickedButton >= 0 && clickedButton < 9)
                        {
                            var9 = var8.copy();
                            var9.stackSize = var9.getMaxStackSize();
                            this.mc.thePlayer.inventory.setInventorySlotContents(clickedButton, var9);
                            this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
                        }

                        return;
                    }

                    if (clickType == 3)
                    {
                        if (var11.getItemStack() == null && slotIn.getHasStack())
                        {
                            var9 = slotIn.getStack().copy();
                            var9.stackSize = var9.getMaxStackSize();
                            var11.setItemStack(var9);
                        }

                        return;
                    }

                    if (clickType == 4)
                    {
                        if (var8 != null)
                        {
                            var9 = var8.copy();
                            var9.stackSize = clickedButton == 0 ? 1 : var9.getMaxStackSize();
                            this.mc.thePlayer.dropPlayerItemWithRandomChoice(var9, true);
                            this.mc.playerController.sendPacketDropItem(var9);
                        }

                        return;
                    }

                    if (var7 != null && var8 != null && var7.isItemEqual(var8))
                    {
                        if (clickedButton == 0)
                        {
                            if (var5)
                            {
                                var7.stackSize = var7.getMaxStackSize();
                            }
                            else if (var7.stackSize < var7.getMaxStackSize())
                            {
                                ++var7.stackSize;
                            }
                        }
                        else if (var7.stackSize <= 1)
                        {
                            var11.setItemStack((ItemStack)null);
                        }
                        else
                        {
                            --var7.stackSize;
                        }
                    }
                    else if (var8 != null && var7 == null)
                    {
                        var11.setItemStack(ItemStack.copyItemStack(var8));
                        var7 = var11.getItemStack();

                        if (var5)
                        {
                            var7.stackSize = var7.getMaxStackSize();
                        }
                    }
                    else
                    {
                        var11.setItemStack((ItemStack)null);
                    }
                }
                else
                {
                    this.field_147002_h.slotClick(slotIn == null ? slotId : slotIn.slotNumber, clickedButton, clickType, this.mc.thePlayer);

                    if (Container.func_94532_c(clickedButton) == 2)
                    {
                        for (var10 = 0; var10 < 9; ++var10)
                        {
                            this.mc.playerController.sendSlotPacket(this.field_147002_h.getSlot(45 + var10).getStack(), 36 + var10);
                        }
                    }
                    else if (slotIn != null)
                    {
                        var6 = this.field_147002_h.getSlot(slotIn.slotNumber).getStack();
                        this.mc.playerController.sendSlotPacket(var6, slotIn.slotNumber - this.field_147002_h.inventorySlots.size() + 9 + 36);
                    }
                }
            }
        }
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        if (this.mc.playerController.isInCreativeMode())
        {
            super.initGui();
            this.buttonList.clear();
            Keyboard.enableRepeatEvents(true);
            this.searchField = new GuiTextField(this.fontRendererObj, this.field_147003_i + 82, this.field_147009_r + 6, 89, this.fontRendererObj.FONT_HEIGHT);
            this.searchField.func_146203_f(15);
            this.searchField.func_146185_a(false);
            this.searchField.func_146189_e(false);
            this.searchField.func_146193_g(16777215);
            int var1 = selectedTabIndex;
            selectedTabIndex = -1;
            this.setCurrentCreativeTab(CreativeTabs.creativeTabArray.get(var1));
            this.field_147059_E = new CreativeCrafting(this.mc);
            this.mc.thePlayer.inventoryContainer.addCraftingToCrafters(this.field_147059_E);
            int tabCount = CreativeTabs.creativeTabArray.size();
            if (tabCount > 12)
            {
        		this.buttonList.add(new GuiButton(101, field_147003_i, field_147009_r - 50, 20, 20, "�")); 
        		this.buttonList.add(new GuiButton(102, field_147003_i + field_146999_f - 20, field_147009_r - 50, 20, 20, "�"));                
                maxPages = ((tabCount - 12) / 10) + 1;
                
            }            
        }
        else
        {
            this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
        }
    }

    /**
     * "Called when the screen is unloaded. Used to disable keyboard repeat events."
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();

        if (this.mc.thePlayer != null && this.mc.thePlayer.inventory != null)
        {
            this.mc.thePlayer.inventoryContainer.removeCraftingFromCrafters(this.field_147059_E);
        }

        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if (selectedTabIndex != CreativeTabs.tabAllSearch.getTabIndex())
        {
            if (GameSettings.isKeyDown(this.mc.gameSettings.keyBindChat))
            {
                this.setCurrentCreativeTab(CreativeTabs.tabAllSearch);
            }
            else
            {
                super.keyTyped(p_73869_1_, p_73869_2_);
            }
        }
        else
        {
            if (this.field_147057_D)
            {
                this.field_147057_D = false;
                this.searchField.setText("");
            }

            if (!this.func_146983_a(p_73869_2_))
            {
                if (this.searchField.textboxKeyTyped(p_73869_1_, p_73869_2_))
                {
                    this.func_147053_i();
                }
                else
                {
                    super.keyTyped(p_73869_1_, p_73869_2_);
                }
            }
        }
    }

    private void func_147053_i()
    {
        GuiContainerCreative.ContainerCreative var1 = (GuiContainerCreative.ContainerCreative)this.field_147002_h;
        var1.field_148330_a.clear();
        Iterator var2 = Item.itemRegistry.iterator();

        while (var2.hasNext())
        {
            Item var3 = (Item)var2.next();

            if (var3 != null && var3.getCreativeTab() != null)
            {
                var3.getSubItems(var3, (CreativeTabs)null, var1.field_148330_a);
            }
        }

        Enchantment[] var8 = Enchantment.enchantmentsList;
        int var9 = var8.length;

        for (int var4 = 0; var4 < var9; ++var4)
        {
            Enchantment var5 = var8[var4];

            if (var5 != null && var5.type != null)
            {
                Items.enchanted_book.func_92113_a(var5, var1.field_148330_a);
            }
        }

        var2 = var1.field_148330_a.iterator();
        String var10 = this.searchField.getText().toLowerCase();

        while (var2.hasNext())
        {
            ItemStack var11 = (ItemStack)var2.next();
            boolean var12 = false;
            Iterator var6 = var11.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips).iterator();

            while (true)
            {
                if (var6.hasNext())
                {
                    String var7 = (String)var6.next();

                    if (!var7.toLowerCase().contains(var10))
                    {
                        continue;
                    }

                    var12 = true;
                }

                if (!var12)
                {
                    var2.remove();
                }

                break;
            }
        }

        this.currentScroll = 0.0F;
        var1.func_148329_a(0.0F);
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_)
    {
        CreativeTabs var3 = CreativeTabs.creativeTabArray.get(selectedTabIndex);

        if (var3.drawInForegroundOfTab())
        {
            GL11.glDisable(GL11.GL_BLEND);
            this.fontRendererObj.drawString(I18n.format(var3.getTranslatedTabLabel(), new Object[0]), 8, 6, 4210752);
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        if (p_73864_3_ == 0)
        {
            int var4 = p_73864_1_ - this.field_147003_i;
            int var5 = p_73864_2_ - this.field_147009_r;
            CreativeTabs[] var6 = CreativeTabs.creativeTabArray.toArray(new CreativeTabs[CreativeTabs.creativeTabArray.size()]);
            int var7 = var6.length;

            for (int var8 = 0; var8 < var7; ++var8)
            {
                CreativeTabs var9 = var6[var8];

                if (this.func_147049_a(var9, var4, var5))
                {
                    return;
                }
            }
        }

        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }

    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_)
    {
        if (p_146286_3_ == 0)
        {
            int var4 = p_146286_1_ - this.field_147003_i;
            int var5 = p_146286_2_ - this.field_147009_r;
            CreativeTabs[] var6 = CreativeTabs.creativeTabArray.toArray(new CreativeTabs[CreativeTabs.creativeTabArray.size()]);
            int var7 = var6.length;

            for (int var8 = 0; var8 < var7; ++var8)
            {
                CreativeTabs var9 = var6[var8];

                if (this.func_147049_a(var9, var4, var5))
                {
                    this.setCurrentCreativeTab(var9);
                    return;
                }
            }
        }

        super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
    }
    
    private boolean func_147055_p()
    {
        return selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && CreativeTabs.creativeTabArray.get(selectedTabIndex).shouldHidePlayerInventory() && ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148328_e();
    }

    private void setCurrentCreativeTab(CreativeTabs p_147050_1_)
    {
    	if (p_147050_1_ == null) return;
        int var2 = selectedTabIndex;
        selectedTabIndex = p_147050_1_.getTabIndex();
        GuiContainerCreative.ContainerCreative var3 = (GuiContainerCreative.ContainerCreative)this.field_147002_h;
        this.field_147008_s.clear();
        var3.field_148330_a.clear();
        p_147050_1_.displayAllReleventItems(var3.field_148330_a);

        if (p_147050_1_ == CreativeTabs.tabInventory)
        {
            Container var4 = this.mc.thePlayer.inventoryContainer;

            if (this.field_147063_B == null)
            {
                this.field_147063_B = var3.inventorySlots;
            }

            var3.inventorySlots = new ArrayList();

            for (int var5 = 0; var5 < var4.inventorySlots.size(); ++var5)
            {
                GuiContainerCreative.CreativeSlot var6 = new GuiContainerCreative.CreativeSlot((Slot)var4.inventorySlots.get(var5), var5);
                var3.inventorySlots.add(var6);
                int var7;
                int var8;
                int var9;

                if (var5 >= 5 && var5 < 9)
                {
                    var7 = var5 - 5;
                    var8 = var7 / 2;
                    var9 = var7 % 2;
                    var6.xDisplayPosition = 9 + var8 * 54;
                    var6.yDisplayPosition = 6 + var9 * 27;
                }
                else if (var5 >= 0 && var5 < 5)
                {
                    var6.yDisplayPosition = -2000;
                    var6.xDisplayPosition = -2000;
                }
                else if (var5 < var4.inventorySlots.size())
                {
                    var7 = var5 - 9;
                    var8 = var7 % 9;
                    var9 = var7 / 9;
                    var6.xDisplayPosition = 9 + var8 * 18;

                    if (var5 >= 36)
                    {
                        var6.yDisplayPosition = 112;
                    }
                    else
                    {
                        var6.yDisplayPosition = 54 + var9 * 18;
                    }
                }
            }

            this.field_147064_C = new Slot(field_147060_v, 0, 173, 112);
            var3.inventorySlots.add(this.field_147064_C);
        }
        else if (var2 == CreativeTabs.tabInventory.getTabIndex())
        {
            var3.inventorySlots = this.field_147063_B;
            this.field_147063_B = null;
        }

        if (this.searchField != null)
        {
            if (p_147050_1_ == CreativeTabs.tabAllSearch)
            {
                this.searchField.func_146189_e(true);
                this.searchField.func_146205_d(false);
                this.searchField.setFocused(true);
                this.searchField.setText("");
                this.func_147053_i();
            }
            else
            {
                this.searchField.func_146189_e(false);
                this.searchField.func_146205_d(true);
                this.searchField.setFocused(false);
            }
        }

        this.currentScroll = 0.0F;
        var3.func_148329_a(0.0F);
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput()
    {
        super.handleMouseInput();
        int var1 = Mouse.getEventDWheel();

        if (var1 != 0 && this.func_147055_p())
        {
            int var2 = ((GuiContainerCreative.ContainerCreative)this.field_147002_h).field_148330_a.size() / 9 - 5 + 1;

            if (var1 > 0)
            {
                var1 = 1;
            }

            if (var1 < 0)
            {
                var1 = -1;
            }

            this.currentScroll = (float)((double)this.currentScroll - (double)var1 / (double)var2);

            if (this.currentScroll < 0.0F)
            {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F)
            {
                this.currentScroll = 1.0F;
            }

            ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148329_a(this.currentScroll);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        boolean var4 = Mouse.isButtonDown(0);
        int var5 = this.field_147003_i;
        int var6 = this.field_147009_r;
        int var7 = var5 + 175;
        int var8 = var6 + 18;
        int var9 = var7 + 14;
        int var10 = var8 + 112;

        if (!this.wasClicking && var4 && p_73863_1_ >= var7 && p_73863_2_ >= var8 && p_73863_1_ < var9 && p_73863_2_ < var10)
        {
            this.isScrolling = this.func_147055_p();
        }

        if (!var4)
        {
            this.isScrolling = false;
        }

        this.wasClicking = var4;

        if (this.isScrolling)
        {
            this.currentScroll = ((float)(p_73863_2_ - var8) - 7.5F) / ((float)(var10 - var8) - 15.0F);

            if (this.currentScroll < 0.0F)
            {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F)
            {
                this.currentScroll = 1.0F;
            }

            ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148329_a(this.currentScroll);
        }

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        CreativeTabs[] var11 = CreativeTabs.creativeTabArray.toArray(new CreativeTabs[CreativeTabs.creativeTabArray.size()]);
        int var12 = var11.length;

        int start = tabPage * 10;
        int i2 = Math.min(var11.length, ((tabPage + 1) * 10) + 2);
        if (tabPage != 0) start += 2;
        
        for (int var13 = 0; var13 < var12; ++var13)
        {
            CreativeTabs var14 = var11[var13];
            if (var14.getTabPage() == tabPage){
            	if (this.renderCreativeInventoryHoveringText(var14, p_73863_1_, p_73863_2_))
            	{
                	break;
            	}
            }
        }

        if (this.field_147064_C != null && selectedTabIndex == CreativeTabs.tabInventory.getTabIndex() && this.func_146978_c(this.field_147064_C.xDisplayPosition, this.field_147064_C.yDisplayPosition, 16, 16, p_73863_1_, p_73863_2_))
        {
            this.func_146279_a(I18n.format("inventory.binSlot", new Object[0]), p_73863_1_, p_73863_2_);
        }
        
        if (maxPages != 0)
        {
            String page = String.format("�ePage�7: �6%d �7/ �6%d", tabPage + 1, maxPages + 1);
            int width = fontRendererObj.getStringWidth(page);
            GL11.glDisable(GL11.GL_LIGHTING);
            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
            fontRendererObj.drawString(page, this.field_147003_i + (field_146999_f / 2) - (width / 2), this.field_147009_r - 44, -1);
            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
        }
        
        this.drawStringScale("�7D�velopp� par Maxlego08", 5, 5, 0.8F);
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
    }

    protected void func_146285_a(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_)
    {
        if (selectedTabIndex == CreativeTabs.tabAllSearch.getTabIndex())
        {
            List var4 = p_146285_1_.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
            CreativeTabs var5 = p_146285_1_.getItem().getCreativeTab();

            if (var5 == null && p_146285_1_.getItem() == Items.enchanted_book)
            {
                Map var6 = EnchantmentHelper.getEnchantments(p_146285_1_);

                if (var6.size() == 1)
                {
                    Enchantment var7 = Enchantment.enchantmentsList[((Integer)var6.keySet().iterator().next()).intValue()];
                    CreativeTabs[] var8 = CreativeTabs.creativeTabArray.toArray(new CreativeTabs[CreativeTabs.creativeTabArray.size()]);
                    int var9 = var8.length;

                    for (int var10 = 0; var10 < var9; ++var10)
                    {
                        CreativeTabs var11 = var8[var10];

                        if (var11.func_111226_a(var7.type))
                        {
                            var5 = var11;
                            break;
                        }
                    }
                }
            }

            if (var5 != null)
            {
                var4.add(1, "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.format(var5.getTranslatedTabLabel(), new Object[0]));
            }

            for (int var12 = 0; var12 < var4.size(); ++var12)
            {
                if (var12 == 0)
                {
                    var4.set(var12, p_146285_1_.getRarity().rarityColor + (String)var4.get(var12));
                }
                else
                {
                    var4.set(var12, EnumChatFormatting.GRAY + (String)var4.get(var12));
                }
            }

            this.func_146283_a(var4, p_146285_2_, p_146285_3_);
        }
        else
        {
            super.func_146285_a(p_146285_1_, p_146285_2_, p_146285_3_);
        }
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHelper.enableGUIStandardItemLighting();
        CreativeTabs var4 = CreativeTabs.creativeTabArray.get(selectedTabIndex);
        CreativeTabs[] var5 = CreativeTabs.creativeTabArray.toArray(new CreativeTabs[CreativeTabs.creativeTabArray.size()]);
        int start = tabPage * 10;
        int var6 = var5.length;
        int var7;

        var6 = Math.min(var5.length, ((tabPage + 1) * 10 + 2));
        if (tabPage != 0) start += 2;
        
        for (var7 = start; var7 < var6; ++var7)
        {
            CreativeTabs var8 = var5[var7];
            this.mc.getTextureManager().bindTexture(creativeInventoryTabs);

            if (var8.getTabIndex() != selectedTabIndex)
            {
                this.func_147051_a(var8);
            }
        }

        if (tabPage != 0)
        {
            if (var4 != CreativeTabs.tabAllSearch)
            {
                this.mc.getTextureManager().bindTexture(creativeInventoryTabs);
                func_147051_a(CreativeTabs.tabAllSearch);
            }
            if (var4 != CreativeTabs.tabInventory)
            {
                this.mc.getTextureManager().bindTexture(creativeInventoryTabs);
                func_147051_a(CreativeTabs.tabInventory);
            }
        }
        
        this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + var4.getBackgroundImageName()));
        this.drawTexturedModalRect(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        this.searchField.drawTextBox();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int var9 = this.field_147003_i + 175;
        var6 = this.field_147009_r + 18;
        var7 = var6 + 112;
        this.mc.getTextureManager().bindTexture(creativeInventoryTabs);

        if (var4.shouldHidePlayerInventory())
        {
            this.drawTexturedModalRect(var9, var6 + (int)((float)(var7 - var6 - 17) * this.currentScroll), 232 + (this.func_147055_p() ? 0 : 12), 0, 12, 15);
        }

        if (var4 == null || var4.getTabPage() != tabPage)
        {
            if (var4 != CreativeTabs.tabAllSearch && var4 != CreativeTabs.tabInventory)
            {
                return;
            }
        }
        
        this.func_147051_a(var4);

        if (var4 == CreativeTabs.tabInventory)
        {
            GuiInventory.func_147046_a(this.field_147003_i + 43, this.field_147009_r + 45, 20, (float)(this.field_147003_i + 43 - p_146976_2_), (float)(this.field_147009_r + 45 - 30 - p_146976_3_), this.mc.thePlayer);
        }
    }

    protected boolean func_147049_a(CreativeTabs p_147049_1_, int p_147049_2_, int p_147049_3_)
    {
        if (p_147049_1_.getTabPage() != tabPage)
        {
            if (p_147049_1_ != CreativeTabs.tabAllSearch &&
                p_147049_1_ != CreativeTabs.tabInventory)
            {
                return false;
            }
        }
    	
        int var4 = p_147049_1_.getTabColumn();
        int var5 = 28 * var4;
        byte var6 = 0;

        if (var4 == 5)
        {
            var5 = this.field_146999_f - 28 + 2;
        }
        else if (var4 > 0)
        {
            var5 += var4;
        }

        int var7;

        if (p_147049_1_.isTabInFirstRow())
        {
            var7 = var6 - 32;
        }
        else
        {
            var7 = var6 + this.field_147000_g;
        }

        return p_147049_2_ >= var5 && p_147049_2_ <= var5 + 28 && p_147049_3_ >= var7 && p_147049_3_ <= var7 + 32;
    }

    protected boolean renderCreativeInventoryHoveringText(CreativeTabs p_147052_1_, int p_147052_2_, int p_147052_3_)
    {
        int var4 = p_147052_1_.getTabColumn();
        int var5 = 28 * var4;
        byte var6 = 0;

        if (var4 == 5)
        {
            var5 = this.field_146999_f - 28 + 2;
        }
        else if (var4 > 0)
        {
            var5 += var4;
        }

        int var7;

        if (p_147052_1_.isTabInFirstRow())
        {
            var7 = var6 - 32;
        }
        else
        {
            var7 = var6 + this.field_147000_g;
        }

        if (this.func_146978_c(var5 + 3, var7 + 3, 23, 27, p_147052_2_, p_147052_3_))
        {
            this.func_146279_a(I18n.format(p_147052_1_.getTranslatedTabLabel(), new Object[0]), p_147052_2_, p_147052_3_);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void func_147051_a(CreativeTabs p_147051_1_)
    {
        boolean var2 = p_147051_1_.getTabIndex() == selectedTabIndex;
        boolean var3 = p_147051_1_.isTabInFirstRow();
        int var4 = p_147051_1_.getTabColumn();
        int var5 = var4 * 28;
        int var6 = 0;
        int var7 = this.field_147003_i + 28 * var4;
        int var8 = this.field_147009_r;
        byte var9 = 32;

        if (var2)
        {
            var6 += 32;
        }

        if (var4 == 5)
        {
            var7 = this.field_147003_i + this.field_146999_f - 28;
        }
        else if (var4 > 0)
        {
            var7 += var4;
        }

        if (var3)
        {
            var8 -= 28;
        }
        else
        {
            var6 += 64;
            var8 += this.field_147000_g - 4;
        }

        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawTexturedModalRect(var7, var8, var5, var6, 28, var9);
        this.zLevel = 100.0F;
        itemRender.zLevel = 100.0F;
        var7 += 6;
        var8 += 8 + (var3 ? 1 : -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        ItemStack var10 = p_147051_1_.getIconItemStack();
        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), var10, var7, var8);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), var10, var7, var8);
        GL11.glDisable(GL11.GL_LIGHTING);
        itemRender.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }

    protected void actionPerformed(GuiButton p_146284_1_)
    {
        if (p_146284_1_.id == 0)
        {
            this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.func_146107_m()));
        }

        if (p_146284_1_.id == 1)
        {
            this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.func_146107_m()));
        }
        
        if (p_146284_1_.id == 101)
        {
            tabPage = Math.max(tabPage - 1, 0);
        }
        else if (p_146284_1_.id == 102)
        {
            tabPage = Math.min(tabPage + 1, maxPages);
        }
    }

    public int func_147056_g()
    {
        return selectedTabIndex;
    }

    static class ContainerCreative extends Container
    {
        public List field_148330_a = new ArrayList();
        private static final String __OBFID = "CL_00000753";

        public ContainerCreative(EntityPlayer p_i1086_1_)
        {
            InventoryPlayer var2 = p_i1086_1_.inventory;
            int var3;

            for (var3 = 0; var3 < 5; ++var3)
            {
                for (int var4 = 0; var4 < 9; ++var4)
                {
                    this.addSlotToContainer(new Slot(GuiContainerCreative.field_147060_v, var3 * 9 + var4, 9 + var4 * 18, 18 + var3 * 18));
                }
            }

            for (var3 = 0; var3 < 9; ++var3)
            {
                this.addSlotToContainer(new Slot(var2, var3, 9 + var3 * 18, 112));
            }

            this.func_148329_a(0.0F);
        }

        public boolean canInteractWith(EntityPlayer p_75145_1_)
        {
            return true;
        }

        public void func_148329_a(float p_148329_1_)
        {
            int var2 = this.field_148330_a.size() / 9 - 5 + 1;
            int var3 = (int)((double)(p_148329_1_ * (float)var2) + 0.5D);

            if (var3 < 0)
            {
                var3 = 0;
            }

            for (int var4 = 0; var4 < 5; ++var4)
            {
                for (int var5 = 0; var5 < 9; ++var5)
                {
                    int var6 = var5 + (var4 + var3) * 9;

                    if (var6 >= 0 && var6 < this.field_148330_a.size())
                    {
                        GuiContainerCreative.field_147060_v.setInventorySlotContents(var5 + var4 * 9, (ItemStack)this.field_148330_a.get(var6));
                    }
                    else
                    {
                        GuiContainerCreative.field_147060_v.setInventorySlotContents(var5 + var4 * 9, (ItemStack)null);
                    }
                }
            }
        }

        public boolean func_148328_e()
        {
            return this.field_148330_a.size() > 45;
        }

        protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {}

        public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
        {
            if (p_82846_2_ >= this.inventorySlots.size() - 9 && p_82846_2_ < this.inventorySlots.size())
            {
                Slot var3 = (Slot)this.inventorySlots.get(p_82846_2_);

                if (var3 != null && var3.getHasStack())
                {
                    var3.putStack((ItemStack)null);
                }
            }

            return null;
        }

        public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_)
        {
            return p_94530_2_.yDisplayPosition > 90;
        }

        public boolean canDragIntoSlot(Slot p_94531_1_)
        {
            return p_94531_1_.inventory instanceof InventoryPlayer || p_94531_1_.yDisplayPosition > 90 && p_94531_1_.xDisplayPosition <= 162;
        }
    }

    class CreativeSlot extends Slot
    {
        private final Slot field_148332_b;
        private static final String __OBFID = "CL_00000754";

        public CreativeSlot(Slot p_i46313_2_, int p_i46313_3_)
        {
            super(p_i46313_2_.inventory, p_i46313_3_, 0, 0);
            this.field_148332_b = p_i46313_2_;
        }

        public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
        {
            this.field_148332_b.onPickupFromSlot(p_82870_1_, p_82870_2_);
        }

        public boolean isItemValid(ItemStack p_75214_1_)
        {
            return this.field_148332_b.isItemValid(p_75214_1_);
        }

        public ItemStack getStack()
        {
            return this.field_148332_b.getStack();
        }

        public boolean getHasStack()
        {
            return this.field_148332_b.getHasStack();
        }

        public void putStack(ItemStack p_75215_1_)
        {
            this.field_148332_b.putStack(p_75215_1_);
        }

        public void onSlotChanged()
        {
            this.field_148332_b.onSlotChanged();
        }

        public int getSlotStackLimit()
        {
            return this.field_148332_b.getSlotStackLimit();
        }

        public IIcon getBackgroundIconIndex()
        {
            return this.field_148332_b.getBackgroundIconIndex();
        }

        public ItemStack decrStackSize(int p_75209_1_)
        {
            return this.field_148332_b.decrStackSize(p_75209_1_);
        }

        public boolean isSlotInInventory(IInventory p_75217_1_, int p_75217_2_)
        {
            return this.field_148332_b.isSlotInInventory(p_75217_1_, p_75217_2_);
        }
    }
}