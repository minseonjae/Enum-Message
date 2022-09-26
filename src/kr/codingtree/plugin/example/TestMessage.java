package kr.codingtree.plugin.example;

import kr.codingtree.enummessage.SimpleEnumMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public enum TestMessage implements SimpleEnumMessage {
    MESSAGE_1("message - 1"),
    MESSAGE_2("{0}, {1}, {2}, {3}"),
    MESSAGE_3("message - 3", "message");

    @Getter
    @NonNull
    private String text;
    @Getter
    private String name;
}