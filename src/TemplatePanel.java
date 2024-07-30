import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TemplatePanel {
    private JFrame frame;
    private JButton createHotel;
    private JButton manageHotel;
    private JButton viewHotel;
    private JButton bookHotel;
    private JButton homeButton;
    private JPanel mainPanel;

    public TemplatePanel() {
        // Set up the frame
        frame = new JFrame("Hotel Reservation System");
        frame.setSize(1200, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);


        // Set up the side panel
        JPanel sidePanel = createSidePanel();
        frame.add(sidePanel);

        // Set up the main panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(220, 208, 200));
        mainPanel.setLayout(null);
        mainPanel.setBounds(200, 0, 1000, 700);
        frame.add(mainPanel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel createSidePanel()
    {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(44, 44, 44));
        sidePanel.setBounds(0, 0, 200, 700);
        
        // Create and add logo to the side panel
        ImageIcon logo = new ImageIcon("hotel_logo.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(9, 10, 180, 180);  // Adjust position and size as needed
        sidePanel.add(logoLabel);

        // Create and add buttons to the side panel
        createHotel = createButton("Create Hotel");
        createHotel.setBounds(15, 220, 170, 35);
        sidePanel.add(createHotel);

        viewHotel = createButton("View Hotel");
        viewHotel.setBounds(15, 280, 170, 35);
        sidePanel.add(viewHotel);

        manageHotel = createButton("Manage Hotel");
        manageHotel.setBounds(15, 340, 170, 35);
        sidePanel.add(manageHotel);

        bookHotel = createButton("Book Hotel");
        bookHotel.setBounds(15, 400, 170, 35);
        sidePanel.add(bookHotel);

        homeButton = createButton("Home Menu");
        homeButton.setBounds(15, 460, 170, 35);
        sidePanel.add(homeButton);

        return sidePanel;
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

    public JPanel getMainPanel()
    {
        return this.mainPanel;
    }

    public JFrame getFrame()
    {
        return this.frame;
    }

    public JButton getHomeButton()
    {
        return this.homeButton;
    }

    public void setCreateButtonListener(ActionListener actionListener) {
		this.createHotel.addActionListener(actionListener);
	}

    public void setManageHotelListener(ActionListener actionListener) {
		this.manageHotel.addActionListener(actionListener);
	}

    public void setViewHotelListener(ActionListener actionListener) {
		this.viewHotel.addActionListener(actionListener);
	}

    public void setBookHotelListener(ActionListener actionListener) {
		this.bookHotel.addActionListener(actionListener);
	}

    public void setHomeButtonListener(ActionListener actionListener) {
		this.homeButton.addActionListener(actionListener);
	}
}
