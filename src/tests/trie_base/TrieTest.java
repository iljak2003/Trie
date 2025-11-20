package tests.trie_base;

import org.junit.jupiter.api.Test;
import trie_base.Trie;
import trie_base.WordK;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void insertSingleWordAndFindByFullPrefix() {
        Trie trie = new Trie();
        trie.insert("кот");

        List<WordK> result = trie.getPrefixNode("кот");

        assertNotNull(result, "Для существующего слова должен возвращаться не null");
        assertEquals(1, result.size(), "Должно быть ровно одно слово");
        WordK wk = result.get(0);
        assertEquals("кот", wk.getWord());
        assertEquals(1, wk.getK(), "Частота слова 'кот' должна быть 1");
    }

    @Test
    void insertSameWordTwiceIncrementsCounter() {
        Trie trie = new Trie();
        trie.insert("кот");
        trie.insert("кот");

        List<WordK> result = trie.getPrefixNode("кот");
        assertNotNull(result);
        assertEquals(1, result.size());
        WordK wk = result.get(0);
        assertEquals("кот", wk.getWord());
        assertEquals(2, wk.getK(), "После двух вставок частота должна быть 2");
    }

    @Test
    void differentWordsWithSamePrefixAreAllReturned() {
        Trie trie = new Trie();
        trie.insert("кот");
        trie.insert("кот");
        trie.insert("котик");
        trie.insert("кошка");

        List<WordK> result = trie.getPrefixNode("ко");

        assertNotNull(result, "Существующий префикс должен возвращать список, а не null");
        assertEquals(3, result.size(), "По префиксу 'ко' должно быть три слова");

        Integer kotFreq = null;
        Integer kotikFreq = null;
        Integer koshkaFreq = null;

        for (WordK wk : result) {
            switch (wk.getWord()) {
                case "кот" -> kotFreq = wk.getK();
                case "котик" -> kotikFreq = wk.getK();
                case "кошка" -> koshkaFreq = wk.getK();
            }
        }

        assertEquals(2, kotFreq);
        assertEquals(1, kotikFreq);
        assertEquals(1, koshkaFreq);
    }

    @Test
    void getPrefixNodeReturnsNullWhenPrefixNotFound() {
        Trie trie = new Trie();
        trie.insert("кот");
        trie.insert("кошка");

        List<WordK> result = trie.getPrefixNode("собака");

        assertNull(result, "Для отсутствующего префикса метод сейчас возвращает null");
    }

    @Test
    void insertIsCaseInsensitive() {
        Trie trie = new Trie();
        trie.insert("КоТ");
        trie.insert("кот");

        List<WordK> result = trie.getPrefixNode("кот");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getK(), "Слово в разных регистрах должно считаться одним и тем же");
    }
}
