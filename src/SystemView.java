import java.util.Scanner;

public class SystemView {

    private Scanner scanner;

    // CONSTRUCTORS
    public SystemView() 
    {
        this.scanner = new Scanner(System.in);
    }

    // GENERAL PRINT AND PROMPT METHODS
    private void displayDividerDecorator()
    {
		System.out.println("==========");
	}

    public int promptYN(String message) 
    {
        System.out.println(message);
        System.out.println("(1) YES         (2) NO");
        System.out.print("Choice: ");
        int response = scanner.nextInt();
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

    // SPECIFIC PRINT AND PROMPT METHODS
    public void displayWelcomeMessage() 
    {
        System.out.println("Welcome to the Hotel Reservation System!\n");
    }

    public int promptMainMenu() 
    {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|               HOTEL RESERVATION SYSTEM              |");
        System.out.println("| 1. Create Hotel                     3. Manage Hotel |");
        System.out.println("| 2. View Hotel                       4. Book Hotel   |");
        System.out.println("|                Press 0 to QUIT SYSTEM               |");
        System.out.println("+-----------------------------------------------------+");
        System.out.print("Select: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
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
}
