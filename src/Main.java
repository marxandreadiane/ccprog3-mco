public class Main
{
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
