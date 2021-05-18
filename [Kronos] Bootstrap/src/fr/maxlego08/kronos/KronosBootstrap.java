package fr.maxlego08.kronos;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ClasspathConstructor;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.openlauncherlib.util.SplashScreen;
import fr.theshark34.openlauncherlib.util.explorer.ExploredDirectory;
import fr.theshark34.openlauncherlib.util.explorer.Explorer;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.swinger.Swinger;

public class KronosBootstrap {

	private static SplashScreen splash;
	private static Thread thread;
	private static Thread t;
	private static final File VULTARIA_B_DIR = new File(GameDirGenerator.createGameDir("Kronos"), "launcher");
	private static final CrashReporter VULTARIA_B_REPORTER = new CrashReporter("Kronos", VULTARIA_B_DIR);
	
	public static void main(String[] args) {
		
		
		Swinger.setSystemLookNFeel();
	    Swinger.setResourcePath("/fr/maxlego08/kronos/ressources/");
	    
	    showSplash();
	    
	    t = new Thread() 
	    {
	    	@Override
	    	public void run() 
	    	{
	    	try 
	    		{	
	    		update();
	    		} 
	    	
	    	catch (Exception e) 
	    		{
	    		if(thread != null) 
	    			thread.interrupt();
	    		
	    		VULTARIA_B_REPORTER.catchError(e, "[Kronos] Erreur, impossible de mettre à jour le launcher !");
	    		} 
	    	
	    	
	    	try 
	        	{
	    	launch();
	        	} 
	        catch(LaunchException e) 
	    		{
	    	VULTARIA_B_REPORTER.catchError(e, "[Kronos] Erreur, Impossible de lancer le launcher !");
	    		}
	    	};
	    };
	 t.start();
	}

	private static void showSplash() {
		splash = new SplashScreen("Kronos", Swinger.getResource("logo.png"));
		splash.setIconImage(Swinger.getResource("icon.png"));
		splash.setLayout(null);
		splash.setTransparent();
		splash.setVisible(true);
	}

	private static void update() throws Exception {
		SUpdate su = new SUpdate("http://www.kronosfaction.fr/launcher/bootstrap", VULTARIA_B_DIR);
		su.getServerRequester().setRewriteEnabled(true);
		su.addApplication(new FileDeleter());
		thread = new Thread() {
		
			
			@Override
			public void run() 
			{
				while(!this.isInterrupted()) 
				{

				}
			}
			
		};
		thread.start();
		
		su.start();
		thread.interrupt();
	}

	private static void launch() throws LaunchException {
		ClasspathConstructor constructor = new ClasspathConstructor();
		ExploredDirectory gameDir = Explorer.dir(VULTARIA_B_DIR);
		constructor.add(gameDir.sub("Libs").allRecursive().files().match("^(.*\\.((jar)$))*$"));
		constructor.add(gameDir.get("launcher.jar"));
		ExternalLaunchProfile profile = new ExternalLaunchProfile("fr.maxlego08.launcher.client.MainClient", constructor.make());
		List<String> s = Arrays.asList(new String[]{"e","d","d","d","5","6"});
		profile.setArgs(s);
		ExternalLauncher launcher = new ExternalLauncher(profile);
		Process b = launcher.launch();
		splash.setVisible(false);
		System.exit(0);
		try {
			b.waitFor();
		} catch(InterruptedException ignored) {
			
		} 
	}
	
}
