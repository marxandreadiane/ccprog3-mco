import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains all the printing functionalities necessary
 */
public class SystemView {

    private Scanner scanner;

    // CONSTRUCTORS
    /**
     * Constructs SystemView with a scanner
     */
    public SystemView() 
    {
        this.scanner = new Scanner(System.in);
    }

    // GENERAL PRINT AND PROMPT METHODS
    /**
     * Eats the remaining character(s) after an int is read
     */
    public void buffer() {
        scanner.nextLine();
    }

    /**
     * Prompts a Yes/No question
     * @param message the question / prompt
     * @return the response of the user to the message
     */
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

    /**
     * Asks for a string input
     * @param message the question / prompt
     * @return the response of the user to the message
     */
    public String promptName(String message) 
    {
        System.out.print(message);
        String name = scanner.nextLine();
        return name;
    }

    /**
     * Asks for an int input
     * @param message the question / prompt
     * @return the response of the user to the message
     */
    public int promptInt(String message) 
    {
        System.out.print(message);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    /**
     * Asks for a double input
     * @param message the question / prompt
     * @return the response of the user to the message
     */
    public double promptDouble(String message) 
    {
        System.out.print(message);
        double number = scanner.nextDouble();
        scanner.nextLine();
        return number;
    }

    // MAIN MENU and MAIN FUNCTIONALITIES' DISPLAY METHODS
    /**
     * Displays a welcome message upon entering the hotel reservation system
     */
    public void displayWelcomeMessage() 
    {
        System.out.println("Welcome to the Hotel Reservation System!\n");
    }

    /**
     * Displays the main menu of the hotel reservation system
     */
    public void displayMainMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|               HOTEL RESERVATION SYSTEM              |");
        System.out.println("| 1. Create Hotel                    3. Manage Hotel  |");
        System.out.println("| 2. View Hotel                      4. Book Hotel    |");
        System.out.println("|                Press 0 to QUIT SYSTEM               |");
        System.out.println("+-----------------------------------------------------+");
    }

    /**
     * Displays the create hotel title 
     */
    public void displayCreateHotelMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                     CREATE HOTEL                    |");
        System.out.println("+-----------------------------------------------------+");
    }

    /**
     * Displays the view hotel title 
     */
    public void displayViewHotelMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                      VIEW HOTEL                     |");
        System.out.println("+-----------------------------------------------------+");
    }

    /**
     * Displays the manage hotel title 
     */
    public void displayManageHotelMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                     MANAGE HOTEL                    |");
        System.out.println("+-----------------------------------------------------+");
    }

    /**
     * Displays the book hotel title 
     */
    public void displayBookingMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
            System.out.println("|                      BOOK HOTEL                     |");
            System.out.println("+-----------------------------------------------------+");
    }

     // PRINT/DISPLAY SPECIFIC OBJECTS OR STRINGS
    /**
     * Prints the list of existing hotels.
     * @param hotelList the list of hotels in the system
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

    /**
     * Prints the available dates given a room 
     * @param room a room in the system
     */
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
    /**
     * Displays the view hotel menu
     * @param name the name of the hotel
     */
    public void displayHotelInfoMenu(String name)
    {
        int choice;
        System.out.println("\nSelect info you want to know about hotel: " + name);
        System.out.println("1. High-level information");
        System.out.println("2. Low-level information");
        System.out.println("3. Quit");
    }

    /**
     * Displays the low level information menu under view hotel 
     * @param name the name of the hotel
     */
    public void displayLowLevelInfoMenu(String name) 
    {
        System.out.println("\nSelect which info you want to know more about hotel: " + name);
        System.out.println("1. Hotel availability given date");
        System.out.println("2. Room information");
        System.out.println("3. Reservation information");
        System.out.println("4. Quit");
    }

    /**
     * Displays the high level information menu under view hotel 
     * @param hotel the hotel whose high level information will be displayed
     */
    public void displayHighLevelInfo(Hotel hotel)
    {
        System.out.println("\nHere are the high-level information of the hotel:");

        String hotelInformation = "Hotel Name: " + hotel.getName() + 
                                "\nTotal Number of Rooms: " + hotel.getRoomList().size() +
                                "\nEstimated earnings for the month: " + hotel.getTotalReservationEarnings();

        System.out.println(hotelInformation);
    }

    // VIEWING / MANAGING HOTEL DISPLAY SUBMETHODS
    /**
     * Displays the menu for manage hotel 
     */
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

    /**
     * Prints information about this reservation.
     * @param reservation the reservation object containing the information to be displayed
     */
    public void displayReservationInfo(Reservation reservation)
    {
        System.out.println(" Name of guest: " + reservation.getGuestName());
        System.out.println("   Room information: " + reservation.getRoom().getName());
        System.out.println("   Check-in date: " + reservation.getCheckInDate());
        System.out.println("   Check-out date: " + reservation.getCheckOutDate());
        System.out.println();
    }

}
