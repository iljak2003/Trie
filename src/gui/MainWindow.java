package gui;

import heapselect.HeapSelect;
import trie_base.Trie;
import trie_base.WordK;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class MainWindow extends JFrame {

    private Trie trie = new Trie();

    private JTextField topKField;
    private JTextField wordField;
    private JButton addButton;
    private JList<String> suggestionsList;
    private DefaultListModel<String> listModel;

    public MainWindow() {
        super("Trie (автодополнение)");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        setLayout(null);

        JLabel topKLabel = new JLabel("Топ K слов по префиксу:");
        topKLabel.setBounds(20, 10, 40, 25);
        add(topKLabel);

        topKField = new JTextField("1");
        topKField.setBounds(60, 10, 50, 25);
        add(topKField);

        JLabel enterLabel = new JLabel("Введите слово / префикс:");
        enterLabel.setBounds(20, 50, 200, 20);
        add(enterLabel);

        wordField = new JTextField();
        wordField.setBounds(20, 75, 380, 40);
        add(wordField);

        addButton = new JButton("Добавить слово");
        addButton.setBounds(20, 130, 150, 30);
        add(addButton);

        JLabel listLabel = new JLabel("Список топ K слов по префиксу");
        listLabel.setBounds(450, 10, 200, 20);
        add(listLabel);

        listModel = new DefaultListModel<>();
        suggestionsList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(suggestionsList);
        scrollPane.setBounds(450, 35, 400, 400);
        add(scrollPane);

        addButton.addActionListener(e -> {
            String text = wordField.getText();
            if (text != null) {
                text = text.trim();
            }
            if (text != null && !text.isEmpty()) {
                trie.insert(text);
                updateSuggestions();
            }
        });

        wordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        topKField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });
    }

    private void updateSuggestions() {
        String prefix = wordField.getText();
        if (prefix == null) {
            prefix = "";
        }
        prefix = prefix.trim();

        listModel.clear();

        if (prefix.isEmpty()) {
            return;
        }

        int k;
        try {
            String kText = topKField.getText();
            if (kText == null || kText.trim().isEmpty()) {
                k = 1;
            } else {
                k = Integer.parseInt(kText.trim());
            }
        } catch (Exception ex) {
            k = 1;
        }

        if (k <= 0) {
            return;
        }

        List<WordK> all;
        try {
            all = trie.getPrefixNode(prefix);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        if (all == null || all.isEmpty()) {
            return;
        }

        List<WordK> top = HeapSelect.topK(all, k);
        if (top == null || top.isEmpty()) {
            return;
        }

        for (WordK wk : top) {
            if (wk != null) {
                String line = wk.getWord() + " (" + wk.getK() + ")";
                listModel.addElement(line);
            }
        }
    }
}