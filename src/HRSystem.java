import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a Hotel Reservation System that manages multiple hotels.
 */
public class HRSystem
{
    private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    // CONSTRUCTOR - none; is set to default.

    // ADD, REMOVE and SEARCH HOTEL METHODS
    /**
     * Creates a new hotel with the specified name.
     * @param name the name of the new hotel
     * @return true if the hotel is created successfully, false if the hotel already exists
     */
    public boolean createHotel(String name, int numOfRooms)
    {
        boolean success = false;

        if (findHotelByName(name) == null)
        {
            hotelList.add(new Hotel(name, numOfRooms));
            success = true;
        }
        return success;
    }

    /**
     * Removes a hotel with the specified name.
     * @param name the name of the hotel to remove
     * @return true if the hotel is removed successfully, false if the hotel does not exist
     */
    public boolean removeHotel(String name)
    {
        boolean success = false;
        if (findHotelByName(name) != null)
        {
            hotelList.remove(findHotelByName(name));
            success = true;
        }
        return success;
    }

    /**
     * Finds a hotel by its name.
     * @param name the name of the hotel to find
     * @return the hotel with the specified name, or null if no such hotel exists
     */
    public Hotel findHotelByName(String name)
    {
        for (Hotel hotel : this.hotelList)
        {
            if (hotel.getName().equals(name))
            {
                return hotel;
            }
        }
        return null;
    }

    /**
     * Prints the list of existing hotels.
     */
    public void printHotel()
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
     * Displays high-level or low-level information about a specified hotel.
     * @param name the name of the hotel to view
     */
    public void viewHotel(String name)
    {
        int choice;
        Hotel hotel = findHotelByName(name);
        Scanner scanner = new Scanner(System.in);
        String buffer;

        do
        {
            System.out.println("1. High-level information");
            System.out.println("2. Low-level information");
            System.out.println("3. Quit");

            System.out.print("Choice: ");
            choice = scanner.nextInt();
            buffer = scanner.nextLine();

            switch(choice)
            {
                case 1:
                hotel.getHighLevel();
                break;

                case 2:
                hotel.getLowLevel();
                break;
                
                default: break;
            }
        } while (choice != 3);
    }
    
