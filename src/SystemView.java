import java.util.ArrayList;
import java.util.Scanner;

public class SystemView {

    private Scanner scanner;

    // CONSTRUCTORS
    public SystemView() 
    {
        this.scanner = new Scanner(System.in);
    }

    // GENERAL PRINT AND PROMPT METHODS
    public void buffer() {
        scanner.nextLine();
    }

    public int promptYN(String message) 
    {
        int response;
        do 
        {
            System.out.println(message);
            System.out.println("(1) YES         (2) NO");
            System.out.print("Choice: ");
            response = scanner.nextInt();
            if (response != 1 && response != 2) {
                System.out.println("Invalid response! Please try again!");
            }
        } while (response != 1 && response != 2);
        scanner.nextLine();
        return response;
    }

    public String promptName(String message) 
    {
        System.out.print(message);
        String name = scanner.nextLine();
        return name;
    }

    public int promptInt(String message) 
    {
        System.out.print(message);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public double promptDouble(String message) 
    {
        System.out.print(message);
        double number = scanner.nextDouble();
        scanner.nextLine();
        return number;
    }

    // MAIN MENU and MAIN FUNCTIONALITIES' DISPLAY METHODS
    public void displayWelcomeMessage() 
    {
        System.out.println("Welcome to the Hotel Reservation System!\n");
    }

    public void displayMainMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|               HOTEL RESERVATION SYSTEM              |");
        System.out.println("| 1. Create Hotel                    3. Manage Hotel  |");
        System.out.println("| 2. View Hotel                      4. Book Hotel    |");
        System.out.println("|                Press 0 to QUIT SYSTEM               |");
        System.out.println("+-----------------------------------------------------+");
    }

    public void displayCreateHotelMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                     CREATE HOTEL                    |");
        System.out.println("+-----------------------------------------------------+");
    }

    public void displayViewHotelMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                      VIEW HOTEL                     |");
        System.out.println("+-----------------------------------------------------+");
    }

    public void displayManageHotelMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                     MANAGE HOTEL                    |");
        System.out.println("+-----------------------------------------------------+");
    }

    public void displayBookingMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
            System.out.println("|                      BOOK HOTEL                     |");
            System.out.println("+-----------------------------------------------------+");
    }

     // PRINT/DISPLAY SPECIFIC OBJECTS OR STRINGS
    /**
     * Prints the list of existing hotels.
     */
    public void displayHotels(ArrayList<Hotel> hotelList)
    {
        int i;
        System.out.println("\n- List of Existing Hotels -");
        for (i = 0; i < hotelList.size(); i++)
        {
            System.out.println((i + 1) + ". " + hotelList.get(i).getName());
        }
        System.out.println("- - - - END OF LIST - - - -");
    
    }

    public void displayAvailableDates(Room room) 
    {
        int i;

        for (i = 1; i < 31; i++)
        {
            if (!room.getReservedDates().contains(i))
            {
                System.out.print(i + " ");
            }
        }
        System.out.print("\n");
    }

    // VIEW INFO DISPLAY SUBMETHODS
    public void displayHotelInfoMenu(String name)
    {
        int choice;
        System.out.println("\nSelect info you want to know about hotel: " + name);
        System.out.println("1. High-level information");
        System.out.println("2. Low-level information");
        System.out.println("3. Quit");
    }

    public void displayLowLevelInfoMenu(String name) 
    {
        System.out.println("\nSelect which info you want to know more about hotel: " + name);
        System.out.println("1. Hotel availability given date");
        System.out.println("2. Room information");
        System.out.println("3. Reservation information");
        System.out.println("4. Quit");
    }

    public void displayHighLevelInfo(Hotel hotel)
    {
        System.out.println("\nHere are the high-level information of the hotel:");

        String hotelInformation = "Hotel Name: " + hotel.getName() + 
                                "\nTotal Number of Rooms: " + hotel.getRoomList().size() +
                                "\nEstimated earnings for the month: " + hotel.getTotalReservationEarnings();

        System.out.println(hotelInformation);
    }

    // MANAGING HOTEL DISPLAY SUBMETHODS
    public void displayManagingActions()
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                     MANAGE HOTEL                    |");
        System.out.println("| 1. Change hotel name      4. Update room base price |");
        System.out.println("| 2. Add hotel room         5. Remove reservation     |");
        System.out.println("| 3. Remove hotel room      6. Remove hotel           |");
        System.out.println("|             Press 0 to EXIT Manage Hotel            |");
        System.out.println("+-----------------------------------------------------+");
    }

}
