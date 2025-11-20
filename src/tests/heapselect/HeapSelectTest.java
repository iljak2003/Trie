package tests.heapselect;

import heapselect.HeapSelect;
import org.junit.jupiter.api.Test;
import trie_base.WordK;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeapSelectTest {

    @Test
    void topKReturnsNullForEmptyOrInvalidInput() {
        List<WordK> empty = new ArrayList<>();

        assertNull(HeapSelect.topK(null, 3), "При null-списке метод должен вернуть null");
        assertNull(HeapSelect.topK(empty, 3), "При пустом списке метод должен вернуть null");
        assertNull(HeapSelect.topK(empty, 0), "При k<=0 метод должен вернуть null");
    }

    @Test
    void topKReturnsCorrectTop3ByFrequency() {
        List<WordK> list = new ArrayList<>();
        list.add(new WordK("кот",      5));
        list.add(new WordK("котик",   12));
        list.add(new WordK("компот",   3));
        list.add(new WordK("кошка",    7));
        list.add(new WordK("ком",      7));
        list.add(new WordK("кит",      1));

        int k = 3;
        List<WordK> top = HeapSelect.topK(list, k);

        assertNotNull(top, "Для корректных данных результат не должен быть null");
        assertEquals(3, top.size(), "Размер результата должен быть ровно k");

        // Частоты должны быть 12, 7, 7 (порядок двух семёрок не важен)
        int max = top.get(0).getK();
        assertEquals(12, max, "Первый элемент должен иметь максимальную частоту (12)");

        int minFreq = Integer.MAX_VALUE;
        for (WordK wk : top) {
            if (wk.getK() < minFreq) {
                minFreq = wk.getK();
            }
        }
        assertEquals(7, minFreq, "Минимальная частота в топе-3 должна быть 7");
    }

    @Test
    void topKWithKGreaterThanListSizeReturnsAllSorted() {
        List<WordK> list = new ArrayList<>();
        list.add(new WordK("a", 1));
        list.add(new WordK("b", 5));
        list.add(new WordK("c", 3));

        List<WordK> top = HeapSelect.topK(list, 10);

        assertNotNull(top);
        assertEquals(3, top.size(), "Если k больше размера списка, должны вернуться все элементы");

        assertEquals(5, top.get(0).getK());
        assertEquals(3, top.get(1).getK());
        assertEquals(1, top.get(2).getK());
    }
}
