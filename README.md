# Enum-Message

Bukkit, BungeeCord 지원


### __필요한 파일__ _(이미 포함되어 있음)_
__- Platform-Config__\
__- Spigot__\
__- BungeeCord__\
__- lombok__

## 예제

### TestMessage.java
``` Java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public enum TestMessage implements SimpleEnumMessage {
    MESSAGE_1("message - 1"),
    MESSAGE_2("{0}, {1}, {2}, {3}"),
    MESSAGE_3("message - 3", "message");

    @Getter
    private String text;
}
```

### TestPlugin.java
``` Java
public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        EnumMessage.loadConfig(TestMessage.class, this);
        
        ExampleMessage.MESSAGE_1.console();
        ExampleMessage.MESSAGE_2.console("a", "c", "b", "d");
        ExampleMessage.MESSAGE_3.console();
    }
   
}
```

#

### message-config.yml 1
``` yaml
MESSAGE_1: 'message - 1'
MESSAGE_2: '{0}, {1}, {2}, {3}'
message: 'message - 3'
```

### Result Console 1
```text
message - 1
a, c, b, d
message - 3
```

#

### message-config.yml 2
``` yaml
MESSAGE_1: 'message - 2'
MESSAGE_2: '{0} - {1} - {2} - {3}'
message: 'message - 4'
```

### Result Console 2
```text
message - 2
a - c - b - d
message - 4
```

