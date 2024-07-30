import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TemplatePanelController {

    private TemplatePanel templatePanel;
    private CreateHotel createHotel;
    private HRSystem hrSystem;

    public TemplatePanelController(TemplatePanel templatePanel, HRSystem hrSystem) {
        this.templatePanel = templatePanel;
        this.hrSystem = hrSystem;

        // Set up listeners for various buttons
        this.templatePanel.setCreateButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            new CreateHotelController(new CreateHotel(hrSystem), hrSystem);
            templatePanel.getFrame().dispose();

            }
        });

        this.templatePanel.setManageHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ManageHotelController(hrSystem);
                templatePanel.getFrame().dispose();
                
            }
        });

        this.templatePanel.setViewHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ViewHotelController(hrSystem);
                templatePanel.getFrame().dispose();
                
            }
        });

        this.templatePanel.setBookHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new BookHotelController(hrSystem);
                templatePanel.getFrame().dispose();
                
            }
        });

        this.templatePanel.setHomeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new MainMenu(hrSystem);
                templatePanel.getFrame().dispose();
                
            }
        });

        this.templatePanel.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    templatePanel.getFrame(),
                    "Are you sure you want to exit? Hotel Reservation System data will cease to exist.",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) {
                    templatePanel.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    templatePanel.getFrame().dispose(); 
                }
            }
        });
    }
}
