import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class ManageHotel {
    private TemplatePanel background = new TemplatePanel();
    private JButton changeName, addRoom, removeRoom, updatePrice, removeReservation, removeHotel, datePrice;
    private JTextField changeNameField, addRoomStandard, addRoomDeluxe, addRoomExecutive, updatePriceField, modifyDate, modifyPrice;
    private JButton changeNameButton, addRoomButton, removeRoomButton, updatePriceButton, removeReservationButton, removeHotelButton, cancelRemovalButton, datePriceButton;
    private AutoCompleteComboBox hotelName, removeRoomField, guestNameField, reservationDetailsField;

    public ManageHotel(HRSystem hrSystem) {
        new TemplatePanelController(background, hrSystem);
        // Add welcome label to the main panel
        JLabel titleLabel = new JLabel("MANAGE HOTEL");
        titleLabel.setFont(new Font("Garamond", Font.PLAIN, 55));
        titleLabel.setBounds(280, 30, 800, 50);  // Adjust position and size as needed
        background.getMainPanel().add(titleLabel);

        // Hotel
        JLabel promptHotel = new JLabel("Name of the Hotel: ");
        promptHotel.setFont(new Font("Garamond", Font.PLAIN, 20));
        promptHotel.setBounds(200, 140, 800, 40);
        background.getMainPanel().add(promptHotel);

        hotelName = createAutoBox(hrSystem.getHotelNames(), 18);
        hotelName.setBounds(360, 140, 400, 40);
        background.getMainPanel().add(hotelName);

        changeName = manageButton("CHANGE NAME", 180, 240);
        background.getMainPanel().add(changeName);

        addRoom = manageButton("ADD ROOM(S)", 530, 240);
        background.getMainPanel().add(addRoom);

        removeRoom = manageButton("REMOVE ROOM(S)", 180, 340);
        background.getMainPanel().add(removeRoom);

        updatePrice = manageButton("UPDATE PRICE", 530, 340);
        background.getMainPanel().add(updatePrice);

        removeReservation = manageButton("REMOVE RESERVATION", 180, 440);
        background.getMainPanel().add(removeReservation);

        removeHotel = manageButton("REMOVE HOTEL", 530, 440);
        background.getMainPanel().add(removeHotel);

        datePrice = manageButton("MODIFY DATE-PRICE", 350, 540);
        background.getMainPanel().add(datePrice);

        changeNameButton = new JButton("CHANGE");
        addRoomButton = new JButton("ADD");
        removeReservationButton = new JButton("REMOVE");
        updatePriceButton = new JButton("UPDATE");
        removeHotelButton = new JButton("REMOVE");
        cancelRemovalButton = new JButton("CANCEL");
        removeRoomButton = new JButton("REMOVE ROOM");
        datePriceButton = new JButton("MODIFY");
    }

    public JFrame changeName()
    {
        JFrame frame = new JFrame("Change Hotel Name");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel label = new JLabel("New Hotel Name:");
        label.setBounds(130, 30, 150, 40);
        label.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label);

        changeNameField = new JTextField();
        changeNameField.setFont(new Font("Garamond", Font.PLAIN, 18));
        changeNameField.setBounds(50, 100, 300, 40);
        panel.add(changeNameField);

        changeNameButton.setFocusable(false);
        changeNameButton.setBackground(new Color(72, 72, 72));
        changeNameButton.setForeground(Color.WHITE);
        changeNameButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        changeNameButton.setBounds(100, 180, 175, 40);
        panel.add(changeNameButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JFrame addRoom()
    {
        JFrame frame = new JFrame("Add Room(s)");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel label1 = new JLabel("Number of Standard Rooms: ");
        label1.setBounds(30, 30, 250, 40);
        label1.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label1);

        JLabel label2 = new JLabel("Number of Deluxe Rooms: ");
        label2.setBounds(30, 70, 250, 40);
        label2.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label2);

        JLabel label3 = new JLabel("Number of Executive Rooms: ");
        label3.setBounds(30, 110, 250, 40);
        label3.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label3);

        addRoomStandard = new JTextField("0");
        addRoomStandard.setFont(new Font("Garamond", Font.PLAIN, 18));
        addRoomStandard.setBounds(300, 32, 50, 30);
        panel.add(addRoomStandard);

        addRoomDeluxe = new JTextField("0");
        addRoomDeluxe.setFont(new Font("Garamond", Font.PLAIN, 18));
        addRoomDeluxe.setBounds(300, 72, 50, 30);
        panel.add(addRoomDeluxe);

        addRoomExecutive = new JTextField("0");
        addRoomExecutive.setFont(new Font("Garamond", Font.PLAIN, 18));
        addRoomExecutive.setBounds(300, 112, 50, 30);
        panel.add(addRoomExecutive);

        addRoomButton.setFocusable(false);
        addRoomButton.setBackground(new Color(72, 72, 72));
        addRoomButton.setForeground(Color.WHITE);
        addRoomButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        addRoomButton.setBounds(100, 180, 175, 40);
        panel.add(addRoomButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JFrame removeRoom(Hotel hotel)
    {
        JFrame frame = new JFrame("Remove Room");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel label = new JLabel("Select Room:");
        label.setBounds(150, 30, 150, 40);
        label.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label);

        removeRoomField = createAutoBox(hotel.getRoomList(), 18);
        removeRoomField.setBounds(50, 90, 300, 40);
        panel.add(removeRoomField);

        removeRoomButton.setFocusable(false);
        removeRoomButton.setBackground(new Color(72, 72, 72));
        removeRoomButton.setForeground(Color.WHITE);
        removeRoomButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        removeRoomButton.setBounds(100, 180, 175, 40);
        panel.add(removeRoomButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JFrame removeReservation(Hotel hotel)
    {
        JFrame frame = new JFrame("Remove Reservation");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);

        JLabel label2 = new JLabel("Reservation Details:");
        label2.setBounds(120, 40, 150, 40);
        label2.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label2);

        reservationDetailsField = createAutoBox(hotel.getReservationString(), 18);
        reservationDetailsField.setBounds(50, 80, 300, 30);
        panel.add(reservationDetailsField);

        removeReservationButton.setFocusable(false);
        removeReservationButton.setBackground(new Color(72, 72, 72));
        removeReservationButton.setForeground(Color.WHITE);
        removeReservationButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        removeReservationButton.setBounds(100, 180, 200, 40);
        panel.add(removeReservationButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }
        
    public JFrame updatePrice(Hotel hotel)
    {
        JFrame frame = new JFrame("Update Base Price");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);

        JLabel currentPrice = new JLabel("Current Base Price:");
        currentPrice.setBounds(50, 30, 150, 40);
        currentPrice.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(currentPrice);

        JTextField currentPriceField = new JTextField();
        currentPriceField.setFont(new Font("Garamond", Font.PLAIN, 18));
        currentPriceField.setBounds(220, 30, 130, 40);
        currentPriceField.setText(Double.toString(hotel.getRoomPrice(new Room("Temporary"))));
        currentPriceField.setEditable(false);
        panel.add(currentPriceField);
        
        JLabel label = new JLabel("New Base Price:");
        label.setBounds(50, 100, 150, 40);
        label.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label);

        updatePriceField = new JTextField();
        updatePriceField.setFont(new Font("Garamond", Font.PLAIN, 18));
        updatePriceField.setBounds(220, 100, 130, 40);
        panel.add(updatePriceField);

        updatePriceButton.setFocusable(false);
        updatePriceButton.setBackground(new Color(72, 72, 72));
        updatePriceButton.setForeground(Color.WHITE);
        updatePriceButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        updatePriceButton.setBounds(100, 180, 175, 40);
        panel.add(updatePriceButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JFrame removeHotel()
    {
        JFrame frame = new JFrame("Remove Hotel");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel label = new JLabel("Remove Hotel?");
        label.setBounds(110, 50, 400, 40);
        label.setFont(new Font("Garamond", Font.PLAIN, 28));
        panel.add(label);

        JLabel label1 = new JLabel("This cannot be undone.");
        label1.setBounds(100, 80, 400, 40);
        label1.setFont(new Font("Garamond", Font.PLAIN, 20));
        panel.add(label1);

        cancelRemovalButton.setFocusable(false);
        cancelRemovalButton.setBackground(new Color(72, 72, 72));
        cancelRemovalButton.setForeground(Color.WHITE);
        cancelRemovalButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        cancelRemovalButton.setBounds(50, 180, 125, 40);
        panel.add(cancelRemovalButton);

        removeHotelButton.setFocusable(false);
        removeHotelButton.setBackground(new Color(72, 72, 72));
        removeHotelButton.setForeground(Color.WHITE);
        removeHotelButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        removeHotelButton.setBounds(225, 180, 125, 40); 
        panel.add(removeHotelButton);

        
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }
    
    public JFrame modifyDatePrice()
    {
        JFrame frame = new JFrame("Modify Date-Price");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel label1 = new JLabel("Date: ");
        label1.setBounds(100, 40, 250, 40);
        label1.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label1);

        JLabel label2 = new JLabel("Price (%): ");
        label2.setBounds(100, 100, 250, 40);
        label2.setFont(new Font("Garamond", Font.PLAIN, 18));
        panel.add(label2);

        modifyDate = new JTextField("");
        modifyDate.setFont(new Font("Garamond", Font.PLAIN, 18));
        modifyDate.setBounds(210, 42, 100, 30);
        panel.add(modifyDate);

        modifyPrice = new JTextField("");
        modifyPrice.setFont(new Font("Garamond", Font.PLAIN, 18));
        modifyPrice.setBounds(210, 102, 100, 30);
        panel.add(modifyPrice);

        datePriceButton.setFocusable(false);
        datePriceButton.setBackground(new Color(72, 72, 72));
        datePriceButton.setForeground(Color.WHITE);
        datePriceButton.setFont(new Font("Garamond", Font.PLAIN, 18));
        datePriceButton.setBounds(100, 180, 175, 40);
        panel.add(datePriceButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JButton createButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Garamond", Font.PLAIN, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(220, 208, 200));
        button.setFocusable(false);
        
        return button;
    }

    public AutoCompleteComboBox createAutoBox(ArrayList<String> list, int fontSize)
    {
        AutoCompleteComboBox comboBox = new AutoCompleteComboBox(list);
        comboBox.setBackground(Color.GRAY);
        comboBox.setFont(new Font("Garamond", Font.PLAIN, fontSize));
        comboBox.setVisible(true);

        return comboBox;
    }

    public JButton manageButton(String text, int xAxis, int yAxis)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Garamond", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(44, 44, 44));
        button.setFocusable(false);
        button.setBounds(xAxis, yAxis, 250, 50);

        return button;
    }

    public void displayMessage(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public boolean showYesNoDialog() {
        int response = JOptionPane.showConfirmDialog(null, "Confirm modification? If you click yes, the action cannot be undone.", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        return response == JOptionPane.YES_OPTION;
    }

    public boolean showYesNoDialog(String message) {
        int response = JOptionPane.showConfirmDialog(null, message, "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        return response == JOptionPane.YES_OPTION;
    }

    // SETTERS AND GETTERS

    // Getter methods for text fields
    public String getChangeNameFieldText() {
        return changeNameField.getText();
    }

    public String getAddRoomStandardText() {
        return addRoomStandard.getText();
    }

    public String getAddRoomDeluxeText() {
        return addRoomDeluxe.getText();
    }

    public String getAddRoomExecutiveText() {
        return addRoomExecutive.getText();
    }

    public String getUpdatePriceFieldText() {
        return updatePriceField.getText();
    }

    public String getModifyDateText() {
        return modifyDate.getText();
    }

    public String getModifyPriceText() {
        return modifyPrice.getText();
    }

    // Getter methods for combo boxes
    public AutoCompleteComboBox getHotelName()
    {
        return this.hotelName;
    }

    public String getHotelNameSelectedItem() {
        return (String) hotelName.getSelectedItem();
    }

    public String getRemoveRoomFieldText() {
        return (String) removeRoomField.getSelectedItem();
    }

    public String getReservationDetailsFieldSelectedItem() {
        return (String) reservationDetailsField.getSelectedItem();
    }
    
    public JFrame getFrame()
    {
        return this.background.getFrame();
    }

    public void clearRemoveRoomField() {
        removeRoomField.setSelectedItem(null);
    }
    
    // Action listener setters
    
    public void setChangeNameListener(ActionListener actionListener) {
        this.changeName.addActionListener(actionListener);
    }
    
    public void setAddRoomListener(ActionListener actionListener) {
        this.addRoom.addActionListener(actionListener);
    }
    
    public void setRemoveRoomListener(ActionListener actionListener) {
        this.removeRoom.addActionListener(actionListener);
    }
    
    public void setUpdatePriceListener(ActionListener actionListener) {
        this.updatePrice.addActionListener(actionListener);
    }
    
    public void setRemoveReservationListener(ActionListener actionListener) {
        this.removeReservation.addActionListener(actionListener);
    }

    public void setRemoveHotelListener(ActionListener actionListener) {
        this.removeHotel.addActionListener(actionListener);
    }
    
    public void setDatePriceListener(ActionListener actionListener) {
        this.datePrice.addActionListener(actionListener);
    }

    public void setChangeNameButtonListener(ActionListener actionListener) {
        this.changeNameButton.addActionListener(actionListener);
    }
    
    public void setAddRoomButtonListener(ActionListener actionListener) {
        this.addRoomButton.addActionListener(actionListener);
    }
    
    public void setRemoveRoomButtonListener(ActionListener actionListener) {
        this.removeRoomButton.addActionListener(actionListener);
    }
    
    public void setUpdatePriceButtonListener(ActionListener actionListener) {
        this.updatePriceButton.addActionListener(actionListener);
    }
    
    public void setRemoveReservationButtonListener(ActionListener actionListener) {
        this.removeReservationButton.addActionListener(actionListener);
    }
    public void setRemoveHotelButtonListener(ActionListener actionListener) {
        this.removeHotelButton.addActionListener(actionListener);
    }
    public void setCancelRemovalButtonListener(ActionListener actionListener) {
        this.cancelRemovalButton.addActionListener(actionListener);
    }

    public void setDatePriceButtonListener(ActionListener actionListener) {
        this.datePriceButton.addActionListener(actionListener);
    }

}
