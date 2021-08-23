package kr.codingtree.enummessage;

import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MessageMap extends LinkedHashMap<Enum, String> {

    @Getter
    private static HashMap<String, MessageMap> extend = new HashMap();

    @SneakyThrows
    public MessageMap(Class<? extends EnumMessage> enumMessage) {
        if (enumMessage.isEnum()) {
            Method m = getMethod(enumMessage, "values");

            for (Object o : (Object[]) m.invoke(null, null)) {
                if (o instanceof Enum && o instanceof EnumMessage) {
                    put((Enum) o, ((EnumMessage) o).getText());
                }
            }
            extend.put(enumMessage.getName(), this);
        }
    }

    public static String search(EnumMessage simpleMessage) {
        if (simpleMessage.getClass().isEnum()) {
            String name = simpleMessage.getClass().getName();
            if (extend.containsKey(name)) {
                MessageMap map = extend.get(name);
                Enum e = (Enum) simpleMessage;
                if (map.containsKey(e)) return map.get(e);
            }
        }
        return null;
    }


    private Method getMethod(Class clazz, String name) {
        for (Method method : clazz.getMethods()) if (method.getName().equals(name)) return method;
        return null;
    }
}
