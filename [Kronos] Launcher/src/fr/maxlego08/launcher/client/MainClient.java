package fr.maxlego08.launcher.client;

import javax.swing.JOptionPane;

import fr.maxlego08.launcher.Launcher;
import fr.maxlego08.launcher.LauncherFrame;
import fr.maxlego08.launcher.LauncherPanel;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;

public class MainClient {

	private Launcher launcher;
	private LauncherFrame launcherFrame;
	private LauncherPanel launcherPanel;
	private CrashReporter crashReporter;
	private static MainClient client;
	
	public static void main(String[] args) {
		if (args.length != 6){
			JOptionPane.showMessageDialog(null, "Erreur, Vous ne pouvez pas lancer le launcheur sans passer par le bootstrap");
			System.exit(0);
		}
		new MainClient();		
	}
	
	public MainClient() {
		client = this;
		Swinger.setSystemLookNFeel();
		Swinger.setResourcePath("/fr/maxlego08/launcher/resources/");
		
		/*
		 * Chargement des class
		 * */
		
		launcher = new Launcher();
		launcher.getKMC_CRASH_FOLDER().mkdirs();
		crashReporter = new CrashReporter("Kronos", launcher.getKMC_CRASH_FOLDER());		
		
		launcherPanel = new LauncherPanel();
		launcherFrame = new LauncherFrame();
	}
	
	public Launcher getLauncher() {
		return launcher;
	}
	
	public CrashReporter getCrashReporter() {
		return crashReporter;
	}
	
	public LauncherFrame getLauncherFrame() {
		return launcherFrame;
	}
	
	public static MainClient getClient() {
		if (client == null){
			System.err.println("Le client est null !");
		}
		return client;
	}
	
	public LauncherPanel getLauncherPanel() {
		return launcherPanel;
	}
	
}
