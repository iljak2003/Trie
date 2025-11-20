package heapselect;

import trie_base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapSelect {
    public static List<WordK> topK(List<WordK> wordKList, int k) {
        if (wordKList == null || wordKList.isEmpty() || k <= 0) {
            return null;
        }

        PriorityQueue<WordK> heap = new PriorityQueue<>();

        for (WordK w : wordKList) {
            if (heap.size() < k) {
                heap.add(w);
            } else {
                WordK min = heap.peek();
                if (min != null && w.getK() > min.getK()) {
                    heap.poll();
                    heap.add(w);
                }
            }
        }

        List<WordK> result = new ArrayList<>(heap);
        result.sort((a, b) -> Integer.compare(b.getK(), a.getK()));

        return result;
    }
}
