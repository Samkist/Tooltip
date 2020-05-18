package me.samkist.usernametooltip;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class ChatListener implements Listener {

	private KarismicTooltip plugin;
	private final TextComponent newLine = new TextComponent(ComponentSerializer.parse("{text: \"\n\"}"));

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		ArrayList<BaseComponent> tooltip = new ArrayList<>();

		plugin.getConfig().getStringList("tooltip").forEach(o -> {
			String s = o.replace("%displayname%", event.getPlayer().getDisplayName())
				.replace("%playername%", event.getPlayer().getName());
			TextComponent comp = new TextComponent(ChatColor.translateAlternateColorCodes('&', s));
			tooltip.add(comp);
		});

		ArrayList<BaseComponent> components = new ArrayList<>();

		components.add(tooltip.get(0));
		for(int i = 1; i < tooltip.size(); i++) {
			components.add(newLine);
			components.add(tooltip.get(i));
		}

		String chatFormat = plugin.getConfig().getString("format")
				.replace("%playername%", event.getPlayer().getName())
				.replace("%displayname%", event.getPlayer().getDisplayName()) + " ";

		TextComponent toSend = new TextComponent(ChatColor.translateAlternateColorCodes('&', chatFormat + event.getMessage()));
		toSend.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, components.toArray(new BaseComponent[components.size()])));
		event.getPlayer().spigot().sendMessage(toSend);
	}

	public ChatListener(KarismicTooltip plugin) {
		this.plugin = plugin;
	}
}
