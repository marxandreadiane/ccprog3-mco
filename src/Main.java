import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main (String[] args)
    {
        HRSystem system = new HRSystem();
        int choice;

        String name;

        do 
        {
            System.out.println("\n+-----------------------------------------------------+");
            System.out.println("|               HOTEL RESERVATION SYSTEM              |");
            System.out.println("| 1. Create Hotel                     3. Manage Hotel |");
            System.out.println("| 2. View Hotel                       4. Book Hotel   |");
            System.out.println("|                Press 0 to QUIT SYSTEM               |");
            System.out.println("+-----------------------------------------------------+");
            System.out.print("Select: ");
            choice = scanner.nextInt();
            name = scanner.nextLine(); // clear buffer lang

            switch(choice)
            {
                case 1: 
                boolean tryAgain;

                do
                {
                    tryAgain = false;
                    
                    System.out.println("\n+-----------------------------------------------------+");
                    System.out.println("|                     CREATE HOTEL                    |");
                    System.out.println("+-----------------------------------------------------+");
                    System.out.print("Name of the new hotel: ");
                    name = scanner.nextLine();

                    if (system.createHotel(name))
                    {
                        System.out.println("\nHotel successfully created!\n");
                        do
                        {
                            System.out.println("Would you like to create another hotel?");
                            System.out.println("(1) YES         (2) NO");
                            System.out.print("Choice: ");
                            choice = scanner.nextInt();
                            name = scanner.nextLine(); // clear buffer

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
                            System.out.println("\nError! Hotel already exists. Would you like to try again?");
                            System.out.println("(1) YES         (2) NO");
                            System.out.print("Choice: ");
                            choice = scanner.nextInt();
                            name = scanner.nextLine(); // clear buffer

                            if (choice == 1)
                            {
                                tryAgain = true;
                            }   
                        } while (choice != 1 && choice != 2);
                    }
                    
                } while (tryAgain);
            
                
                break;


                case 2:

                System.out.println("\n+-----------------------------------------------------+");
                System.out.println("|                      VIEW HOTEL                     |");
                System.out.println("+-----------------------------------------------------+");
                
                do {
                    System.out.println("\nWould you like to view the list of hotels?");
                    System.out.println("(1) YES           (2) NO");
                    System.out.print("Choice: ");
                    choice = scanner.nextInt();
                    name = scanner.nextLine(); // clear buffer
                } while (choice != 1 && choice != 2);

                if (choice == 1)
                {
                    system.printHotel();
                }
                
                do
                {
                    System.out.print("\nName of hotel: ");
                    name = scanner.nextLine();

                    if (system.findHotelByName(name) == null)
                    {
                        System.out.println("\nHotel not found. Try again.");
                    }

                } while (system.findHotelByName(name) == null);

                system.viewHotel(name); 

            
                break;

                case 3: 

                System.out.println("\n+-----------------------------------------------------+");
                System.out.println("|                     MANAGE HOTEL                    |");
                System.out.println("+-----------------------------------------------------+");
    
                do {
                    System.out.println("\nWould you like to view the list of hotels?");
                    System.out.println("(1) YES           (2) NO");
                    System.out.print("Choice: ");
                    choice = scanner.nextInt();
                    name = scanner.nextLine(); // clear buffer
                } while (choice != 1 && choice != 2);

                if (choice == 1)
                {
                    system.printHotel();
                }
                
                do
                {
                    System.out.print("\nName of hotel: ");
                    name = scanner.nextLine();

                    if (system.findHotelByName(name) == null)
                    {
                        System.out.println("\nHotel not found. Try again.");
                    }

                } while (system.findHotelByName(name) == null);
                
                system.manageHotel(name); 
                
                break;

                case 4: 

                System.out.println("\n+-----------------------------------------------------+");
                System.out.println("|                      BOOK HOTEL                     |");
                System.out.println("+-----------------------------------------------------+");
                
                if(system.simulateBooking())
                {
                    System.out.println("\nBooked successfully.");
                }
                else
                {
                    System.out.println("\nError. Booking failed.");
                }
                break;

                default: break;
            }
        } while (choice != 0);
    }
}
