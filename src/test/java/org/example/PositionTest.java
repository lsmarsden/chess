package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
            -1, 5
            0, -1
            8, 4
            4, 8
            """)
    void shouldThrowWhenOutOfBounds(int x, int y) {
        // exercise & verify
        assertThatIllegalArgumentException().isThrownBy(() -> new Position(x, y))
                .withMessage("Position out of bounds");
    }

    @Test
    void shouldNotThrowWhenInBounds() {
        // exercise & verify
        assertThatNoException().isThrownBy(() -> new Position(4, 4));
    }
}