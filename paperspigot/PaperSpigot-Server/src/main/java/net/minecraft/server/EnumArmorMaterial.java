package net.minecraft.server;

public enum EnumArmorMaterial {

    CLOTH("CLOTH", 0, 5, new int[]{1, 3, 2, 1}, 15),
    CHAIN("CHAIN", 1, 15, new int[]{2, 5, 4, 1}, 12),
    IRON("IRON", 2, 15, new int[]{2, 6, 5, 2}, 9),
    GOLD("GOLD", 3, 7, new int[]{2, 5, 3, 1}, 25),
    DIAMOND("DIAMOND", 4, 33, new int[]{3, 8, 6, 3}, 10),
	ANGELITE("ANGELITE", 5, 300, new int[]{11, 16, 14, 11}, 10),
	CELESTINE("CELESTINE", 6, 250, new int[]{9, 14, 12, 9}, 10),
	MALACHITE("MALACHITE", 7, 200, new int[]{7, 12, 10, 7}, 10),
	CELENITE("CELENITE", 8, 175, new int[]{5, 10, 8, 5}, 10);
    private int f;
    private int[] g;
    private int h;
    private static final EnumArmorMaterial[] i = new EnumArmorMaterial[] {CLOTH, CHAIN, IRON, GOLD, DIAMOND, ANGELITE, CELESTINE, MALACHITE, CELENITE};

    private EnumArmorMaterial(String s, int i, int j, int[] aint, int k) {
        this.f = j;
        this.g = aint;
        this.h = k;
    }

    public int a(int i) {
        return ItemArmor.e()[i] * this.f;
    }

    public int b(int i) {
        return this.g[i];
    }

    public int a() {
        return this.h;
    }

    public Item b() {
        return this == CLOTH ? Items.LEATHER : 
        	(this == CHAIN ? Items.IRON_INGOT :
        	(this == GOLD ? Items.GOLD_INGOT : 
        	(this == IRON ? Items.IRON_INGOT : 
        		
        		(this == ANGELITE ? Items.ANGELITE : 
        		(this == CELESTINE ? Items.CELESTINE : 
        		(this == MALACHITE ? Items.MALACHITE : 
        		(this == CELENITE ? Items.CELENITE : 
        		
        	(this == DIAMOND ? Items.DIAMOND : null))))))));
    }
}
