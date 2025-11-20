package tests.trie_base;

import org.junit.jupiter.api.Test;
import trie_base.WordK;

import static org.junit.jupiter.api.Assertions.*;

class WordKTest {

    @Test
    void gettersReturnCorrectValues() {
        WordK wk = new WordK("кот", 5);

        assertEquals("кот", wk.getWord());
        assertEquals(5, wk.getK());
    }

    @Test
    void compareToUsesKAscending() {
        WordK w1 = new WordK("a", 1);
        WordK w2 = new WordK("b", 3);
        WordK w3 = new WordK("c", 3);

        assertTrue(w1.compareTo(w2) < 0, "1 < 3 → должен быть отрицательный результат");
        assertTrue(w2.compareTo(w1) > 0, "3 > 1 → должен быть положительный результат");
        assertEquals(0, w2.compareTo(w3), "Одинаковые k → compareTo должен вернуть 0");
    }
}
