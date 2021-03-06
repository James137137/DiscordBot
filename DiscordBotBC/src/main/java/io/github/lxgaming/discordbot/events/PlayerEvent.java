package io.github.lxgaming.discordbot.events;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerEvent implements Listener {
	
	private static Boolean playerChat = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerChat");
	private static Boolean playerJoin = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerJoin");
	private static Boolean playerQuit = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerQuit");
	
	@EventHandler
	public void onPlayerChat(ChatEvent C) {
		ProxiedPlayer player = (ProxiedPlayer) C.getSender();
		
		if (C.isCommand() || C.isCancelled()) {
			return;
		}
		
		if (playerChat == true && player.hasPermission("DiscordBot.GlobalChat")) {
			MessageSender.sendMessage(C.getMessage(), player.getName(), player.getDisplayName(), "Message", true, false, false);
		}
		return;
	}
	
	@EventHandler
	public void onServerConnect(ServerConnectEvent SC) {
		if (playerJoin == true) {
			MessageSender.sendMessage("Joined", SC.getPlayer().getName(), SC.getPlayer().getDisplayName(), "Player.Join", true, false, false);
		}
		return;
	}
	
	@EventHandler
	public void onServerDisconnect(ServerDisconnectEvent SD) {
		if (playerQuit == true) {
			MessageSender.sendMessage("Quit", SD.getPlayer().getName(), SD.getPlayer().getDisplayName(), "Player.Quit", true, false, false);
		}
		return;
	}
}