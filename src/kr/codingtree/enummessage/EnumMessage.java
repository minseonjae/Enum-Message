package kr.codingtree.enummessage;

import kr.codingtree.enummessage.adapter.PlatformAdapter;
import kr.codingtree.enummessage.adapter.bukkit.BukkitAdapter;
import kr.codingtree.enummessage.adapter.bungee.BungeeAdapter;

import kr.codingtree.platformconfig.Config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnumMessage {

    private PlatformAdapter platformAdapter = null;

    private boolean isClass(String path) {
        try {
            Class.forName(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 어떤 플랫폼에서 사용하는지 확인하여 해당 플랫폼에 호환되는 어댑터를 가져옵니다.
     * @return Bukkit 또는 Bungee에 호환되는 어댑터
     */
    public PlatformAdapter getPlatformAdapter() {
        if (platformAdapter == null) {
            try {
                if (isClass("net.md_5.bungee.BungeeCord")) platformAdapter = new BungeeAdapter();
                else if (isClass("org.bukkit.Bukkit")) platformAdapter = new BukkitAdapter();
                else throw new Exception("Could not setup!");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return platformAdapter;
    }

    /**
     * 콘솔에 메세지 출력
     * @param message 메세지
     */
    protected void console(Object message) {
        getPlatformAdapter().console(message);
    }

    /**
     * 유저에게 메세지를 출력합니다.
     * @param sender Bukkit 또는 Bungee의 Player 클래스
     * @param message 메세지
     */
    protected void user(Object sender, Object message) {
        getPlatformAdapter().user(sender, message);
    }

    /**
     * 모든 유저에게 메세지를 출력합니다.
     * @param message 메세지
     */
    protected void broadcast(Object message) {
        getPlatformAdapter().broadcast(message);
    }

    /**
     * 설정 파일이 없을 경우 열거체의 상수 값을 기본 값으로 설정 파일에 저장하며,
     * 이미 설정 파일에 값이 있을 경우 설정 값으로 불러옵니다.
     * @param simpleEnumMessage 열거체
     * @param plugin Bukkit의 JavaPlugin 또는 Bungee의 Plugin
     */
    public void loadConfig(Class<? extends SimpleEnumMessage> simpleEnumMessage, Object plugin) {
        if (simpleEnumMessage.isEnum()) {
            // Config 생성 및 불러오기
            Config config = new Config(getPlatformAdapter().getDataFolder(plugin), "message-config.yml").load();
            EnumMessageMap messageMap = new EnumMessageMap(simpleEnumMessage);

            // 열거체 상수 값을 기본 값으로 저장합니다.
            messageMap.forEach((key, value) -> {
                SimpleEnumMessage e = (SimpleEnumMessage) key;
                config.addDefault(key.name(), value);
            });

            // 설정된 값이 없을 경우 기본 값을 저장합니다.
            config.save();

            // 열거체 상수를 바탕으로 설정된 값을 불러옵니다.
            messageMap.keySet().forEach(key -> {
                SimpleEnumMessage e = (SimpleEnumMessage) key;
                messageMap.put(key, config.getString(key.name()));
            });
        }
    }
}
