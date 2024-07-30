import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRSystemController {
    private HomeScreen homeScreen;

    public HRSystemController(HRSystem hrSystem) {
        homeScreen = new HomeScreen();

        // Set up the home screen button listener
        this.homeScreen.setButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(hrSystem);
                homeScreen.getFrame().dispose();
            }
        });
    }
}
