package trie_base;

import java.util.Locale;

public class Trie {

    private TrieNode root = new TrieNode();

    public void insert(String str) throws Exception {
        str = str.toLowerCase(Locale.ROOT);
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!node.hasChild(c)){
                node.addChild(c);
            }
            node = node.getChild(c);
        }
        node.setKey(true);
        node.addData();
    }

}
