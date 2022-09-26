package kr.codingtree.enummessage;

import java.text.MessageFormat;

public interface SimpleEnumMessage {

    String getText();

    default void console() {
        console(EnumMessageMap.search(this));
    }
    default void console(Object... args) {
        EnumMessage.console(MessageFormat.format(EnumMessageMap.search(this), args));
    }
    default void user(Object sender) {
        user(sender, EnumMessageMap.search(this));
    }
    default void user(Object sender, Object... args) {
        user(sender, MessageFormat.format(EnumMessageMap.search(this), args));
    }
    default void broadcast() {
        broadcast(EnumMessageMap.search(this));
    }
    default void broadcast(Object... args) {
        broadcast(MessageFormat.format(EnumMessageMap.search(this), args));
    }

}
