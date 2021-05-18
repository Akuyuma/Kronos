package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.ChatMode;
import com.massivecraft.factions.struct.Permission;

public class CmdChat extends FCommand
{
	
	public CmdChat()
	{
		super();
		this.aliases.add("c");
		this.aliases.add("chat");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("mode", "next");
		
		this.permission = Permission.CHAT.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = true;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		if ( ! Conf.factionOnlyChat )
		{
			msg("<b>The built in chat chat channels are disabled on this server.");
			return;
		}
		
		String modeString = this.argAsString(0);
		ChatMode modeTarget = fme.getChatMode().getNext();
		
		if (modeString != null)
		{
			modeString.toLowerCase();
			if(modeString.startsWith("p"))
			{
				modeTarget = ChatMode.PUBLIC;
			}
			else if (modeString.startsWith("a"))
			{
				modeTarget = ChatMode.ALLIANCE;
			}
			else if(modeString.startsWith("f"))
			{
				modeTarget = ChatMode.FACTION;
			}
			else
			{
				msg(P.prefix +"L'un de vos arguments n'est pas correct: �a'a'�7(Alliance) �e/ �a'f' �7(Factions) �e/ �a'p' �7(Public)�e.");
				return;
			}
		}
		
		fme.setChatMode(modeTarget);
		
		if(fme.getChatMode() == ChatMode.PUBLIC)
		{
			msg(P.prefix +"Vous �tes d�sormais dans la canal �aPublic�e.");
		}
		else if (fme.getChatMode() == ChatMode.ALLIANCE )
		{
			msg(P.prefix +"Vous �tes d�sormais dans la canal �aAlliance�e.");
		}
		else
		{
			msg(P.prefix +"Vous �tes d�sormais dans la canal �aFactions�e.");
		}
	}
}
