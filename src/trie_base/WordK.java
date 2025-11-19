package trie_base;

public class WordK implements Comparable<WordK>{
    private String word;
    private int k;

    public WordK(String word, int k) {
        this.word = word;
        this.k = k;
    }

    public int getK(){
        return k;
    }

    public String getWord(){
        return word;
    }

    @Override
    public int compareTo(WordK o) {
        return Integer.compare(this.k, o.k);
    }
}
