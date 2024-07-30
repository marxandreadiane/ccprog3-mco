package com;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class AutoCompleteComboBox extends JComboBox<String> {
    private List<String> list;
    private boolean isFiltering = false; 

    public AutoCompleteComboBox(List<String> list) {
        super();
        this.list = new ArrayList<>(list);
        setEditable(true);
        JTextComponent editor = (JTextComponent) getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!isFiltering) { 
                    String input = editor.getText();
                    filterList(input);
                    showPopup();
                    if (e.getKeyCode() == KeyEvent.VK_ENTER && getSelectedItem() != null) {
                        editor.setText(getSelectedItem().toString());
                    }
                }
            }
        });
    }

    private void filterList(String input) {
        isFiltering = true;
        List<String> filteredList = new ArrayList<>();
        boolean foundMatch = false;
        for (String entry : list) {
            if (entry.toLowerCase().startsWith(input.toLowerCase())) {
                filteredList.add(entry);
                foundMatch = true;
            }
        }
        if (!foundMatch) {
            filteredList.add(input); 
        }
        DefaultComboBoxModel<String> newList = new DefaultComboBoxModel<>(filteredList.toArray(new String[0]));
        setModel(newList);
        if (!filteredList.isEmpty()) {
            setSelectedItem(input);
        }
        JTextComponent editor = (JTextComponent) getEditor().getEditorComponent();
        editor.setCaretPosition(editor.getText().length());
        isFiltering = false;
    }

    public void removeEntry(String input)
    {
        list.remove(input);
    }
}
