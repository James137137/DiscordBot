package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DiscordChatCommand extends Command {
	
	String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	Boolean consoleOutput = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public DiscordChatCommand() {
		super("discordchat", "DiscordBot.CommandChat", "dcc");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		String message = "";
		for (String arg : args) {
			message = message + arg + " ";
		}
		
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			MessageSender.sendMessage(message, player.getName(), player.getDisplayName(), "Message", true, true, true);
		} else {
			MessageSender.sendMessage(message, sender.getName(), sender.getName(), "Message", true, true, true);
		}
		return;
	}
}