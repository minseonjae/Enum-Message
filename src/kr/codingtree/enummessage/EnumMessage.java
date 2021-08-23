package kr.codingtree.enummessage;

import java.text.MessageFormat;

public interface EnumMessage {
    String getText();

    default String getName() {
        return null;
    }

    default void console() {
        MessageAssist.console(MessageMap.search(this));
    }
    default void console(Object... args) {
        MessageAssist.console(MessageFormat.format(MessageMap.search(this), args));
    }
    default void user(Object sender) {
        MessageAssist.user(sender, MessageMap.search(this));
    }
    default void user(Object sender, Object... args) {
        MessageAssist.user(sender, MessageFormat.format(MessageMap.search(this), args));
    }
    default void broadcast() {
        MessageAssist.broadcast(MessageMap.search(this));
    }
    default void broadcast(Object... args) {
        MessageAssist.broadcast(MessageFormat.format(MessageMap.search(this), args));
    }
}

