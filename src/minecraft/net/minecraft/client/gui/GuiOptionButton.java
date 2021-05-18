package net.minecraft.client.gui;

import org.lwjgl.opengl.GL11;

import fr.maxlego08.kronos.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class GuiOptionButton extends GuiButton
{
    private final GameSettings.Options field_146137_o;
    private static final String __OBFID = "CL_00000676";

	 private ResourceLocation custumboutton = new ResourceLocation("textures/gui/kronos/boutton_1.png");
	 private ResourceLocation custumbouttonHover = new ResourceLocation("textures/gui/kronos/boutton_1_hover.png");
    
    public GuiOptionButton(int p_i45011_1_, int p_i45011_2_, int p_i45011_3_, String p_i45011_4_)
    {
        this(p_i45011_1_, p_i45011_2_, p_i45011_3_, (GameSettings.Options)null, p_i45011_4_);  
    }

    public GuiOptionButton(int p_i45012_1_, int p_i45012_2_, int p_i45012_3_, int p_i45012_4_, int p_i45012_5_, String p_i45012_6_)
    {
        super(p_i45012_1_, p_i45012_2_, p_i45012_3_, p_i45012_4_, p_i45012_5_, p_i45012_6_);
        this.field_146137_o = null;      
    }

    public GuiOptionButton(int p_i45013_1_, int p_i45013_2_, int p_i45013_3_, GameSettings.Options p_i45013_4_, String p_i45013_5_)
    {
        super(p_i45013_1_, p_i45013_2_, p_i45013_3_, 150, 20, p_i45013_5_);
        this.field_146137_o = p_i45013_4_;     
    }

    public GameSettings.Options func_146136_c()
    {
        return this.field_146137_o;
    }
    
	public void drawButton(Minecraft p_146112_1_, int mouseX, int mouseY)
	{
		if (this.field_146125_m)
	  	{
			
			int xPosition = this.field_146128_h;
			int yPosition = this.field_146129_i;
			int width = this.field_146120_f;
			int height = this.field_146121_g;
			  
			
	        ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	        int realW = var5.getScaledWidth();
	        int realH = var5.getScaledHeight();
	        
	        boolean hovered = ((mouseX >= xPosition) && (mouseY >= yPosition) && (mouseX < xPosition + width) && (mouseY < yPosition + height));
			
	        FontRenderer zz = Minecraft.getMinecraft().fontRenderer;
	        if (!hovered){
	        	drawBack(xPosition, yPosition, width, height);
	        	drawCenteredString(zz, displayString, xPosition + width/2, yPosition + height /3, Colors.getInstance().getMainButton());
	        }else{
	        	drawBackH(xPosition, yPosition, width, height);	        	
	        	drawCenteredString(zz, "» "+displayString + " «", xPosition + width/2, yPosition + height /3, Colors.getInstance().getMainButton());
	        }
	        
	  	}
    }
	
    public void drawBack(int x, int y, int w, int h){
    	GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().getTextureManager().bindTexture(custumboutton);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTextureWithOptionalSize(x, y, 0, 0, w, h, w, h);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public void drawBackH(int x, int y, int w, int h){
    	GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	Minecraft.getMinecraft().getTextureManager().bindTexture(custumbouttonHover);
    	 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.drawTextureWithOptionalSize(x, y, 0, 0, w, h, w, h);
    	GL11.glDisable(GL11.GL_BLEND);
    }
}
