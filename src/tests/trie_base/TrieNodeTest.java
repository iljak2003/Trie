package tests.trie_base;

import org.junit.jupiter.api.Test;
import trie_base.TrieNode;

import static org.junit.jupiter.api.Assertions.*;

class TrieNodeTest {

    @Test
    void defaultConstructorHasExpectedDefaults() {
        TrieNode node = new TrieNode();

        assertFalse(node.isKey(), "Новый узел не должен быть концом слова");
        assertEquals(0, node.getData(), "Счётчик по умолчанию должен быть 0");
        assertEquals(' ', node.getValue(), "Значение по умолчанию — пробел");

        TrieNode[] children = node.getChildrenArray();
        assertNotNull(children, "Массив детей не должен быть null");
        assertEquals(32, children.length, "Размер массива детей должен быть 32");
        for (TrieNode child : children) {
            assertNull(child, "Все дети по умолчанию должны быть null");
        }
    }

    @Test
    void letterInNumberMapsRussianLettersStartingFromA() {
        TrieNode node = new TrieNode();

        int indexA = node.letterInNumber('а');
        int indexB = node.letterInNumber('б');
        int indexYa = node.letterInNumber('я'); // последняя из supportableChars

        assertEquals(0, indexA, "Буква 'а' должна отображаться в индекс 0");
        assertEquals(1, indexB, "Буква 'б' должна отображаться в индекс 1");
        assertTrue(indexYa >= 0 && indexYa < 32, "Индекс 'я' должен попадать в диапазон массива");
    }

    @Test
    void unsupportedLetterThrowsException() {
        TrieNode node = new TrieNode();

        assertThrows(IllegalArgumentException.class,
                () -> node.letterInNumber('x'),
                "Английская буква должна вызывать IllegalArgumentException");
    }

    @Test
    void addChildCreatesChildAndHasChildWorks() {
        TrieNode root = new TrieNode();

        assertFalse(root.hasChild('к'), "До добавления ребёнка hasChild должен быть false");

        root.addChild('к');

        assertTrue(root.hasChild('к'), "После addChild hasChild должен стать true");
        TrieNode child = root.getChild('к');
        assertNotNull(child, "getChild должен вернуть не null");
        assertEquals('к', child.getValue(), "У ребёнка должен быть сохранён символ");
        assertFalse(child.isKey(), "Новый ребёнок не должен быть концом слова");
        assertEquals(0, child.getData(), "Счётчик у нового ребёнка = 0");
    }

    @Test
    void addDataWorksOnlyWhenIsKeyTrue() {
        TrieNode node = new TrieNode();
        node.addChild('к');
        TrieNode child = node.getChild('к');

        // пока не конец слова – счётчик не увеличивается
        assertEquals(0, child.getData());
        child.addData();
        assertEquals(0, child.getData(), "Если isKey=false, addData не должен менять счётчик");

        child.setKey(true);
        assertEquals(0, child.getData(), "После setKey(true) счётчик остаётся 0");
        child.addData();
        assertEquals(1, child.getData(), "После addData счётчик должен увеличиться до 1");
        child.addData();
        assertEquals(2, child.getData(), "Счётчик должен расти при каждом addData()");
    }
}
