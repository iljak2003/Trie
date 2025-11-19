import heapselect.HeapSelect;
import trie_base.WordK;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)  {
        List<WordK> list = new ArrayList<>();
        list.add(new WordK("кот",      5));
        list.add(new WordK("котик",   12));
        list.add(new WordK("компот",   3));
        list.add(new WordK("кошка",    7));
        list.add(new WordK("ком",      7));
        list.add(new WordK("кит",      1));

        int k = 3; // хотим топ-3 по частоте

        List<WordK> top = HeapSelect.topK(list, k);

        System.out.println("Top-" + k + " слов:");
        for (WordK w : top) {
            System.out.println(w.getWord() + " : " + w.getK());
        }
    }
}