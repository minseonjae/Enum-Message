package kr.codingtree.plugin.example;

import kr.codingtree.enummessage.EnumMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class TestKPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        EnumMessage.loadConfig(TestMessage.class, this);

        TestMessage.MESSAGE_1.console();
        TestMessage.MESSAGE_2.console("a", "c", "b", "d");
        TestMessage.MESSAGE_3.console();
    }
}