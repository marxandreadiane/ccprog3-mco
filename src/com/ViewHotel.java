package com;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class ViewHotel {
    private TemplatePanel background = new TemplatePanel();
    private JButton confirmButton, showLow, showHigh;
    private JPanel lowLevel, highLevel;
    private JButton confirmDate, confirmRoom, confirmReservation;
    private AutoCompleteComboBox date, reservation, room, hotelName;
    private JTextField textName, textRoom, textStandard, textDeluxe, textExecutive, textEarnings;
    

    public ViewHotel(HRSystem hrSystem) {
        new TemplatePanelController(background, hrSystem);

        // Add welcome label to the main panel
        JLabel titleLabel = new JLabel("VIEW HOTEL");
        titleLabel.setFont(new Font("Garamond", Font.PLAIN, 55));
        titleLabel.setBounds(330, 30, 800, 50);  // Adjust position and size as needed
        background.getMainPanel().add(titleLabel);

        // Hotel
        JLabel promptHotel = new JLabel("Name of the Hotel: ");
        promptHotel.setFont(new Font("Garamond", Font.PLAIN, 20));
        promptHotel.setBounds(150, 140, 800, 40);
        background.getMainPanel().add(promptHotel);

        hotelName = createAutoBox(hrSystem.getHotelNames(), 18);
        hotelName.setBounds(330, 140, 400, 40);
        background.getMainPanel().add(hotelName);

        confirmButton = createButton(0);
        confirmButton.setFont(new Font("Garamond", Font.BOLD, 18));
        confirmButton.setBounds(750, 140, 70, 40);
        background.getMainPanel().add(confirmButton);

        showLow = createButton(0);
        showLow.setText("Show Low-Level Information");
        showLow.setFont(new Font("Garamond", Font.BOLD, 12));
        showLow.setBounds(330, 190, 190, 30);
        showLow.setVisible(false);
        background.getMainPanel().add(showLow);

        showHigh = createButton(0);
        showHigh.setText("Show High-Level Information");
        showHigh.setFont(new Font("Garamond", Font.BOLD, 12));
        showHigh.setBounds(540, 190, 190, 30);
        showHigh.setVisible(false);
        background.getMainPanel().add(showHigh);

        // buttons
        confirmDate = createButton(92);
        confirmRoom = createButton(162);
        confirmReservation = createButton(252);
        
    }

    public JPanel lowLevelInfo(Hotel hotel)
    {
        JPanel lowLevel = new JPanel();
        lowLevel.setLayout(null);
        lowLevel.setBounds(50, 260, 470, 300);

        JPanel upperBorder = new JPanel();
        upperBorder.setLayout(null);
        upperBorder.setBackground(new Color(44, 44, 44));
        upperBorder.setBounds(0, 0, 470, 40);
        lowLevel.add(upperBorder);

        JLabel promptHotel = new JLabel("LOW-LEVEL INFORMATION");
        promptHotel.setFont(new Font("Garamond", Font.PLAIN, 16));
        promptHotel.setForeground(Color.WHITE);
        promptHotel.setBounds(130, 0, 300, 40);
        upperBorder.add(promptHotel);

        JLabel promptDate = infoLabel("Check available and booked rooms", 15, 50);
        promptDate.setBounds(25, 50, 300, 40);
        lowLevel.add(promptDate);

        JLabel textDate = infoLabel("Select Date:", 15, 50);
        textDate.setBounds(70, 80, 100, 40);
        lowLevel.add(textDate);

        ArrayList<String> list = new ArrayList<>();
        
        for (int i = 1; i < 31; i++)
        {
            list.add(Integer.toString(i));
        }
        date = createAutoBox(list, 14);
        date.setBounds(220, 92, 120, 20);
        lowLevel.add(date);

        lowLevel.add(confirmDate);

        JLabel promptRoom = infoLabel("Check room information", 15, 120);
        promptRoom.setBounds(25, 120, 300, 40);
        lowLevel.add(promptRoom);

        JLabel textRoom = infoLabel("Select Room:", 15, 50);
        textRoom.setBounds(70, 150, 100, 40);
        lowLevel.add(textRoom);

        room = createAutoBox(hotel.getRoomList(), 14);
        room.setBounds(220, 162, 120, 20);
        lowLevel.add(room);

        lowLevel.add(confirmRoom);

        JLabel promptReservation = infoLabel("Check reservation information", 15, 190);
        promptReservation.setBounds(25, 190, 300, 40);
        lowLevel.add(promptReservation);

        JLabel textReservation = infoLabel("Select Reservation:", 15, 50);
        textReservation.setBounds(70, 220, 200, 40);
        lowLevel.add(textReservation);

        reservation = createAutoBox(hotel.getReservationString(), 14);
        reservation.setBounds(70, 252, 270, 20);
        lowLevel.add(reservation);

        lowLevel.add(confirmReservation);

        return lowLevel;
    }

    public JButton createButton(int yAxis)
    {
        JButton button = new JButton("OK");
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Garamond", Font.BOLD, 14));
        button.setBounds(380, yAxis, 55, 20);
        button.setFocusable(false);

        return button;
    }

    public JPanel highLevelInfo(Hotel hotel)
    {
        JPanel highLevel = new JPanel();
        highLevel.setLayout(null);
        highLevel.setBounds(540, 260, 380, 300);

        JPanel upperBorder = new JPanel();
        upperBorder.setLayout(null);
        upperBorder.setBackground(new Color(44, 44, 44));
        upperBorder.setBounds(0, 0, 630, 40);
        highLevel.add(upperBorder);

        JLabel promptHotel = new JLabel("HIGH-LEVEL INFORMATION");
        promptHotel.setFont(new Font("Garamond", Font.PLAIN, 16));
        promptHotel.setForeground(Color.WHITE);
        promptHotel.setBounds(85, 0, 240, 40);
        upperBorder.add(promptHotel);

        highLevel.add(infoLabel("Name of the Hotel:", 14, 50));
        highLevel.add(infoLabel("Number of Rooms:", 14, 90));
        highLevel.add(infoLabel("              Standard", 14, 130));
        highLevel.add(infoLabel("                Deluxe", 14, 160));
        highLevel.add(infoLabel("             Executive", 14, 190));
        highLevel.add(infoLabel("Est. Monthly Earnings:", 14, 230));


        textName = infoText(hotel.getName(), 60, 150);
        highLevel.add(textName);

        textRoom = infoText(Integer.toString(hotel.getNumberOfRooms()), 100, 150);
        highLevel.add(textRoom);

        textStandard = infoText(Integer.toString(hotel.getNumberOfStandardRooms()), 140, 70);
        highLevel.add(textStandard);

        textDeluxe = infoText(Integer.toString(hotel.getNumberOfDeluxeRooms()), 170, 70);
        highLevel.add(textDeluxe);

        textExecutive = infoText(Integer.toString(hotel.getNumberOfExecutiveRooms()), 200, 70);
        highLevel.add(textExecutive);

        textEarnings = infoText(Double.toString(hotel.getTotalReservationEarnings()), 240, 150);
        highLevel.add(textEarnings);

        return highLevel;
    }  

    public JFrame dateFrame(String date, Hotel hotel)
    {
        int nDate = Integer.parseInt(date);
        
        JFrame frame = new JFrame("Date Availability");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel dateLabel = infoLabel("SELECTED DATE: ", 18, 50);
        dateLabel.setBounds(60, 0, 300, 100);
        panel.add(dateLabel);

        JTextField dateField = infoText(date, 18, 50);
        dateField.setFont(new Font("Garamond", Font.PLAIN, 18));
        dateField.setBounds(280, 40, 50, 20);
        panel.add(dateField);

        JLabel availableLabel = infoLabel("Number of available rooms: ", 18, 50);
        availableLabel.setBounds(60, 80, 300, 100);
        panel.add(availableLabel);

        JTextField availField = infoText(Integer.toString(hotel.getAvailableRooms(nDate)), 18, 50);
        availField.setFont(new Font("Garamond", Font.PLAIN, 18));
        availField.setBounds(280, 120, 50, 20);
        panel.add(availField);

        JLabel bookedLabel = infoLabel("Number of booked rooms: ", 18, 50);
        bookedLabel.setBounds(60, 130, 300, 100);
        panel.add(bookedLabel);

        JTextField bookedField = infoText(Integer.toString(hotel.getBookedRooms(nDate)), 18, 50);
        bookedField.setFont(new Font("Garamond", Font.PLAIN, 18));
        bookedField.setBounds(280, 170, 50, 20);
        panel.add(bookedField);


        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JFrame roomFrame(Hotel hotel, Room room)
    {
        JFrame frame = new JFrame("Room Information");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel nameLabel = infoLabel("Name of Room: ", 18, 50);
        nameLabel.setBounds(60, -20, 300, 100);
        panel.add(nameLabel);

        JTextField nameField = infoText(room.getName(), 18, 50);
        nameField.setFont(new Font("Garamond", Font.PLAIN, 18));
        nameField.setBounds(200, 20, 130, 20);
        panel.add(nameField);

        JLabel typeLabel = infoLabel("Type of Room: ", 18, 50);
        typeLabel.setBounds(60, 20, 300, 100);
        panel.add(typeLabel);

        JTextField typeField = infoText(hotel.getRoomType(room), 18, 50);
        typeField.setFont(new Font("Garamond", Font.PLAIN, 18));
        typeField.setBounds(200, 60, 130, 20);
        panel.add(typeField);

        JLabel priceLabel = infoLabel("Price of the room: ", 18, 50);
        priceLabel.setBounds(60, 60, 300, 100);
        panel.add(priceLabel);

        JTextField priceField = infoText(Double.toString(hotel.getRoomPrice(room)), 18, 50);
        priceField.setFont(new Font("Garamond", Font.PLAIN, 18));
        priceField.setBounds(210, 100, 120, 20);
        panel.add(priceField);

        JLabel dateLabel = infoLabel("Available dates: ", 18, 50);
        dateLabel.setBounds(60, 100, 300, 100);
        panel.add(dateLabel);

        JTextArea dateField = new JTextArea(displayAvailableDates(room));
        dateField.setFont(new Font("Garamond", Font.PLAIN, 18));
        dateField.setBounds(60, 170, 270, 60);
        dateField.setLineWrap(true);
        dateField.setWrapStyleWord(true);
        dateField.setOpaque(false); 
        dateField.setEditable(false); 
        panel.add(dateField);


        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public String displayAvailableDates(Room room) 
    {
        String availableDates = new String();
        int i;

        for (i = 1; i < 31; i++)
        {
            if (room.getAvailability(i))
            {
                availableDates += Integer.toString(i) + " ";
            }
        }
        
        return availableDates;
    }

    public JFrame reservationFrame(Hotel hotel, Reservation reservation)
    {
        JFrame frame = new JFrame("Reservation Information");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 208, 200));
        panel.setBounds(0, 0, 400, 300);
        
        JLabel guestLabel = infoLabel("Name of Guest: ", 18, 50);
        guestLabel.setBounds(60, 0, 300, 100);
        panel.add(guestLabel);

        JTextField guestField = infoText(reservation.getGuestName(), 18, 50);
        guestField.setFont(new Font("Garamond", Font.PLAIN, 18));
        guestField.setBounds(200, 40, 130, 20);
        panel.add(guestField);
        
        JLabel nameLabel = infoLabel("Name of Room: ", 18, 50);
        nameLabel.setBounds(60, 40, 300, 100);
        panel.add(nameLabel);

        JTextField nameField = infoText(reservation.getRoom().getName(), 18, 50);
        nameField.setFont(new Font("Garamond", Font.PLAIN, 18));
        nameField.setBounds(200, 80, 130, 20);
        panel.add(nameField);

        JLabel checkLabel = infoLabel("Check-in / Check-out: ", 18, 50);
        checkLabel.setBounds(60, 80, 300, 100);
        panel.add(checkLabel);

        String dates = reservation.getCheckInDate() + " - " + reservation.getCheckOutDate();
        JTextField checkField = infoText(dates, 18, 50);
        checkField.setFont(new Font("Garamond", Font.PLAIN, 18));
        checkField.setBounds(250, 120, 80, 20);
        panel.add(checkField);

        JLabel totalLabel = infoLabel("Total price: ", 18, 50);
        totalLabel.setBounds(60, 120, 300, 100);
        panel.add(totalLabel);

        Double totalPrice = reservation.getTotalPrice();
        JTextField totalField = infoText(Double.toString(totalPrice), 18, 50);
        totalField.setFont(new Font("Garamond", Font.PLAIN, 18));
        totalField.setBounds(200, 160, 130, 20);
        panel.add(totalField);

        JLabel priceLabel = infoLabel("Price breakdown: ", 18, 50);
        priceLabel.setBounds(60, 160, 300, 100);
        panel.add(priceLabel);

        AutoCompleteComboBox breakdown = createAutoBox(reservation.getBreakdown(hotel, reservation.getRoom()), 18);
        breakdown.setFont(new Font("Garamond", Font.PLAIN, 18));
        breakdown.setBounds(200, 200, 130, 20);
        panel.add(breakdown);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        return frame;
    }
    
    public AutoCompleteComboBox createAutoBox(ArrayList<String> list, int fontSize)
    {
        AutoCompleteComboBox comboBox = new AutoCompleteComboBox(list);
        comboBox.setBackground(Color.GRAY);
        comboBox.setFont(new Font("Garamond", Font.PLAIN, fontSize));
        comboBox.setVisible(true);

        return comboBox;
    }

    public JTextField infoText(String text, int yAxis, int width)
    {
        JTextField textSample = new JTextField(text);
        textSample.setFont(new Font("Garamond", Font.PLAIN, 14));
        textSample.setBounds(200, yAxis, width, 20);
        textSample.setFocusable(false);

        return textSample;
    }

    public JLabel infoLabel(String text, int fontSize, int yAxis)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Garamond", Font.PLAIN, fontSize));
        label.setForeground(Color.BLACK);
        label.setBounds(25, yAxis, 150, 40);

        return label;
    }

    public void displayMessage(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    // panel getter
    public JPanel getMainPanel() {
        return this.background.getMainPanel(); 
    }

    // Button getters
    public JButton getHighButton()
    {
        return this.showHigh;
    }

    public JButton getLowButton()
    {
        return this.showLow;
    }

    // Panel getters
    public JPanel getHighPanel()
    {
        return this.highLevel;
    }

    public JPanel getLowPanel()
    {
        return this.lowLevel;
    }

    // Button action listener setters
    public void setConfirmButtonListener(ActionListener actionListener) {
        this.confirmButton.addActionListener(actionListener);
    }

    public void setShowLowButtonListener(ActionListener actionListener) {
        this.showLow.addActionListener(actionListener);
    }

    public void setShowHighButtonListener(ActionListener actionListener) {
        this.showHigh.addActionListener(actionListener);
    }

    public void setConfirmDateButtonListener(ActionListener actionListener) {
        this.confirmDate.addActionListener(actionListener);
    }

    public void setConfirmRoomButtonListener(ActionListener actionListener) {
        this.confirmRoom.addActionListener(actionListener);
    }

    public void setConfirmReservationButtonListener(ActionListener actionListener) {
        this.confirmReservation.addActionListener(actionListener);
    }

    // ComboBox getters
    public String getDateSelectedItem() {
        return (String) this.date.getSelectedItem();
    }

    public String getReservationSelectedItem() {
        return (String) this.reservation.getSelectedItem();
    }

    public String getRoomSelectedItem() {
        return (String) this.room.getSelectedItem();
    }

    public String getHotelNameSelectedItem() {
        return (String) this.hotelName.getSelectedItem();
    }

    // TextField getters
    public String getTextName() {
        return this.textName.getText();
    }

    public String getTextRoom() {
        return this.textRoom.getText();
    }

    public String getTextStandard() {
        return this.textStandard.getText();
    }

    public String getTextDeluxe() {
        return this.textDeluxe.getText();
    }

    public String getTextExecutive() {
        return this.textExecutive.getText();
    }

    public String getTextEarnings() {
        return this.textEarnings.getText();
    }

}
