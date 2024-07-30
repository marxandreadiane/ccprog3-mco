import java.awt.*;
import javax.swing.*;

public class MainMenu {
    private TemplatePanel background = new TemplatePanel();

    public MainMenu(HRSystem hrSystem) {
        background.getHomeButton().setEnabled(false);
        new TemplatePanelController(background, hrSystem);
        createLabel("WELCOME TO", 30, 400, 200);
        createLabel("HOTEL RESERVATION SYSTEM", 55, 80, 250);
        createLabel("Select any feature to proceed.", 20, 380, 350);
    }

    public JLabel createLabel(String text, int fontSize, int xAxis, int yAxis)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Garamond", Font.PLAIN, fontSize));
        label.setBounds(xAxis, yAxis, 800, 50);  // Adjust position and size as needed
        background.getMainPanel().add(label);

        return label;
    }

}
