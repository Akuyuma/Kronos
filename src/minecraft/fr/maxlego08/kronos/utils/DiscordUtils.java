package fr.maxlego08.kronos.utils;

import java.util.Date;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordUtils {

	public DiscordUtils() {
		try{
		    DiscordEventHandlers handlers = new DiscordEventHandlers();
		    DiscordRPC.discordInitialize("546262884368252958", handlers, true);
		    DiscordRPC.discordRunCallbacks();
		    Date date = new Date();
		    long epoch = date.getTime();	    
			DiscordRichPresence rich = new DiscordRichPresence.Builder("Serveur faction sous launcher").setStartTimestamps(epoch / 1000L).build();
			DiscordRPC.discordUpdatePresence(rich);	
		}catch (Exception e) { }
	}
	
}
