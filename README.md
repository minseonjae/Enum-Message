# Enum-Message

Bukkit & BungeeCord Library


### __Required List__ _(already included)_
__- Platform-Config__\
__- Spigot__\
__- BungeeCord__\
__- lombok__

## Example
### TestPlugin.java
``` Java

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        MessageAssist.loadConfig(ExampleMessage.class, this);
        
        ExampleMessage.MESSAGE_1.console();
        ExampleMessage.MESSAGE_2.console("a", "c", "b", "d");
        ExampleMessage.MESSAGE_3.console();
    }
   
}
```
### TestMessage.java
``` Java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public enum TestMessage implements EnumMessage {
    MESSAGE_1("message - 1"),
    MESSAGE_2("{0}, {1}, {2}, {3}"),
    MESSAGE_3("message - 3", "message");

    @Getter
    @NonNull
    private String text;
    @Getter
    private String name;
}
```

### Result Console
```text
message - 1
a, c, b, d
message - 3
```

### Saved message-config.yml

``` yaml
MESSAGE_1: 'message - 1'
MESSAGE_2: '{0}, {1}, {2}, {3}'
message: 'message - 3'
```


### Reverse loading of message-config.yml will change the value of the variable.
