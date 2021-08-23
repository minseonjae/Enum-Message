package kr.codingtree.enummessage.adapter.bukkit;

import kr.codingtree.enummessage.adapter.PlatformAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BukkitAdapter implements PlatformAdapter {
    @Override
    public void console(Object message) {
        Bukkit.getConsoleSender().sendMessage(replaceColor(message));
    }
    @Override
    public void user(Object sender, Object message) {
        if (sender instanceof CommandSender) ((CommandSender) sender).sendMessage(replaceColor(message));
    }
    @Override
    public void broadcast(Object message) {
        Bukkit.broadcastMessage(replaceColor(message));
    }
    @Override
    public String replaceColor(Object message) {
        return ChatColor.translateAlternateColorCodes('&', message.toString());
    }
    @Override
    public File getDataFolder(Object plugin) {
        if (plugin instanceof JavaPlugin) return ((JavaPlugin) plugin).getDataFolder();
        return null;
    }
}
