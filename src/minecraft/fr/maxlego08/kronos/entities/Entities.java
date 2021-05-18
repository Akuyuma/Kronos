package fr.maxlego08.kronos.entities;

import fr.maxlego08.kronos.entity.EntityGodPearl;
import net.minecraft.entity.Entity;

public enum Entities {

	DYNAMITE(EntityDynamite.class, "Dynamite", 252),
	GODPEARL(EntityGodPearl.class, "GodPearl", 253),
	
	;
	
	private final Class entity;
	private final String name;
	private final int id;
	private int eggColorBack = 0;
	private int eggColorFront = 0;
	
	private Entities(Class entity, String name, int id, int eggColorBack, int eggColorFront) {
		this.entity = entity;
		this.name = name;
		this.id = id;
		this.eggColorBack = eggColorBack;
		this.eggColorFront = eggColorFront;
	}
	
	private Entities(Class entity, String name, int id) {
		this.entity = entity;
		this.name = name;
		this.id = id;
	}

	public boolean hasColoredEgg(){
		return eggColorBack != 0 && eggColorFront != 0;
	}
	
	public int getEggColorBack() {
		return eggColorBack;
	}

	public int getEggColorFront() {
		return eggColorFront;
	}
	public Class getEntity() {
		return entity;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	
	
}
