package fr.maxlego08.kronos;

public class Kronos {

	/*
	 * Développé par Maxlego08 pour le serveur Kronos
	 * */
	
	private String clientVersion = "1.0.0";
	
	private static boolean alreadyInit = false;
	
	private static final Kronos kronos = new Kronos();
	
	public void init()
	{
		if(alreadyInit) return;
		
		
		alreadyInit = true;
	}
	
	public String getClientVersion() {
		return clientVersion;
	}
	
	public static Kronos getKronos() {
		return kronos;
	}
	
	
}
