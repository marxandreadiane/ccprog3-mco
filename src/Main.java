/**
 * The Main class serves as the entry point to the Hotel Reservation System, providing options to access the functionalities of the system.
 */
public class Main
{
    /**
     * The main method serves as the entry point to the Hotel Reservation System.
     * This instantiates HRSystem, SystemView, and SystemController,
     * and presents the functionalities of the system.
     *
     * @param args command line arguments
     */
    public static void main (String[] args)
    {
        HRSystem system = new HRSystem();
        SystemView systemView = new SystemView();
        SystemController systemController = new SystemController(systemView, system);
        int choice;

        do 
        {
            systemView.displayMainMenu();
            choice = systemView.promptInt("Select: ");

            switch(choice)
            {
                case 0:
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;
                
                case 1: 
                    systemController.createHotel();
                    break;

                case 2:
                    systemController.viewHotel();
                    break;

                case 3: 
                    systemController.manageHotel();
                    break;

                case 4: 
                    systemController.simulateBooking();
                    break;

                default:
                    System.out.println("Invalid response! Please enter to continue.");
                    systemView.buffer();
                    break;
            }
        } while (choice != 0);
    }
}
