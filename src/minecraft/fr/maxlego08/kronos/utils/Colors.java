package fr.maxlego08.kronos.utils;

import java.awt.Color;

public class Colors {

	private static Colors instance;
	
	public Colors() {
		instance = this;
	}
	
	private Integer[] textTransparent = {100,80,70,60,50,40,30,20,10,0,0,0};
	
	public static Colors getInstance() {
		return instance;
	}
	
	public Color getRainbow(int time){
		switch (time) {
		case 0:
			return new Color(255, 255, 255);
		case 1:
			return new Color(245, 255, 255);
		case 2:
			return new Color(235, 255, 255);
		case 3:
			return new Color(225, 255, 255);
		case 4:
			return new Color(215, 255, 255);
		case 5:
			return new Color(205, 255, 255);
		case 6:
			return new Color(195, 255, 255);
		case 7:
			return new Color(185, 255, 255);
		case 8:
			return new Color(175, 255, 255);
		case 9:
			return new Color(165, 255, 255);
		case 10:
			return new Color(155, 255, 255);
		case 11:
			return new Color(145, 255, 255);
		case 12:
			return new Color(135, 255, 255);
		case 13:
			return new Color(125, 255, 255);
		case 14:
			return new Color(115, 255, 255);
		case 15:
			return new Color(105, 255, 255);
		case 16:
			return new Color(95, 255, 255);
		case 17:
			return new Color(85, 255, 255);
		case 18:
			return new Color(75, 255, 255);
		case 19:
			return new Color(65, 255, 255);
		case 20:
			return new Color(55, 255, 255);
		case 21:
			return new Color(45, 255, 255);
		case 22:
			return new Color(35, 255, 255);
		case 23:
			return new Color(25, 255, 255);
		case 24:
			return new Color(15, 255, 255);
		case 25:
			return new Color(5, 255, 255);
		case 26:
			return new Color(0, 255, 255);
		case 27:
			return new Color(0, 245, 255);
		case 28:
			return new Color(0, 235, 255);
		case 29:
			return new Color(0, 225, 255);
		case 30:
			return new Color(0, 215, 255);
		case 31:
			return new Color(0, 205, 255);
		case 32:
			return new Color(0, 195, 255);
		case 33:
			return new Color(0, 185, 255);
		case 34:
			return new Color(0, 175, 255);
		case 35:
			return new Color(0, 165, 255);
		case 36:
			return new Color(0, 155, 255);
		case 37:
			return new Color(0, 145, 255);
		case 38:
			return new Color(0, 135, 255);
		case 39:
			return new Color(0, 125, 255);
		case 40:
			return new Color(0, 115, 255);
		case 41:
			return new Color(0, 105, 255);
		case 42:
			return new Color(0, 95, 255);
		case 43:
			return new Color(0, 85, 255);
		case 44:
			return new Color(0, 75, 255);
		case 45:
			return new Color(0, 65, 255);
		case 46:
			return new Color(0, 45, 255);
		case 47:
			return new Color(0, 35, 255);
		case 48:
			return new Color(0, 25, 255);
		case 49:
			return new Color(0, 15, 255);
		case 50:
			return new Color(0, 5, 255);
		case 51:
			return new Color(0, 0, 255);
		case 52:
			return new Color(0, 0, 245);
		case 53:
			return new Color(0, 0, 235);
		case 54:
			return new Color(0, 0, 225);
		case 55:
			return new Color(0, 0, 215);
		case 56:
			return new Color(0, 0, 205);
		case 57:
			return new Color(0, 0, 195);
		case 58:
			return new Color(0, 0, 185);
		case 59:
			return new Color(0, 0, 175);
		case 60:
			return new Color(0, 0, 165);
		case 61:
			return new Color(0, 0, 155);
		case 62:
			return new Color(0, 0, 145);
		case 63:
			return new Color(0, 0, 135);
		case 64:
			return new Color(0, 0, 125);
		case 65:
			return new Color(0, 0, 115);
		case 66:
			return new Color(0, 0, 105);
		case 67:
			return new Color(0, 0, 95);
		case 68:
			return new Color(0, 0, 85);
		case 69:
			return new Color(0, 0, 75);
		case 70:
			return new Color(0, 0, 65);
		case 71:
			return new Color(0, 0, 55);
		case 72:
			return new Color(0, 0, 45);
		case 73:
			return new Color(0, 0, 35);
		case 74:
			return new Color(0, 0, 25);
		case 75:
			return new Color(0, 0, 15);
		case 76:
			return new Color(0, 0, 5);
		case 77:
			return new Color(0, 0, 0);
		case 78:
			return new Color(0, 5, 0);
		case 79:
			return new Color(0, 15, 0);
		case 80:
			return new Color(0, 25, 0);
		case 81:
			return new Color(0, 35, 0);
		case 82:
			return new Color(0, 45, 0);
		case 83:
			return new Color(0, 55, 0);
		case 84:
			return new Color(0, 65, 0);
		case 85:
			return new Color(0, 75, 0);
		case 86:
			return new Color(0, 85, 0);
		case 87:
			return new Color(0, 95, 0);
		case 88:
			return new Color(0, 95, 0);
		case 89:
			return new Color(0, 105, 0);
		case 90:
			return new Color(0, 115, 0);
		case 91:
			return new Color(0, 125, 0);
		case 92:
			return new Color(0, 135, 0);
		case 93:
			return new Color(0, 145, 0);
		case 94:
			return new Color(0, 155, 0);
		case 95:
			return new Color(0, 165, 0);
		case 96:
			return new Color(0, 175, 0);
		case 97:
			return new Color(0, 185, 0);
		case 98:
			return new Color(0, 195, 0);
		case 99:
			return new Color(0, 205, 0);
		case 100:
			return new Color(0, 215, 0);
		case 101:
			return new Color(0, 225, 0);
		case 102:
			return new Color(0, 235, 0);
		case 103:
			return new Color(0, 245, 0);
		case 104:
			return new Color(0, 255, 0);
		case 105:
			return new Color(255, 255, 0);
		case 106:
			return new Color(255, 255, 5);
		case 107:
			return new Color(255, 255, 15);
		case 108:
			return new Color(255, 255, 25);
		case 109:
			return new Color(255, 255, 35);
		case 110:
			return new Color(255, 255, 45);
		case 111:
			return new Color(255, 255, 65);
		case 112:
			return new Color(255, 255, 75);
		case 113:
			return new Color(255, 255, 85);
		case 114:
			return new Color(255, 255, 95);
		case 115:
			return new Color(255, 255, 105);
		case 116:
			return new Color(255, 255, 115);
		case 117:
			return new Color(255, 255, 125);
		case 118:
			return new Color(255, 255, 135);
		case 119:
			return new Color(255, 255, 145);
		case 120:
			return new Color(255, 255, 155);
		case 121:
			return new Color(255, 255, 165);
		case 122:
			return new Color(255, 255, 175);
		case 123:
			return new Color(255, 255, 185);
		case 124:
			return new Color(255, 255, 195);
		case 125:
			return new Color(255, 255, 205);
		case 126:
			return new Color(255, 255, 215);
		case 127:
			return new Color(255, 255, 225);
		case 128:
			return new Color(255, 255, 235);
		case 129:
			return new Color(255, 255, 245);
		case 130:
			return new Color(255, 255, 255);
		}
		System.out.println("Il y a une erreur ici !");
		return new Color(180, 7, 255);
	}
	
	
	public Color getRed(){
		return new Color(255, 60, 60);
	}
	
