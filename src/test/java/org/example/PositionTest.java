package org.example;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@Epic("Chess Game")
@Feature("Position Validation")
class PositionTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            -1, 5
            0, -1
            8, 4
            4, 8
            """)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Validate position boundaries")
    @Description("Ensure that creating a position outside the valid board range throws an exception.")
    void shouldThrowWhenOutOfBounds(int x, int y) {
        // given
        Allure.parameter("x", x);
        Allure.parameter("y", y);

        // exercise & verify
        assertThatIllegalArgumentException().isThrownBy(() -> new Position(x, y))
                .withMessage("Position out of bounds");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validate position boundaries")
    @Description("Ensure that creating a position within the valid board range does not throw an exception.")
    void shouldNotThrowWhenInBounds() {
        // exercise & verify
        assertThatNoException().isThrownBy(() -> new Position(4, 4));
    }
}