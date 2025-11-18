package trie_base;

public class TrieNode {
    boolean is_key;
    int data;
    char value;
    TrieNode[] children = new TrieNode[32];

    TrieNode(char value, int data, boolean is_key) {
        this.is_key = is_key;
        this.data = data;
        this.value = value;
    }

    TrieNode() {
        this.is_key = false;
        this.data = 0;
        this.value = ' ';
    }

    public boolean isKey() {
        return is_key;
    }

    public void setKey(boolean key) {
        this.is_key = key;
    }

    public int getData() {
        if (isKey()) {
            return data;
        }
        return 0;
    }

    public void addData() {
        if (isKey()) {
            data += 1;
        }
    }

    public char getValue(){
        return value;
    }

    private int letterInNumber(char letter) {
        return letter - 'а';
    }

    public TrieNode addChild(char value) {
        int indexOfValue = letterInNumber(value);
        if (children[indexOfValue] == null) {
            children[indexOfValue] = new TrieNode(value, 0, false);
            return children[indexOfValue];
        }
        return children[indexOfValue];
    }

    public TrieNode getChild(char value) {
        int indexOfValue = letterInNumber(value);
        return children[indexOfValue];
    }

    public boolean hasChild(char value) {
        int indexOfValue = letterInNumber(value);
        return children[indexOfValue] != null;
    }

}
