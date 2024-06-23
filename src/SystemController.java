public class SystemController {
    private SystemView systemView;
    private HRSystem system;

    // CONSTRUCTORS
    /** 
     * 
     */
    public SystemController(SystemView systemView, HRSystem system)
    {
        this.systemView = systemView;
        this.system = system;
    }

    // MAIN METHODS
    public void createHotel() 
    {
        int choice;
        boolean tryAgain;
        do
        {
            tryAgain = false;
            
            systemView.displayCreateHotelMenu();
            String name = systemView.promptName("Name of the new hotel: ");

            if (system.createHotel(name))
            {
                System.out.println("\nHotel successfully created!\n");
                do
                {
                    choice = systemView.promptYN("Would you like to create another hotel?");

                    if (choice == 1)
                    {
                        tryAgain = true;
                    }   
                } while (choice != 1 && choice != 2);
            }
            else
            {
                do
                {
                    choice = systemView.promptYN("\nError! Hotel already exists. Would you like to try again?");

                    if (choice == 1)
                    {
                        tryAgain = true;
                    }   
                } while (choice != 1 && choice != 2);
            }
            
        } while (tryAgain);
    }

    public void viewHotel() 
    {
        int choice; String name;
        systemView.displayViewHotelMenu();
        do 
        {
            choice = systemView.promptYN("\nWould you like to view the list of hotels?");
        } while (choice != 1 && choice != 2);

        if (choice == 1)
        {
            system.printHotel();
        }
        
        do
        {
            name = systemView.promptName("Name of hotel: ");

            if (system.findHotelByName(name) == null)
            {
                System.out.println("\nHotel not found. Try again.");
            }

        } while (system.findHotelByName(name) == null);

        system.viewHotel(name); 
    }

    public void manageHotel() 
    {
        int choice; String name;
        systemView.displayManageHotelMenu();
        do {
            choice = systemView.promptYN("Would you like to view the list of hotels?");
        } while (choice != 1 && choice != 2);

        if (choice == 1)
        {
            system.printHotel();
        }
        
        do
        {
            name = systemView.promptName("Name of hotel: ");

            if (system.findHotelByName(name) == null)
            {
                System.out.println("\nHotel not found. Try again.");
            }

        } while (system.findHotelByName(name) == null);
        
        system.manageHotel(name); 
    }

    public void simulateBooking() 
    {
        systemView.displayBookingMenu();
                    
            if(system.simulateBooking())
            {
                System.out.println("\nBooked successfully.");
            }
            else
            {
                System.out.println("\nError. Booking failed.");
            }
    }
}
 