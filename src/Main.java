import trie_base.Trie;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)  {
        Trie oo = new Trie();
        oo.insert("говно");
        oo.insert("говницо");
        oo.insert("говнише");
        oo.insert("кот");
        oo.insert("котик");
        System.out.println(oo.getPrefixNode("к"));

    }
}