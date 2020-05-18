package me.samkist.usernametooltip;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TooltipCommand implements CommandExecutor {

	private KarismicTooltip plugin;

	/**
	 * Executes the given command, returning its success.
	 * <br>
	 * If false is returned, then the "usage" plugin.yml entry for this command
	 * (if defined) will be sent to the player.
	 *
	 * @param sender  Source of the command
	 * @param command Command which was executed
	 * @param label   Alias of the command which was used
	 * @param args    Passed command arguments
	 * @return true if a valid command, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if(!sender.hasPermission("tooltip.reload")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l/!\\&c You do not have permission for this."));
			return true;
		}
		if(args.length == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l(!) &cUsage: /tooltip <command>"));
			return true;
		}

		if(args[0].equalsIgnoreCase("reload")) {
			plugin.reload();
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &aPlugin reloaded."));
		}

		return true;
	}

	public TooltipCommand(KarismicTooltip plugin) {
		this.plugin = plugin;
	}
}
