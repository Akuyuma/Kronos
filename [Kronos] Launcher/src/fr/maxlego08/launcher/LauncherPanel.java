package fr.maxlego08.launcher;

import static fr.theshark34.swinger.Swinger.drawFullsizedImage;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import fr.maxlego08.launcher.client.MainClient;
import fr.maxlego08.launcher.gson.DiscUtils;
import fr.northenflo.auth.mineweb.AuthMineweb;
import fr.northenflo.auth.mineweb.utils.Get.getSession;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openlauncherlib.util.ramselector.RamSelector;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.animation.Animator;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import fr.theshark34.swinger.textured.STexturedProgressBar;

@SuppressWarnings("serial")
public class LauncherPanel extends JPanel implements SwingerEventListener {

	private Image background = Swinger.getResource("vierge.png");
	
	private JTextField usernameField = new JTextField("");
	private JPasswordField passwordField = new JPasswordField("");

	private STexturedButton playButton = new STexturedButton(Swinger.getResource("play.png"), Swinger.getResource("play_hover.png"));
	
	private STexturedButton site = new STexturedButton(Swinger.getResource("site.png"), Swinger.getResource("site_hover.png"));
	private STexturedButton option = new STexturedButton(Swinger.getResource("option.png"), Swinger.getResource("option_hover.png"));
	
	private STexturedButton quitButton = new STexturedButton(Swinger.getResource("fermer.png"), Swinger.getResource("fermer_hover.png"));
	private STexturedButton hideButton = new STexturedButton(Swinger.getResource("reduire.png"), Swinger.getResource("reduire_hover.png"));
	
	
	public static RamSelector ram = new RamSelector(new File(MainClient.getClient().getLauncher().getKMC_DIR(), "ram.txt"));
	
	private STexturedProgressBar bar = new STexturedProgressBar(Swinger.getResource("bar.png"), Swinger.getResource("barfull.png"));
	
	public LauncherPanel() {
		
		setLayout(null);
		this.setBackground(Swinger.TRANSPARENT);
		
		load();
		
		this.usernameField.setForeground(new Color(0, 0, 0));
		this.usernameField.setFont(new Font("Arial", Font.BOLD, 24));
		this.usernameField.setCaretColor(new Color(0, 0, 0));
		this.usernameField.setOpaque(false);
		this.usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.usernameField.setBounds(580, 375, 170, 30);
		add(this.usernameField);

		this.passwordField.setForeground(new Color(0, 0, 0));
		this.passwordField.setFont(new Font("Arial", Font.BOLD, 24));
		this.passwordField.setCaretColor(new Color(0, 0, 0));
		this.passwordField.setOpaque(false);
		this.passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.passwordField.setBounds(580, 428, 170, 30);
		add(this.passwordField);

		this.playButton.addEventListener(this);
		this.playButton.setBounds(600, 500, 100, 75);
		this.playButton.setVisible(true);
		this.playButton.setFocusable(false);
		this.add(playButton);

		this.site.addEventListener(this);
		this.site.setBounds(385, 410, 120, 100);
		add(this.site);
		
		this.option.addEventListener(this);
		this.option.setBounds(890, 400, 120, 120);
		add(this.option);
		
		this.bar.setBounds(545, 340, 208, 13);
		add(this.bar);
		
		quitButton.setBounds(810, 360);
		quitButton.addEventListener(this);
		this.add(quitButton);
		
		hideButton.setBounds(850, 370);
		hideButton.addEventListener(this);
		this.add(hideButton);	
		
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEvent(SwingerEvent event) {

		if(event.getSource() == site) {

			try {
				
				Desktop.getDesktop().browse(new URI("https://vultaria.fr"));
				
			} catch (IOException | URISyntaxException e) {
				
				e.printStackTrace();
			}

		} else if(event.getSource() == option) {
			
			//MainClient.getClient().getLauncherFrame().getParam().setVisible(true);
			
		}else if (event.getSource() == quitButton){
			Animator.fadeOutFrame(MainClient.getClient().getLauncherFrame(), 1 , new Runnable() {
				
				@Override
				public void run() {
					
					System.exit(0);
					
				}
			});
		}else if (event.getSource() == hideButton){
			
				MainClient.getClient().getLauncherFrame().setState(JFrame.ICONIFIED);
			
		} else if(event.getSource() == playButton) {
			
			save();
			
			Thread t = new Thread() {
	
				public void run() {
					
					try {
						
						MainClient.getClient().getLauncher().auth(usernameField.getText(), passwordField.getText());
						
					} catch (AuthenticationException e) {
	
						setFieldsEnabled(true);
						e.printStackTrace();
						return;	
					}
	
					if(AuthMineweb.isConnected()) {
					
						String rank = getSession.getUser("rank");
						
						System.out.println(rank);
						
						if (!rank.equals("Administrateur")){
							JOptionPane.showMessageDialog(null, "Vous ne pouvez pas lancer le client !");
							return;
						}
						
						try {
		
							
							MainClient.getClient().getLauncher().update();
							playButton.setEnabled(false);
		
						} catch (Exception e) {
								
							MainClient.getClient().getLauncher().interruptThread();
							setFieldsEnabled(true);		
							JOptionPane.showMessageDialog(null, "Erreur, impossible de mettre à jour le jeu !");
							return;	
						}
		
						try {
		
							MainClient.getClient().getLauncher().launch();
		
						} catch (Exception e) {
		
							setFieldsEnabled(true);
							return;	
						}
						
					} else {
						
						setFieldsEnabled(true);
						JOptionPane.showMessageDialog(null, "Erreur, mauvais identifiants !");
					}
				}
	
			};
	
			t.start();
		}

	}

	public void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		drawFullsizedImage(graphics, this, background);
	}

