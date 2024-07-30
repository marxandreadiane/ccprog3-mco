package com;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class BookHotel {
    private TemplatePanel background = new TemplatePanel();
    private JButton confirmButton, bookButton, confirmDates;
    private JTextField inputName, checkIn, checkOut, applyVoucher;
    private JPanel miniPanel;
    private AutoCompleteComboBox room, hotelName;
    private HRSystem hrSystem;

    public BookHotel(HRSystem hrSystem) {
        this.hrSystem = hrSystem;
        new TemplatePanelController(background, hrSystem);
        
        JLabel titleLabel = createLabel("BOOK HOTEL", 55, 330, 30);
        titleLabel.setBounds(330, 30, 600, 50);
        background.getMainPanel().add(titleLabel);

        // Hotel
        background.getMainPanel().add(createLabel("Name of the Hotel:", 20, 150, 140));

        hotelName = createAutoBox(hrSystem.getHotelNames(), 18);
        hotelName.setBounds(330, 140, 400, 40);
        background.getMainPanel().add(hotelName);

        confirmButton = new JButton("OK");
        confirmButton.setBackground(Color.GRAY);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Garamond", Font.BOLD, 18));
        confirmButton.setBounds(750, 140, 70, 40);
        confirmButton.setFocusable(false);
        background.getMainPanel().add(confirmButton);

        // Create button
        bookButton = new JButton("BOOK");
        bookButton.setBackground(new Color(44, 44, 44));
        bookButton.setFont(new Font("Garamond", Font.BOLD, 20));
        bookButton.setForeground(Color.WHITE);
        bookButton.setBounds(400, 550, 200, 40);
        bookButton.setFocusable(false);
        background.getMainPanel().add(bookButton);

        confirmDates = new JButton("Confirm Dates");
    }

    public void otherInformation(Hotel hotel) {
        miniPanel = new JPanel();
        miniPanel.setBackground(new Color(210, 198, 190));
        miniPanel.setLayout(null);
        miniPanel.setBounds(100, 240, 800, 270);
        background.getMainPanel().add(miniPanel);

        // Name of the guest
        miniPanel.add(createLabel("Name of guest:", 18, 50, 20));

        inputName = new JTextField();
        inputName.setBounds(200, 25, 300, 30);
        inputName.setFont(new Font("Garamond", Font.PLAIN, 14));
        miniPanel.add(inputName);

        // Dates
        miniPanel.add(createLabel("Check-in / Check-out dates:", 18, 50, 90));
        miniPanel.add(createLabel("to", 18, 360, 90));

        checkIn = new JTextField();
        checkIn.setBounds(280, 95, 60, 30);
        checkIn.setFont(new Font("Garamond", Font.PLAIN, 14));
        miniPanel.add(checkIn);

        checkOut = new JTextField();
        checkOut.setBounds(400, 95, 60, 30);
        checkOut.setFont(new Font("Garamond", Font.PLAIN, 14));
        miniPanel.add(checkOut);

        confirmDates.setBackground(Color.GRAY);
        confirmDates.setForeground(Color.WHITE);
        confirmDates.setFont(new Font("Garamond", Font.PLAIN, 14));
        confirmDates.setBounds(550, 95, 130, 30);
        confirmDates.setFocusable(false);
        miniPanel.add(confirmDates);
        
    }

    public void roomVoucher(Hotel hotel) {

        room = createAutoBox(hotel.filterAvailableRooms(Integer.parseInt(getInputCheckIn()), Integer.parseInt(getInputCheckOut())), 14);
        room.setBounds(160, 166, 100, 30);
        miniPanel.add(room);

        miniPanel.add(createLabel("Select room:", 18, 50, 160));
        miniPanel.add(createLabel("Voucher (Optional):", 18, 420, 160));
        miniPanel.add(createLabel("[S - Standard | D - Deluxe | E - Executive]", 12, 50, 200));

        applyVoucher = new JTextField();
        applyVoucher.setBounds(600, 166, 150, 30);
        applyVoucher.setFont(new Font("Garamond", Font.PLAIN, 14));
        miniPanel.add(applyVoucher);

        miniPanel.revalidate();
        miniPanel.repaint();
    
    }

    public JLabel createLabel(String text, int fontSize, int xAxis, int yAxis) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Garamond", Font.PLAIN, fontSize));
        label.setBounds(xAxis, yAxis, 300, 40);
        return label;
    }

    public AutoCompleteComboBox createAutoBox(ArrayList<String> list, int fontSize) {
        AutoCompleteComboBox comboBox = new AutoCompleteComboBox(list);
        comboBox.setBackground(Color.GRAY);
        comboBox.setFont(new Font("Garamond", Font.PLAIN, fontSize));
        comboBox.setVisible(true);
        return comboBox;
    }

    public void clearTextFields()
    {
        inputName.setText("");
        checkIn.setText("");
        checkOut.setText("");
        room.setSelectedItem(null);
        hotelName.setSelectedItem(null);
        applyVoucher.setText("");
    }

    public void displayMessage(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public boolean showYesNoDialog() {
        int response = JOptionPane.showConfirmDialog(null, "Voucher is not applicable. Proceed with the booking?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        return response == JOptionPane.YES_OPTION;
    }

    // Button action listener setters
    public void setConfirmButtonListener(ActionListener actionListener) {
        this.confirmButton.addActionListener(actionListener);
    }

    public void setBookButtonListener(ActionListener actionListener) {
        this.bookButton.addActionListener(actionListener);
    }

    public void setConfirmDatesListener(ActionListener actionListener) {
        this.confirmDates.addActionListener(actionListener);
    }

    // TextField getter
    public String getInputName() {
        return this.inputName.getText();
    }

    public String getInputCheckIn() {
        return this.checkIn.getText();
    }

    public String getInputCheckOut() {
        return this.checkOut.getText();
    }

    // panel getter
    public JPanel getMainPanel() {
        return this.background.getMainPanel();
    }

    // ComboBox getters
    public String getRoomSelectedItem() {
        return (String) this.room.getSelectedItem();
    }

    public String getHotelNameSelectedItem() {
        return (String) this.hotelName.getSelectedItem();
    }

    public JPanel getPanel()
    {
        return this.background.getMainPanel();
    }

    public JPanel getMiniPanel()
    {
        return this.miniPanel;
    }

    public AutoCompleteComboBox getRoomField()
    {
        return room;
    }

    public String getVoucherText()
    {
        return this.applyVoucher.getText();
    }
}
