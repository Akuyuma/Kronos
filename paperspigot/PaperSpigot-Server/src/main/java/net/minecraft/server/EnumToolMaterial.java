package net.minecraft.server;


public enum EnumToolMaterial {

    WOOD("WOOD", 0, 0, 59, 2.0F, 0.0F, 15),
    STONE("STONE", 1, 1, 131, 4.0F, 1.0F, 5),
    IRON("IRON", 2, 2, 250, 6.0F, 2.0F, 14),
    DIAMOND("DIAMOND", 3, 3, 1561, 8.0F, 3.0F, 10),
    GOLD("GOLD", 4, 0, 32, 12.0F, 0.0F, 22),
    
	ANGELITE("ANGELITE", 5, 9, 6000, 12.0F, 5.25F, 10), 
	CELESTINE("CELESTINE", 6, 8, 5000, 10F, 4.40F, 10),
	MALACHITE("MALACHITE", 7, 7, 4500, 9F, 3.70F, 10),
	CELENITE("CELENITE", 8, 6, 4000, 8.5F, 3.0F, 10),
	
	SCYTHE("SCYTHE", 9, 6, 4500, 15F, 6.50F, 11),
    
	ARES("ARES", 10, 6, 4000, 12F, 10.50F, 5),
	
    ;
    
    private final int f;
    private final int g;
    private final float h;
    private final float i;
    private final int j;
    private static final EnumToolMaterial[] k = new EnumToolMaterial[] {WOOD, STONE, IRON, DIAMOND, GOLD, ANGELITE, CELESTINE, MALACHITE, CELENITE, SCYTHE, ARES};

    private EnumToolMaterial(String s, int i, int j, int k, float f, float f1, int l) {
        this.f = j;
        this.g = k;
        this.h = f;
        this.i = f1;
        this.j = l;
    }

    public int a() {
        return this.g;
    }

    public float b() {
        return this.h;
    }

    public float c() {
        return this.i;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.j;
    }

    public Item f() {
        return this == WOOD ? Item.getItemOf(Blocks.WOOD) : (this == STONE ? Item.getItemOf(Blocks.COBBLESTONE) : 
        	(this == GOLD ? Items.GOLD_INGOT :
        	(this == IRON ? Items.IRON_INGOT : 

           		(this == ANGELITE ? Items.ANGELITE : 
        			(this == CELESTINE ? Items.CELESTINE : 
        				(this == MALACHITE ? Items.MALACHITE : 
        					(this == CELENITE ? Items.CELENITE :
        		
        	(this == DIAMOND ? Items.DIAMOND : null))))))));
    }
}
