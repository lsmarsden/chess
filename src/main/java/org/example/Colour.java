package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Colour {
    WHITE('w'), BLACK('b');

    private final char symbol;
}
