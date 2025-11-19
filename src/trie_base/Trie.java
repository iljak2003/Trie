package trie_base;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private TrieNode root = new TrieNode();

    private String wordToLowerCase(String word) {
        return word.toLowerCase();
    }

    public void insert(String str) {
        str = wordToLowerCase(str);
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            char currentLetter = str.charAt(i);
            if (!node.hasChild(currentLetter)) {
                node.addChild(currentLetter);
            }
            node = node.getChild(currentLetter);
        }
        node.setKey(true);
        node.addData();
    }

    public List<String> getPrefixNode(String prefix) {
        prefix = wordToLowerCase(prefix);
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentLetter = prefix.charAt(i);
            if (!node.hasChild(currentLetter)) {
                return null;
            }
            node = node.getChild(currentLetter);
        }
        List<String> qwe = new ArrayList<>();
        getByPrefix(node, qwe, prefix);
        return qwe;
    }

    private void getByPrefix(TrieNode node, List<String> result, String path) {
        TrieNode[] children = node.getChildrenArray();
        if (node.isKey()) {
            result.add(path);
        }

        for (TrieNode current : children) {
            if (current != null) {
                getByPrefix(current, result, path + current.getValue());
            }
        }
    }
}


