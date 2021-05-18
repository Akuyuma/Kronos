package fr.maxlego08.kronos.gui.modified;

import org.lwjgl.opengl.GL11;

import fr.maxlego08.kronos.buttons.GuiButtonMain;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;

public class GuiIngameMenu extends GuiScreen
{
    private int field_146445_a;
    private int field_146444_f;
    private static final String __OBFID = "CL_00000703";

    private Timer timer = new Timer(250F);
    
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	
    	initCustomButton();

    }

    protected void actionPerformed(GuiButton p_146284_1_)
    {
        switch (p_146284_1_.id)
        {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;

            case 1:
                p_146284_1_.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                this.mc.displayGuiScreen(new GuiMainMenu());

            case 2:
            case 3:
            default:
                break;

            case 4:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();

        this.timer.updateTimer();
        
        for(int j = 0; j < this.timer.elapsedTicks; j++){
        	if (animation < 140)  animation+=2;
        }
        initCustomButton();
    }

    private int animation = 0;
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float p_73863_3_)
    {
    	this.drawGradientRect(0, 0, animation, this.height, -1072689136, -804253680);
        
    	this.drawLogo();
    	
        super.drawScreen(mouseX, mouseY, p_73863_3_);       
   
    }
    
    private void drawLogo(){
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator var2 = Tessellator.instance;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/kronos/icon.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTextureWithOptionalSize(animation - 100, 0, 0, 0, 100, 100, 100, 100);
    }
    
    private void initCustomButton(){
    	this.buttonList.clear();
    	this.buttonList.add(new GuiButtonMain(1, animation - 120, this.height - 60 , 100, 30, "Deconnexion"));
    	this.buttonList.add(new GuiButtonMain(0, animation - 120, this.height - 100 , 100, 30, "Options"));
    	this.buttonList.add(new GuiButtonMain(4, animation - 120, this.height - 140 , 100, 30, "Retour en jeu"));
    }
    
}
