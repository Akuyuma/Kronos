package fr.maxlego08.launcher.param;

import static fr.theshark34.swinger.Swinger.drawFullsizedImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.maxlego08.launcher.client.MainClient;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

@SuppressWarnings("serial")
public class ParametrePanel extends JPanel implements SwingerEventListener {

	private Image background = Swinger.getResource("option_back.png");
	
	private JTextField ram = new JTextField(MainClient.getClient().getLauncher().getRam());
	
	private STexturedButton closeButton = new STexturedButton(Swinger.getResource("close.png"), Swinger.getResource("close_hover.png"));
	private STexturedButton purgeButton = new STexturedButton(Swinger.getResource("purger_desac.png"), Swinger.getResource("purger_activ.png"));
	
	public ParametrePanel() {
		
		setLayout(null);
		this.setBackground(Swinger.TRANSPARENT);
		
		this.closeButton.addEventListener(this);
		this.closeButton.setBounds(700, 13, 107, 103);
		add(this.closeButton);
				
		this.purgeButton.addEventListener(this);
		this.purgeButton.setBounds(215, 310, 550, 180);
		add(this.purgeButton);
		
		this.ram.setForeground(new Color(0, 0, 0));
		this.ram.setFont(new Font("Arial", Font.BOLD, 30));
		this.ram.setCaretColor(new Color(0, 0, 0));
		this.ram.setOpaque(false);
		this.ram.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.ram.setBounds(460, 170, 120, 40);
		add(this.ram);
		
		this.setVisible(true);
	}

	@Override
	public void onEvent(SwingerEvent event) {

		if(event.getSource() == closeButton) {

			MainClient.getClient().getLauncher().setRam(this.ram.getText());
			MainClient.getClient().getLauncherFrame().getParam().setVisible(false);	
			System.out.println(this.ram.getText());
		}
		if(event.getSource() == purgeButton) {
			int ms = purgeDirectory(MainClient.getClient().getLauncher().getKMC_DIR());
			JOptionPane.showMessageDialog(null, "Fichier purger en " + ms  +" ms !");
		}	
	}

	public int purgeDirectory(File directory)
	  {
		File d = directory;
		long timer = System.currentTimeMillis();
		try{
		    File[] arrayOfFile;
		    int j = (arrayOfFile = directory.listFiles()).length;
		    for (int i = 0; i < j; i++)
		    {
		      File file = arrayOfFile[i];
		      file.delete();
		    }
		    directory = new File(directory+"/libs");
		    j = (arrayOfFile = directory.listFiles()).length;
		    for (int i = 0; i < j; i++)
		    {
		      File file = arrayOfFile[i];
		      file.delete();
		    }	    
		    directory = new File(d+"/natives");
		    j = (arrayOfFile = directory.listFiles()).length;
		    for (int i = 0; i < j; i++)
		    {
		    	File file = arrayOfFile[i];
		    	file.delete();
		    }	
		}catch (Exception e) { }
	    return (int) Math.abs(timer-System.currentTimeMillis());
	  }
	
	public void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		drawFullsizedImage(graphics, this, background);
	}	
}