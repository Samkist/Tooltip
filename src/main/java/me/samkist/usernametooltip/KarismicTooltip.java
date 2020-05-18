package me.samkist.usernametooltip;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class KarismicTooltip extends JavaPlugin {

	private FileConfiguration config;
	private File configFile;

	@Override
	public void onEnable() {
		//Setup listeners
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);

		// Plugin startup logic
		getConfig().options().copyDefaults(true);
		saveConfig();
		configFile = new File(getDataFolder(), "config.yml");
		config = getConfig();

		//Setup commands
		getCommand("tooltip").setExecutor(new TooltipCommand(this));
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic

	}

	public void reload() {
		reloadConfig();
	}
}