    /**
     * Manages a specified hotel, allowing the user to change the hotel name, add or remove rooms,
     * update base room price, remove reservations, and remove hotels.
     * @param name the name of the hotel to manage
     */
    public void manageHotel(String name)
    {
        int choice;
        Hotel hotel = findHotelByName(name);
        Scanner scanner = new Scanner(System.in);
        String inputName, buffer;
        double price;
        boolean repeat = false;

        do
        {
            System.out.println("\n+-----------------------------------------------------+");
            System.out.println("|                     MANAGE HOTEL                    |");
            System.out.println("| 1. Change hotel name      4. Update room base price |");
            System.out.println("| 2. Add hotel room         5. Remove reservation     |");
            System.out.println("| 3. Remove hotel room      6. Remove hotel           |");
            System.out.println("|             Press 0 to EXIT Manage Hotel            |");
            System.out.println("+-----------------------------------------------------+\n");
    
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            buffer = scanner.nextLine();

            switch(choice)
            {
                case 1:
                
                System.out.print("Input new hotel name: ");
                inputName = scanner.nextLine();

                if (findHotelByName(inputName) == null)
                {
                    hotel.setName(inputName);
                    System.out.println("Name successfully changed.");
                }
                else
                {
                    System.out.println("Hotel already exists.");
                }
                
                break;

                case 2:

                do {
                    int i;
                    repeat = false;
                    do
                    {
                        System.out.print("\nHow many rooms would you like to add?");
                        choice = scanner.nextInt();
                        buffer = scanner.nextLine();

                        if (choice > 50 - hotel.getNumberOfRooms())
                        {
                            System.out.println("Number exceeds the maximum count. Enter another number.");
                        }
                        else if (choice < 0)
                        {
                            System.out.println("Number is invalid. Enter another number.");
                        }
                    } while (choice < 0 || choice > 50 - hotel.getNumberOfRooms());

                    for (i = 0; i < choice; i++)
                    {
                        hotel.addRoom();
                    }
                    System.out.println("\nRoom successfully added.");

                    do {
                        System.out.println("Would you like to add another room?");
                        System.out.println("(1) YES           (2) NO");
                        System.out.print("Choice: ");
                        choice = scanner.nextInt();
                        buffer = scanner.nextLine(); // clear buffer
                    } while (choice != 1 && choice != 2);

                    if (choice == 1)
                    {
                        repeat = true;
                    }
                } while (repeat);
                
                break;

                case 3:

                do
                {
                    repeat = false;
                    System.out.print("\nName of room: ");
                    inputName = scanner.nextLine();

                    if (hotel.findRoomByName(inputName) != null)
                    {
                        hotel.removeRoom(inputName);
                        System.out.println("\nRoom successfully removed.");
                    }
                    else
                    {
                        System.out.println("\nRoom does not exist.");
                    }

                    do {
                        System.out.println("Would you like to remove another room?");
                        System.out.println("(1) YES           (2) NO");
                        System.out.print("Choice: ");
                        choice = scanner.nextInt();
                        buffer = scanner.nextLine(); // clear buffer
                    } while (choice != 1 && choice != 2);

                    if (choice == 1)
                    {
                        repeat = true;
                    }
                } while (repeat);
                
                break;

                case 4:
                do
                {
                    repeat = false;
                    System.out.print("\nNew base price: ");
                    price = scanner.nextDouble();
                    buffer = scanner.nextLine();

                    if (hotel.setBasePrice(price))
                    {
                        System.out.println("\nPrice successfully updated.");
                    }
                    else
                    {
                        System.out.println("\nPrice must be >= 100.");
                        do {
                            System.out.println("Would you like to set a new base price?");
                            System.out.println("(1) YES           (2) NO");
                            System.out.print("Choice: ");
                            choice = scanner.nextInt();
                            buffer = scanner.nextLine(); // clear buffer
                        } while (choice != 1 && choice != 2);

                        if (choice == 1)
                        {
                            repeat = true;
                        }

                    }
                } while (repeat);
                
                break;

                case 5:

                ArrayList<Reservation> reservationList = new ArrayList<Reservation>(); 
                Reservation deleteRes;
                int i;

                do
                {
                    repeat = false;
                    System.out.print("\nName of Guest: ");
                    name = scanner.nextLine();
                    reservationList = hotel.filterReservationByName(name);

                    System.out.println();

                    if (reservationList.isEmpty())
                    {
                        System.out.println("\nNo reservations.");
                    }
                    else
                    {
                        System.out.println("\nList of reservations under guest \"" + name + "\":");
                        System.out.println();
                        for (i = 0; i < reservationList.size(); i++)
                        {
                            System.out.println((i+1) + " - Check-in date:  " + reservationList.get(i).getCheckInDate());
                            System.out.println("    Check-out date: " + reservationList.get(i).getCheckOutDate());
                            System.out.println("    Room:           " + reservationList.get(i).getRoom().getName());
                            System.out.println();
                        }

                        do
                        {
                            System.out.print("Reservation index (1-" + reservationList.size() + "): ");
                            choice = scanner.nextInt();
                            buffer = scanner.nextLine();
                        } while (choice < 1 || choice > reservationList.size() + 1 );

                        deleteRes = reservationList.get(choice - 1);

                        if (hotel.removeReservation(deleteRes.getGuestName(), deleteRes.getCheckInDate(), deleteRes.getCheckOutDate()))
                        {
                            System.out.println("\nReservation successfully deleted!");
                        }
                        else
                        {
                            System.out.println("\nError deleting reservation.");
                        }

                        do {
                            System.out.println("\nWould you like to remove another reservation?");
                            System.out.println("(1) YES           (2) NO");
                            System.out.print("Choice: ");
                            choice = scanner.nextInt();
                            buffer = scanner.nextLine(); // clear buffer
                        } while (choice != 1 && choice != 2);

                        if (choice == 1)
                        {
                            repeat = true;
                        }
                    } 
                } while (repeat);  

                break;

                case 6:
  
                do {
                    System.out.println("\nWould you like to delete hotel \"" + name + "\"?");
                    System.out.println("(1) YES           (2) NO");
                    System.out.print("Choice: ");
                    choice = scanner.nextInt();
                    buffer = scanner.nextLine(); // clear buffer
                } while (choice != 1 && choice != 2);

                if (choice == 1)
                {
                    if (removeHotel(name))
                    {
                        System.out.println("\nHotel successfully deleted");
                    }
                }

                break;

                default:
                
                break;
            }
        } while (choice != 0);
    }

    /**
     * Simulates the booking process for a guest.
     * @return true if the booking is successful, false otherwise
     */
    public boolean simulateBooking()
    {
        Scanner scanner = new Scanner(System.in);
        String hotelName, guestName;
        int checkInDate, checkOutDate;
        Hotel chosenHotel;
        String buffer;
        boolean success = false;
        
        printHotel();

        do
        {
            System.out.print("\nSelect hotel: ");
            hotelName = scanner.nextLine();
            chosenHotel = findHotelByName(hotelName);

            if (chosenHotel == null)
            {
                System.out.println("\nInvalid hotel. Please try again.\n");
            }
        } while (chosenHotel == null);

        do
        {
            do
            {
                System.out.print("\nCheck in date: ");
                checkInDate = scanner.nextInt();
                buffer = scanner.nextLine();

                if (checkInDate >= 31 || checkInDate <= 0)
                {
                    System.out.println("Invalid date. Try again.");
                }
            } while (checkInDate >= 31 || checkInDate <= 0);
            
            do
            {
                System.out.print("\nCheck out date: ");
                checkOutDate = scanner.nextInt();
                buffer = scanner.nextLine();

                if (checkOutDate >= 32 || checkOutDate <= 1)
                {
                    System.out.println("Invalid date. Try again.");
                }

            } while (checkOutDate >= 32 || checkOutDate <= 1);

            if (checkInDate == checkOutDate)
            {
                System.out.println("\nCheck-in and check-out dates must not be the same.");
            }

        } while (checkInDate == checkOutDate);


        if (chosenHotel.getAvailableRooms(checkInDate, checkOutDate) > 0)
        {
            System.out.print("\nGuest name: ");
            guestName = scanner.nextLine();
            
            chosenHotel.createReservation(guestName, checkInDate, checkOutDate);
            success = true;
        }
        else
        {
            System.out.println("\nNo room available. ");
        }

        return success;
    }

}