	private void setFieldsEnabled(boolean enabled) {

		usernameField.setEnabled(enabled);
		passwordField.setEnabled(enabled);
	}
	
	public STexturedProgressBar getProgressBar() {
		
		return bar;
	}


	/**
	 * @param background the background to set
	 */
	public void setBackground(Image background) {
		this.background = background;
	}

	/**
	 * @return the usernameField
	 */
	public JTextField getUsernameField() {
		return usernameField;
	}

	/**
	 * @param usernameField the usernameField to set
	 */
	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}

	/**
	 * @return the passwordField
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * @return the playButton
	 */
	public STexturedButton getPlayButton() {
		return playButton;
	}

	/**
	 * @param playButton the playButton to set
	 */
	public void setPlayButton(STexturedButton playButton) {
		this.playButton = playButton;
	}

	/**
	 * @return the site
	 */
	public STexturedButton getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(STexturedButton site) {
		this.site = site;
	}

	/**
	 * @return the option
	 */
	public STexturedButton getOption() {
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOption(STexturedButton option) {
		this.option = option;
	}

	/**
	 * @return the ram
	 */
	public static RamSelector getRam() {
		return ram;
	}

	/**
	 * @param ram the ram to set
	 */
	public static void setRam(RamSelector ram) {
		LauncherPanel.ram = ram;
	}

	/**
	 * @return the bar
	 */
	public STexturedProgressBar getBar() {
		return bar;
	}

	/**
	 * @param bar the bar to set
	 */
	public void setBar(STexturedProgressBar bar) {
		this.bar = bar;
	}
	
	@SuppressWarnings("deprecation")
	public void save(){
		
		Map<String, String> d = new HashMap<>();
		d.put("username", usernameField.getText());
		d.put("password", Base64.encode(passwordField.getText().getBytes()));			
		
		File file = new File(MainClient.getClient().getLauncher().getKMC_DIR()+"/launcher/info.json");
		
		Gson gson = getGsonBuilder().create();
		try{
			DiscUtils.write(file, gson.toJson(d));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public GsonBuilder getGsonBuilder()
	{
		return new GsonBuilder()
		.setPrettyPrinting()
		.disableHtmlEscaping()
		.serializeNulls()
		.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE);
	}
	
	public void load(){
		
		try {
	
			File file = new File(MainClient.getClient().getLauncher().getKMC_DIR()+"/launcher/info.json");
			
			if (!file.exists()) return;
			
			Gson gson = getGsonBuilder().create();
		
			@SuppressWarnings("unchecked")
			Map<String, String> d = gson.fromJson(DiscUtils.read(file), Map.class);
			
			String z = d.get("password");
			
			byte[] bytet = Base64.decode(z);
			String password = new String(bytet);

			usernameField.setText(d.get("username"));
			passwordField.setText(password);
			
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}