package com;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The HRSystemController class is responsible for managing the interactions between the
 * HomeScreen view and the HRSystem model.
 */
public class HRSystemController {
    private HomeScreen homeScreen;

    /**
     * Constructs an HRSystemController with the specified HRSystem.
     * @param hrSystem the HRSystem object containing hotel data
     */
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
