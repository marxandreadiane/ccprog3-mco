package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CreateHotelController class is responsible for managing the interactions between the
 * CreateHotel view and the HRSystem model.
 */
public class CreateHotelController
{
    private CreateHotel createHotel;
    private HRSystem hrSystem;
    
    /**
     * Constructs a CreateHotelController with the specified CreateHotel and HRSystem.
     * @param createHotel the CreateHotel object containing the view
     * @param hrSystem the HRSystem object containing hotel data
     */
    public CreateHotelController(CreateHotel createHotel, HRSystem hrSystem)
    {
        this.createHotel = createHotel;
        this.hrSystem = hrSystem;

        this.createHotel.setConfirmButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = createHotel.getHotelNameText();

                int result = hrSystem.createHotel(hotelName, createHotel.getStandardRoomText(), createHotel.getDeluxeRoomText(), createHotel.getExecutiveRoomText());
                
                if (result == 0)
                {
                    createHotel.displayMessage("Kindly fill in all empty fields with appropriate values.");
                }
                else if (result == 1)
                {
                    createHotel.displayMessage("Hotel already exists.");   
                }
                else if (result == 2)
                {
                    createHotel.displayMessage("Failed to create hotel. Hotels must have 1-50 rooms.");   
                }
                else if (result == 3)
                {
                    createHotel.displayMessage("Failed to create hotel. Number of rooms per type must be >= 0.");   
                }
                else if (result == 4)
                {
                    createHotel.displayMessage("Hotel created successfully!");
                    createHotel.clearTextFields();
                }
            }
        });
    }
}