package kr.codingtree.enummessage.adapter.bungee;

import kr.codingtree.enummessage.adapter.PlatformAdapter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

public class BungeeAdapter implements PlatformAdapter {
    @Override
    public void console(Object message) {
        ProxyServer.getInstance().getConsole().sendMessage(message instanceof BaseComponent ? (BaseComponent) message : new TextComponent(replaceColor(message)));
    }
    @Override
    public void user(Object sender, Object message) {
        if (sender instanceof CommandSender) ((CommandSender) sender).sendMessage(message instanceof BaseComponent ? (BaseComponent) message : new TextComponent(replaceColor(message)));
    }
    @Override
    public void broadcast(Object message) {
        ProxyServer.getInstance().broadcast(message instanceof BaseComponent ? (BaseComponent) message : new TextComponent(replaceColor(message)));
    }
    @Override
    public String replaceColor(Object message) {
        return ChatColor.translateAlternateColorCodes('&', message.toString());
    }
    @Override
    public File getDataFolder(Object plugin) {
        if (plugin instanceof Plugin) return ((Plugin) plugin).getDataFolder();
        return null;
    }
}

