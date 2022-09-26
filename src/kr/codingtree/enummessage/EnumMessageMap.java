package kr.codingtree.enummessage;

import kr.codingtree.enummessage.exception.NotEnumException;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.HashMap;

public class EnumMessageMap extends HashMap<Enum, String> {

    @Getter
    private static HashMap<String, EnumMessageMap> extend = new HashMap<>();

    /**
     * 열거체인지 확인 후 열거체 안에 있는 상수 그 값을 저장합니다.
     * @param simpleEnumMessage 열거체
     */
    public EnumMessageMap(Class<? extends SimpleEnumMessage> simpleEnumMessage) {
        try {
            // 열거체인지 확인
            if (simpleEnumMessage.isEnum()) {
                // 열거체 안에 있는 기본 values() 메소드를 불러옴
                Method method = simpleEnumMessage.getMethod("values", null);

                for (Object object : (Object[]) method.invoke(null)) {
                    if (object instanceof SimpleEnumMessage) {
                        // 열거체.상수 안에 저장되어 있는 값을 가져와 저장합니다.
                        put((Enum) object, ((SimpleEnumMessage) object).getText());
                    }
                }

                // 해당 클래스의 위치 및 이름으로 저장합니다.
                extend.put(simpleEnumMessage.getName(), this);
            } else {
                throw new NotEnumException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [열거체.상수]를 통해 메세지를 찾음
     * @param simpleMessage [열거체.상수] 형식의 값
     * @return [열거체.상수]를 통해 찾은 메세지 값
     */
    public static String search(SimpleEnumMessage simpleMessage) {
        // 열거체 인지 확인
        if (simpleMessage.getClass().isEnum()) {
            // 열거체의 위치 및 이름
            String name = simpleMessage.getClass().getName();

            // 위치와 이름으로 상수 값이 저장된 Map을 불러옵니다.
            if (extend.containsKey(name)) {
                EnumMessageMap map = extend.get(name);
                Enum e = (Enum) simpleMessage;

                if (map.containsKey(e)) {
                    return map.get(e);
                }
            }
        }
        return null;
    }
}