	public Color getGreen(){
		return new Color(60, 255, 60);
	}
	
	public Color getGray(){
		return new Color(50, 50, 50);
	}
	
	public Color getGrayTransprent(){
		return new Color(50, 50, 50, 220);
	}
	
	public Color getBlock(){
		return new Color(20, 20, 20, 200);
	}
	
	public Color getWhite()
	{
		return new Color(200, 200, 200);
	}
	  
	public Color getBlue()
	{
		return new Color(16, 115, 191);
	}
	
	public Color getEchapColor(){
		return new Color(77, 63, 60);
	}
	
	public Color getPurple(){
		return new Color(12, 6, 29);
	}
	
	public Color getPurple2(){
		return new Color(12, 6, 10);
	}
	
	public Color getSquarePass(){
		return new Color(26, 18, 13);
	}
	
	public Color getYellow(){
		return new Color(217, 187, 0);
	}
	
	public Color getOrange(){
		return new Color(217, 115, 0);
	}
	
	public Color getPassBlue(){
		return new Color(0, 78, 95);
	}
	
	public Color getPassBlue2(){
		return new Color(0, 81, 165);
	}
	
	public Color getYellowTranparent(){
		return new Color(225, 228, 0, 200);	
	}
	
	public Color getProgressBar(){
		return new Color(86, 18, 0);	
	}
	
	public int getMainButton(){
		return new Color(214, 131, 43).getRGB();
	}
	
	public int getMainButtonBorder(){
		return new Color(255, 36, 254).getRGB();
	}
	
	public int getTextColor(){
		return new Color(254, 0, 255).getRGB();
	}
	
	/*
	 * Color shop
	 * */
	
	public int getBackgroundShop(){
		return new Color(10, 19, 10, 245).getRGB();
	}
	
	public int getBlack(){	
		return new Color(0, 0, 0).getRGB();
	}
	
	public int getShopOrange(){
		return new Color(255, 63, 4).getRGB();
	}
	
	public int getShopItemFont(){
		return new Color(24, 5, 6, 100).getRGB();
	}
	
	
	
	public int getSuccessBack(){
		return new Color(44, 29, 24).getRGB();
	}
	
	public int getSuccessBorder(){
		return new Color(124, 29, 24).getRGB();
	}
	
}
