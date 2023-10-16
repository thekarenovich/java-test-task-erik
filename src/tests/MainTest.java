package tests;

import org.junit.jupiter.api.Test;
import solutions.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {
    @Test
    public void testArabicAddition() throws Exception {
        String input = "1 + 2";
        String expected = "3";
        String result = Main.calc(input);
        assertEquals(expected, result);
    }

    @Test
    public void testRomanDivision() throws Exception {
        String input = "VI / III";
        String expected = "II";
        String result = Main.calc(input);
        assertEquals(expected, result);
    }

    @Test
    public void testRomanSubtractionException() {
        String input = "I - II";
        assertThrows(Exception.class, () -> Main.calc(input));
    }

    @Test
    public void testMixedInputException() {
        String input = "I + 1";
        assertThrows(Exception.class, () -> Main.calc(input));
    }

    @Test
    public void testInvalidInputException() {
        String input = "1";
        assertThrows(Exception.class, () -> Main.calc(input));
    }

    @Test
    public void testInvalidOperationException() {
        String input = "1 + 2 + 3";
        assertThrows(Exception.class, () -> Main.calc(input));
    }
}
