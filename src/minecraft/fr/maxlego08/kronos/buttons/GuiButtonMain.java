package fr.maxlego08.kronos.buttons;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import fr.maxlego08.kronos.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;

public class GuiButtonMain extends GuiButton
{
	
	 private ResourceLocation custumboutton = new ResourceLocation("textures/gui/kronos/boutton_1.png");
	 private ResourceLocation custumbouttonHover = new ResourceLocation("textures/gui/kronos/boutton_1_hover.png");
	
	private static final String __OBFID = "CL_00000672";
	public String texte;
	public int coord1;
	public int coord2;
	public int longeur;
	public int longeur2;
	public String barre;
	public int idButton;
	
	
	public GuiButtonMain(int id, int cc, int bb, int largeur, int largeur2, String texte)
	{
		super(id, cc, bb, largeur, largeur2, texte);
		this.texte = texte;
		this.coord1 = cc;
		this.coord2 = bb;
		this.longeur = largeur;
		this.longeur2 = largeur2;
	}
	
	public GuiButtonMain(int id, int cc, int bb, int largeur, int largeur2, String texte, int idButton)
	{
		  super(id, cc, bb, largeur, largeur2, texte);
		  this.texte = texte;
		  this.coord1 = cc;
		  this.coord2 = bb;
		  this.longeur = largeur;
		  this.longeur2 = largeur2;
		  this.idButton = idButton;
		  
		  
		custumboutton = new ResourceLocation("textures/gui/kronos/boutton_"+idButton+".png");
		custumbouttonHover = new ResourceLocation("textures/gui/kronos/boutton_"+idButton+"_hover.png");
		  
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
	        	drawCenteredString(zz, texte, xPosition + width/2, yPosition + height /3, Colors.getInstance().getMainButton());
	        }else{
	        	drawBackH(xPosition, yPosition, width, height);	        	
	        	drawCenteredString(zz, "» "+texte + " «", xPosition + width/2, yPosition + height /3, Colors.getInstance().getMainButton());
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
