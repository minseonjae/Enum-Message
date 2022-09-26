package kr.codingtree.enummessage.adapter;

import java.io.File;

public interface PlatformAdapter {

    void console(Object message);

    void user(Object sender, Object message);

    void broadcast(Object message);

    String replaceColor(Object message);

    File getDataFolder(Object plugin);

}
