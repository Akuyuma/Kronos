package fr.maxlego08.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import fr.maxlego08.launcher.client.MainClient;
import fr.northenflo.auth.exception.DataEmptyException;
import fr.northenflo.auth.exception.DataWrongException;
import fr.northenflo.auth.exception.ServerNotFoundException;
import fr.northenflo.auth.mineweb.AuthMineweb;
import fr.northenflo.auth.mineweb.utils.TypeConnection;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.openlauncherlib.util.ProcessLogManager;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.swinger.animation.Animator;

public class Launcher {

	private final GameVersion KMC_VERSION = new GameVersion("1.7.10", GameType.V1_7_10);
	private final GameInfos KMC_INFOS = new GameInfos("Kronos", KMC_VERSION, new GameTweak[] {});
	private final File KMC_DIR = KMC_INFOS.getGameDir();
	private final File KMC_CRASH_FOLDER = new File(KMC_DIR, "crash");

	private AuthInfos authInfos;
	private Thread updateThread;
	
	public void auth(String username, String password) throws AuthenticationException {
		
		AuthMineweb.setTypeConnection(TypeConnection.launcher);
		AuthMineweb.setUrlRoot("http://kronosfaction.fr");
		
		AuthMineweb.setUsername(username);
		AuthMineweb.setPassword(password);
		
		try {
			
			AuthMineweb.start();
			
		} catch (DataWrongException | DataEmptyException | ServerNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
		
		if(AuthMineweb.isConnected()) {
			authInfos = new AuthInfos(username, "sry", "nope");	
			
		}		
	}
	
	public void update() throws Exception {
		
		
		
		SUpdate su = new SUpdate ("http://www.kronosfaction.fr/launcher/", KMC_DIR); 
		su.getServerRequester().setRewriteEnabled(true);
		su.addApplication(new FileDeleter());
		MainClient.getClient().getLauncherPanel().getProgressBar().setVisible(true);
		updateThread = new Thread() {
			
			private int val;
			private int max;
			
			@Override
			public void run() {
				
				while(!this.isInterrupted()) {
					
					if(BarAPI.getNumberOfFileToDownload() == 0) {
                        
                    } else {
                    	
                        this.val = ((int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000L));
                        this.max = ((int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000L));
                        MainClient.getClient().getLauncherPanel().getProgressBar().setMaximum(this.max);
                        MainClient.getClient().getLauncherPanel().getProgressBar().setValue(this.val);           
                    }				
				}
			}
		};
		updateThread.start();
		
		su.start();
		MainClient.getClient().getLauncherPanel().getProgressBar().setVisible(false);
		
		updateThread.interrupt();
	}
	
	public void launch() throws LaunchException 
	{
		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(KMC_INFOS, GameFolder.BASIC, authInfos);
		profile.getVmArgs().addAll(Arrays.asList(LauncherPanel.ram.getRamArguments()));
		ExternalLauncher launcher = new ExternalLauncher(profile);
		
		Process p = launcher.launch();
		
		ProcessLogManager manager = new ProcessLogManager(p.getInputStream(), new File(KMC_DIR, "logs.txt"));
		manager.start();
		
		try
		{
			Thread.sleep(5000L);
			MainClient.getClient().getLauncherFrame().setVisible(false);
			p.waitFor();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		Animator.fadeOutFrame(MainClient.getClient().getLauncherFrame(), 1 , new Runnable() {
			
			@Override
			public void run() {
				
				System.exit(0);
				
			}
		});
	}
	
	public void interruptThread() {
		updateThread.interrupt();
	}
	
	public int getRam()
	  {
	    int getter = 1024;
	    try {
	      InputStream flux = new FileInputStream(new File(KMC_DIR + "ram.txt"));
	      InputStreamReader lecture = new InputStreamReader(flux);
	      BufferedReader buff = new BufferedReader(lecture);
	      String ligne;
	      while ((ligne = buff.readLine()) != null)
	      {
	        getter = Integer.parseInt(ligne);
	        getter++;
	      }
	      buff.close();
	    } catch (Exception localException) {
	    }
	    return getter;
	  }

	  public void setRam(String val) {
	    File ramfile = new File(new File(KMC_DIR.toString()), "ram.txt");
	    if (!ramfile.exists())
			{
			try{
				ramfile.createNewFile();
		  	} catch (IOException e) {
			  	e.printStackTrace();
	  		}
		}
		try {
			FileWriter rama = new FileWriter(new File(KMC_DIR + "ram.txt"));
			rama.write(val);
			rama.close();
		}catch (Exception localException){}
	}

	/**
	 * @return the authInfos
	 */
	public AuthInfos getAuthInfos() {
		return authInfos;
	}

	/**
	 * @param authInfos the authInfos to set
	 */
	public void setAuthInfos(AuthInfos authInfos) {
		this.authInfos = authInfos;
	}

	/**
	 * @return the updateThread
	 */
	public Thread getUpdateThread() {
		return updateThread;
	}

	/**
	 * @param updateThread the updateThread to set
	 */
	public void setUpdateThread(Thread updateThread) {
		this.updateThread = updateThread;
	}

	/**
	 * @return the kMC_VERSION
	 */
	public GameVersion getKMC_VERSION() {
		return KMC_VERSION;
	}

	/**
	 * @return the kMC_INFOS
	 */
	public GameInfos getKMC_INFOS() {
		return KMC_INFOS;
	}

	/**
	 * @return the kMC_DIR
	 */
	public File getKMC_DIR() {
		return KMC_DIR;
	}

	/**
	 * @return the kMC_CRASH_FOLDER
	 */
	public File getKMC_CRASH_FOLDER() {
		return KMC_CRASH_FOLDER;
	}
	
}