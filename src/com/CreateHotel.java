package com;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateHotel {
    private TemplatePanel background = new TemplatePanel();
    private JButton confirmButton;
    private JTextField hotelName, standardRoom, deluxeRoom, executiveRoom;

    public CreateHotel(HRSystem hrSystem) {
        new TemplatePanelController(background, hrSystem);

        JPanel miniPanel = new JPanel();
        miniPanel.setBackground(new Color(72, 72, 72, 90));
        miniPanel.setLayout(null);
        miniPanel.setBounds(100, 150, 800, 310);
        background.getMainPanel().add(miniPanel); // for design purposes only

        // Labels
        JLabel titleLabel = createLabel("CREATE HOTEL", 55, 290, 30);
        titleLabel.setBounds(290, 30, 800, 50); 

        createLabel("Name of the hotel:", 20, 140, 180);
        createLabel("Number of rooms:", 20, 140, 240);
        createLabel("Standard:", 18, 430, 270);
        createLabel("Deluxe:", 18, 430, 320);
        createLabel("Executive:", 18, 430, 370);

        hotelName = createTextField(320, 180, 520);
        standardRoom = createTextField(530, 270, 50);
        standardRoom.setText("0");
        deluxeRoom = createTextField(530, 320, 50);
        deluxeRoom.setText("0");
        executiveRoom = createTextField(530, 370, 50);
        executiveRoom.setText("0");

        // create
        confirmButton = new JButton("CREATE");
        confirmButton.setBackground(new Color(44, 44, 44));
        confirmButton.setFont(new Font("Garamond", Font.BOLD, 20));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBounds(400, 550, 200, 40);
        confirmButton.setFocusable(false);
        background.getMainPanel().add(confirmButton);
    }
    
    public JLabel createLabel(String text, int fontSize, int xAxis, int yAxis)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Garamond", Font.PLAIN, fontSize));
        label.setBounds(xAxis, yAxis, 250, 40);  // Adjust position and size as needed
        background.getMainPanel().add(label);

        return label;
    }

    public JTextField createTextField(int xAxis, int yAxis, int width)
    {
        JTextField textField = new JTextField();
        textField.setBounds(xAxis, yAxis, width, 40);
        textField.setFont(new Font("Garamond", Font.PLAIN, 18));
        background.getMainPanel().add(textField);

        return textField;
    }

    public void displayMessage(String text)
    {
        JOptionPane.showMessageDialog(null, text);
    }

    public void clearTextFields()
    {
        this.hotelName.setText("");
        this.standardRoom.setText("0");
        this.deluxeRoom.setText("0");
        this.executiveRoom.setText("0");
    }

    public void setConfirmButtonListener(ActionListener actionListener) {
		this.confirmButton.addActionListener(actionListener);
	}

    public String getHotelNameText() {
		return this.hotelName.getText();
	}
    public String getStandardRoomText() {
        if (this.standardRoom.getText() == null || this.standardRoom.getText().equals(""))
        {
            this.standardRoom.setText("0");
        }

		return this.standardRoom.getText();
	}
    public String getDeluxeRoomText() {
		if (this.deluxeRoom.getText() == null || this.deluxeRoom.getText().equals(""))
        {
            this.deluxeRoom.setText("0");
        }

		return this.deluxeRoom.getText();
	}
    public String getExecutiveRoomText() {
		if (this.executiveRoom.getText() == null || this.executiveRoom.getText().equals(""))
        {
            this.executiveRoom.setText("0");
        }

		return this.executiveRoom.getText();
	}
}
