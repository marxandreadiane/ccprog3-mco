package com;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomeScreen
{
    private JFrame frame;
    private JButton button;

    public HomeScreen() {
        frame = new JFrame();
        frame.setSize(1200, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    
        // BG Panel
        ImagePanel bgPanel = new ImagePanel("/resources/new_bg.jpg");
        bgPanel.setBounds(0, 0, 1200, 700);

        // Center panel
        JPanel panel = createTransparentPanel(new Color(0, 0, 0, 125));
        panel.setLayout(null);
        panel.setBounds(350, 180, 500, 300);

        JLabel label1 = new JLabel("HOTEL RESERVATION");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Garamond", Font.BOLD, 30));   
        label1.setBounds(78, 40, 400, 50);
        panel.add(label1);

        JLabel label2 = new JLabel("SYSTEM");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Garamond", Font.BOLD, 30));        
        label2.setBounds(195, 70, 400, 50);
        panel.add(label2);

        button = new JButton("C L I C K   T O  B E G I N");
        button.setFont(new Font("Garamond", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);    
        button.setBounds(125, 175, 250, 50); // Position button within the panel
        button.setBackground(new Color(27, 27, 27));
        button.setFocusable(false);
        button.setBorderPainted(false);
        
        panel.add(button);

        frame.add(panel);
        frame.add(bgPanel);
        frame.setVisible(true);
    }

    public JPanel createTransparentPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setOpaque(true); 
        return panel;
    }

    public void setButtonListener(ActionListener actionListener) {
		this.button.addActionListener(actionListener);
	}

    public JFrame getFrame()
    {
        return this.frame;
    }
}
