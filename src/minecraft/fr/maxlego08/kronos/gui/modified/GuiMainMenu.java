package fr.maxlego08.kronos.gui.modified;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import fr.maxlego08.kronos.buttons.GuiButtonMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback
{
    private static final Logger logger = LogManager.getLogger();

    /** The RNG used by the Main Menu Screen. */
    private static final Random rand = new Random();

    /** Counts the number of screen updates. */
    private float updateCounter;

    /** The splash message. */
    private String splashText;
    private GuiButton buttonResetDemo;

    /** Timer used to rotate the panorama, increases every tick. */
    private int panoramaTimer;

    private DynamicTexture viewportTexture;
    private final Object field_104025_t = new Object();
    private String field_92025_p;
    private String field_146972_A;
    private String field_104024_v;
    private static final ResourceLocation splashTexts = new ResourceLocation("texts/splashes.txt");
    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");

    /** An array of all the paths to the panorama pictures. */
    private int field_92024_r;
    private int field_92023_s;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;
    private ResourceLocation field_110351_G;
    private static final String __OBFID = "CL_00001154";

    private static final ResourceLocation background = new ResourceLocation("textures/gui/kronos/wallpaper.png");
    
    private GuiButton bouton;
    
    public GuiMainMenu()
    {
        this.splashText = "missingno";
        BufferedReader var1 = null;

        try
        {
            ArrayList var2 = new ArrayList();
            var1 = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(), Charsets.UTF_8));
            String var3;

            while ((var3 = var1.readLine()) != null)
            {
                var3 = var3.trim();

                if (!var3.isEmpty())
                {
                    var2.add(var3);
                }
            }

            if (!var2.isEmpty())
            {
                do
                {
                    this.splashText = (String)var2.get(rand.nextInt(var2.size()));
                }
                while (this.splashText.hashCode() == 125780783);
            }
        }
        catch (IOException var12)
        {
            ;
        }
        finally
        {
            if (var1 != null)
            {
                try
                {
                    var1.close();
                }
                catch (IOException var11)
                {
                    ;
                }
            }
        }

        this.updateCounter = rand.nextFloat();
        this.field_92025_p = "";

        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.func_153193_b())
        {
            this.field_92025_p = I18n.format("title.oldgl1", new Object[0]);
            this.field_146972_A = I18n.format("title.oldgl2", new Object[0]);
            this.field_104024_v = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    public void initGui()
    {
        this.viewportTexture = new DynamicTexture(256, 256);

        boolean var2 = true;
        int var3 = this.height / 4 + 28;

       
       this.buttonList.add(new GuiButtonMain(35, this.width / 2 - 90, var3 - 75 , 180, 30, "Local", 3));
       this.buttonList.add(new GuiButtonMain(19, this.width / 2 - 90, var3 , 180, 30, "Rejoindre Kronos", 3));
       this.buttonList.add(new GuiButtonMain(1, this.width / 2 - 90, var3 + 35, 180, 30, "Solo", 1));
       this.buttonList.add(new GuiButtonMain(0, this.width / 2 - 90, var3 + 70, 180, 30, "Options", 2));
       this.buttonList.add(new GuiButtonMain(4, this.width / 2 - 90, var3 + 105, 180, 30, "Quitter", 3));
        
    }

    protected void actionPerformed(GuiButton p_146284_1_)
    {
        if (p_146284_1_.id == 0)
        {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        else if (p_146284_1_.id == 1)
        {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        else  if (p_146284_1_.id == 2)
        {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        else if (p_146284_1_.id == 4)
        {
            this.mc.shutdown();
        }
        
//        else  if (p_146284_1_.id == 19) {
//                this.mc.displayGuiScreen(new GuiLoadingScreen());
//        }
        
        else if (p_146284_1_.id == 19) {
        	this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, "193.70.103.115", 25837));
        }              
        else if (p_146284_1_.id == 35) {
        	this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, "127.0.0.1", 25565));
        }              
    }
    
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
    {
        drawDefaultBackground(mouseX, mouseY);
        
		super.drawScreen(mouseX, mouseY, partialTicks);
    }
	
	


}
