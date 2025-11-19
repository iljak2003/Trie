package trie_base;

public class TrieNode {
    private boolean is_key;
    private int data;
    private char value;
    private TrieNode[] children = new TrieNode[32];
    private final String supportableChars = "абвгдежзийклмнопрстуфхцчшщъыьэюя";

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

    public char getValue() {
        return value;
    }

    public int letterInNumber(char letter) {
        checker(letter);
        return letter - 'а';
    }

    private void checker(char value) {
        boolean flag = false;
        int i = 0;
        while (i < supportableChars.length()) {
            if (value == supportableChars.charAt(i)) {
                flag = true;
            }
            i++;
        }
        if (!flag) {
            throw new IllegalArgumentException("Not supportable symbol");
        }
    }

    public void addChild(char value) {
        int indexOfValue = letterInNumber(value);
        if (!hasChild(value)) {
            children[indexOfValue] = new TrieNode(value, 0, false);
        }
    }

    public TrieNode getChild(char value) {
        int indexOfValue = letterInNumber(value);
        return children[indexOfValue];
    }

    public boolean hasChild(char value) {
        int indexOfValue = letterInNumber(value);
        return children[indexOfValue] != null;
    }


    public TrieNode[] getChildrenArray(){
        return children;
    }

}
