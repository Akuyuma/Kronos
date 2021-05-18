package fr.maxlego08.launcher.param;

import javax.swing.JFrame;

import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class ParametreFrame extends JFrame {

	private ParametrePanel parametrePanel;
	private static CrashReporter crashReporter;
	
	public ParametreFrame()	{
		
		this.setTitle("Paramètres");
		this.setSize(960, 540);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setBackground(Swinger.TRANSPARENT);
		this.setIconImage(Swinger.getResource("launcher_png.png"));
		this.setContentPane(parametrePanel = new ParametrePanel());
		
		WindowMover mover = new WindowMover(this);
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args)	{
		
//		Swinger.setSystemLookNFeel();
//		Swinger.setResourcePath("/fr/quiibz/vultaria/resources/");
//		MainClient.getClient().getLauncher().getKMC_CRASH_FOLDER().mkdirs();
//		crashReporter = new CrashReporter("vultaria", Launcher.KMC_CRASH_FOLDER);
//		
//		new LauncherFrame();
	}
	
	public static CrashReporter getCrashReporter() {
		
		return crashReporter;
	}
}