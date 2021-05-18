package fr.maxlego08.launcher;

import javax.swing.JFrame;

import fr.maxlego08.launcher.client.MainClient;
import fr.maxlego08.launcher.param.ParametreFrame;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class LauncherFrame extends JFrame {

	private ParametreFrame param;
	private static CrashReporter crashReporter;
	
	public LauncherFrame()	{
//		param = new ParametreFrame();
//		param.setVisible(false);
		
		this.setTitle("Kronos");
		this.setSize(1202, 677);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setBackground(Swinger.TRANSPARENT);
		
		
		this.setIconImage(Swinger.getResource("icon.png"));
		
		
		this.setContentPane(MainClient.getClient().getLauncherPanel());
		
		WindowMover mover = new WindowMover(this);
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		
		this.setVisible(true);
	}
	
	public static CrashReporter getCrashReporter() {
		
		return crashReporter;
	}
	

	/**
	 * @return the param
	 */
	public ParametreFrame getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(ParametreFrame param) {
		this.param = param;
	}


	/**
	 * @param crashReporter the crashReporter to set
	 */
	public static void setCrashReporter(CrashReporter crashReporter) {
		LauncherFrame.crashReporter = crashReporter;
	}
	
	
}
