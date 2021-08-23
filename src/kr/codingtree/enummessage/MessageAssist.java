package kr.codingtree.enummessage;

import kr.codingtree.enummessage.adapter.PlatformAdapter;
import kr.codingtree.enummessage.adapter.bukkit.BukkitAdapter;
import kr.codingtree.enummessage.adapter.bungee.BungeeAdapter;
import kr.codingtree.platformconfig.Config;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageAssist {

    private PlatformAdapter platformAdapter = null;

    @Getter
    private boolean setup = false;

    private void setup() {
        try {
            if (isClass("net.md_5.bungee.BungeeCord")) platformAdapter = new BungeeAdapter();
            else if (isClass("org.bukkit.Bukkit")) platformAdapter = new BukkitAdapter();
            else throw new Exception("Could not setup!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlatformAdapter getPlatformAdapter() {
        if (platformAdapter == null) setup();
        return platformAdapter;
    }

    public void console(Object message) {
        getPlatformAdapter().console(message);
    }
    public void user(Object sender, Object message) {
        getPlatformAdapter().user(sender, message);
    }
    public void broadcast(Object message) {
        getPlatformAdapter().broadcast(message);
    }

    public String replaceColor(Object message) {
        return getPlatformAdapter().replaceColor(message);
    }

    public void loadConfig(Class<? extends EnumMessage> enumMessage, Object plugin) {
        if (enumMessage.isEnum()) {
            Config config = new Config(MessageAssist.getPlatformAdapter().getDataFolder(plugin), "message-config.yml").load();

            MessageMap messageMap = new MessageMap(enumMessage);

            messageMap.forEach((key, value) -> {
                EnumMessage e = (EnumMessage) key;
                config.addDefault(e.getName() != null ? e.getName() : key.name(), value);
            });

            config.save();

            messageMap.keySet().forEach(key -> {
                EnumMessage e = (EnumMessage) key;
                messageMap.put(key, config.getString(e.getName() != null ? e.getName() : key.name()));
            });
        }
    }

    private boolean isClass(String path) {
        try {
            Class.forName(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
