import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFSMTest {

    @ParameterizedTest(name = "Вхідний рядок: ''{0}'', Очікуваний стан: ''{1}''")
    @CsvSource({
            "abcTESTabc, F",
            "abcTES, 3",
            "T, 1",
            "TE, 2",
            "TES, 3",
            "TEST, F",
            "abcT, 1",
            "abcTE, 2",
            "NOTHING, S",
            // Кейси, де оригінальний автомат працює неправильно (хибний результат за схемою)
            "TTEST, 1",
            "TETEST, 1"
    })
    void testOriginalFSM(String inputString, String expectedState) {
        TestFSM fsm = new TestFSM(false);
        assertEquals(expectedState, fsm.processString(inputString));
    }

    @ParameterizedTest(name = "Вхідний рядок: ''{0}'', Очікуваний стан: ''{1}''")
    @CsvSource({
            "abcTESTabc, F",
            "abcTES, 3",
            "T, 1",
            "TE, 2",
            "TES, 3",
            "TEST, F",
            // Виправлені крайові випадки (тепер автомат знаходить тут TEST)
            "TTEST, F",
            "TETEST, F",
            "TETESTEST, F",
            "TTTTEST, F",
            // Випадки, коли слова повністю немає
            "TTE, 2",
            "TETES, 3"
    })
    void testFixedFSM(String inputString, String expectedState) {
        TestFSM fsm = new TestFSM(true);
        assertEquals(expectedState, fsm.processString(inputString));
    }
}