import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class AutoCompleteComboBox extends JComboBox<String> {
    private final List<String> items;
    private boolean isFiltering = false; 

    public AutoCompleteComboBox(List<String> items) {
        super();
        this.items = new ArrayList<>(items);
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
        List<String> filteredItems = new ArrayList<>();
        boolean foundMatch = false;
        for (String item : items) {
            if (item.toLowerCase().startsWith(input.toLowerCase())) {
                filteredItems.add(item);
                foundMatch = true;
            }
        }
        if (!foundMatch) {
            filteredItems.add(input); 
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(filteredItems.toArray(new String[0]));
        setModel(model);
        if (!filteredItems.isEmpty()) {
            setSelectedItem(input);
        }
        JTextComponent editor = (JTextComponent) getEditor().getEditorComponent();
        editor.setCaretPosition(editor.getText().length());
        isFiltering = false;
    }
}
