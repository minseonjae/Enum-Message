package kr.codingtree.plugin.example;

import kr.codingtree.enummessage.EnumMessage;
import net.md_5.bungee.api.plugin.Plugin;

public class TestGPlugin extends Plugin {
    @Override
    public void onEnable() {
        EnumMessage.loadConfig(TestMessage.class, this);

        TestMessage.MESSAGE_1.console();
        TestMessage.MESSAGE_2.console("a", "c", "b", "d");
        TestMessage.MESSAGE_3.console();
    }
}