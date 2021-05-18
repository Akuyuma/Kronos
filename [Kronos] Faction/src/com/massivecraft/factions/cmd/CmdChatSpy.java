package com.massivecraft.factions.cmd;

import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;

public class CmdChatSpy extends FCommand
{
	public CmdChatSpy()
	{
		super();
		this.aliases.add("chatspy");
		this.aliases.add("spy");

		this.optionalArgs.put("on/off", "flip");

		this.permission = Permission.CHATSPY.node;
		this.disableOnLock = false;

		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}

	@Override
	public void perform()
	{
		fme.setSpyingChat(this.argAsBool(0, ! fme.isSpyingChat()));

		if ( fme.isSpyingChat())
		{
			fme.msg(P.prefix +"Mode d'espion Factions §aActivé§e.");
			P.p.log(fme.getName() + " has ENABLED chat spying mode.");
		}
		else
		{
			fme.msg(P.prefix +"Mode d'espion Factions §cDésactivé§e.");
			P.p.log(fme.getName() + " DISABLED chat spying mode.");
		}
	}
}