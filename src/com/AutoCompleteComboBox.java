package com;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * The AutoCompleteComboBox class extends JComboBox and provides an auto-complete feature 
 * by filtering the list of items based on user input.
 */
public class AutoCompleteComboBox extends JComboBox<String> {
    private List<String> list;
    private boolean isFiltering = false; 


    /**
     * Constructs an AutoCompleteComboBox with the specified list of items.
     * @param list the list of items to populate the combo box
     */
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

    /**
     * Filters the list of items based on the user input and updates the combo box model.
     * @param input the user input used to filter the list
     */
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

    /**
     * Removes an entry from the list of items.
     * @param input the entry to be removed from the list
     */
    public void removeEntry(String input)
    {
        list.remove(input);
    }
}
