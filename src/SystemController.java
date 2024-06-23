import java.util.ArrayList;

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
    // Create Hotel
    /**
     * Create a hotel in system (of class HRSystem)
     */
    public void createHotel() 
    {
        int choice, numOfRooms;
        boolean tryAgain;
        do
        {
            tryAgain = false;
            
            systemView.displayCreateHotelMenu();
            String name = systemView.promptName("Name of the new hotel: ");

            do
            {
                numOfRooms = systemView.promptInt("How many rooms would you like to have? ");

                if (numOfRooms > 50)
                {
                    System.out.println("Number exceeds the maximum count. Enter another number.");
                }
                else if (numOfRooms <= 0)
                {
                    System.out.println("Number is invalid. Enter another number.");
                }
            } while (numOfRooms < 0 || numOfRooms > 50);


            if (system.createHotel(name, numOfRooms))
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

    // View Hotel
    /**
     * Displays high-level or low-level information about a specified hotel.
     */
    public void viewHotel() 
    {
        Hotel selectedHotel = null;
        int choice;

        systemView.displayViewHotelMenu();

        // Prompt if View List of Hotels
        promptViewListOfHotels();
        
        // Select Hotel
        selectedHotel = promptSelectHotel();
        if (selectedHotel == null)
            return;
        

        // Prompt Info to be Viewed
        do
        {
            systemView.displayHotelInfoMenu(selectedHotel.getName());
            choice = systemView.promptInt("Choice: ");

            switch(choice)
            {
                // High-Level Info
                case 1:
                    System.out.println("\nHere are the high-level information of the hotel:");
                    selectedHotel.getHighLevel();
                    break;

                // Low-Level Info
                case 2:
                    promptLowLevelInfo(selectedHotel);
                    break;

                // Quit
                case 3:
                    break;
                
                default: 
                    System.out.println("Invalid response! Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    // Manage Hotel
    public void manageHotel() 
    {
        Hotel selectedHotel = null; 
        int choice;

        systemView.displayManageHotelMenu();

        // Prompt if View List of Hotels
        promptViewListOfHotels();
        
        // Select Hotel
        selectedHotel = promptSelectHotel();
        if (selectedHotel == null)
            return;
        
        // Prompt managing actions if there is a hotel selected
        do 
        {
            systemView.displayManagingActions();
            choice = systemView.promptInt("Choice: ");

            switch(choice)
            {
                /*
                case 1;
                    break;

                case 2;


                case 3;

                case 4;

                case 5;

                case 6;

                case 7;
                */
            }

        } while (choice != 0);
        
        

        /* 
        system.manageHotel(selectedHotel.getName()); 
        */
    }

    // Simulate Booking
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


    // SUB-METHODS FOR VIEWING AND MANAGING
    // view list of hotel
    public void promptViewListOfHotels() 
    {
        int choice;
        do 
        {
            choice = systemView.promptYN("\nWould you like to view the list of hotels?");
        } while (choice != 1 && choice != 2);

        if (choice == 1)
        {
            system.printHotel();
        }
        System.out.println();
    }
    
    // select a hotel to view/manage, returns Hotel or null
    public Hotel promptSelectHotel() 
    {
        String name;
        do
        {
            name = systemView.promptName("Name of hotel (type \"0\" without double quotes to exit): ");

            if (name.equals("0")) 
            {
                break;
            }
            if (system.findHotelByName(name) == null)
            {
                System.out.println("\nHotel not found. Try again.");
            }
            else
            {
                return system.getHotel(name);
            }

        } while (system.findHotelByName(name) == null);
        
        return null;
    }


    // prompt and view certain low-level information in Hotel
    public void promptLowLevelInfo(Hotel selectedHotel) 
    {
        int date, choice;
        String name;
        Room selectedRoom = null;

        systemView.displayLowLevelInfoMenu(selectedHotel.getName());
        choice = systemView.promptInt("Choice: ");

        switch(choice)
        {
            // Room Availability
            case 1: 
                do
                {
                    date = systemView.promptInt("Select date: ");
                } while (date > 31 || date < 1);

                System.out.println("Total number of available rooms: " + selectedHotel.getAvailableRooms(date));
                System.out.println("Total number of booked rooms: " + selectedHotel.getBookedRooms(date));
                break;

            // Room Info
            case 2: 
                do
                {
                    name = systemView.promptName("Select room: ");
                    selectedRoom = selectedHotel.findRoomByName(name);
                    
                } while (selectedRoom == null);

                System.out.println("Room name: " + selectedRoom.getName());
                System.out.println("Room price per night " + selectedRoom.getName());
                System.out.print("Available dates: ");
                selectedRoom.printAvailableDates();
                break;
            
            // Reservation Info
            case 3: 
                int i = 0;
                name = systemView.promptName("Input name of guest: ");
                ArrayList<Reservation> reservationList = selectedHotel.getReservationList();
                for (Reservation reservation : reservationList)
                {
                    if (reservation.getGuestName().equals(name))
                    {
                        reservationList.get(i).printReservationInfo();
                    }
                    i++;
                }

                break;

            default: break;
        } while (choice != 4);
    }
}
 
