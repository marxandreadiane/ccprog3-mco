import java.util.Scanner;
import java.util.ArrayList;

public class HRSystem
{
    private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    public boolean createHotel(String name)
    {
        boolean success = false;

        if (findHotelByName(name) == null)
        {
            hotelList.add(new Hotel(name));
            success = true;
        }

        return success;
    }

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

    public void manageHotel(String name)
    {
        int choice;
        Hotel hotel = findHotelByName(name);
        Scanner scanner = new Scanner(System.in);
        String inputName, buffer;
        double price;

        do
        {
            System.out.println("1. Change hotel name");
            System.out.println("2. Add room to hotel");
            System.out.println("3. Remove room from hotel");
            System.out.println("4. Update base price for a room");
            System.out.println("5. Remove reservation");
            System.out.println("6. Remove hotel");
            System.out.println("7. Quit");

            System.out.print("Choice: ");
            choice = scanner.nextInt();
            buffer = scanner.nextLine();

            switch(choice)
            {
                case 1:
                System.out.print("Input new name: ");
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

                System.out.print("Name of new room: ");
                inputName = scanner.nextLine();

                if (hotel.addRoom(inputName))
                {
                    System.out.println("Room successfully added.");
                }
                else
                {
                    System.out.println("Room already exists.");
                }
                
                break;

                case 3:

                System.out.print("Name of room: ");
                inputName = scanner.nextLine();

                if (hotel.findRoomByName(inputName) != null)
                {
                    hotel.removeRoom(inputName);
                    System.out.println("Room successfully removed.");
                }
                else
                {
                    System.out.println("Room does not exist.");
                }
                
                break;

                case 4:

                System.out.print("New base price: ");
                price = scanner.nextDouble();
                buffer = scanner.nextLine();

                if (hotel.setBasePrice(price))
                {
                    System.out.println("Price successfully updated.");
                }
                else
                {
                    System.out.println("Price must be >= 100.");
                }
                
                break;

                case 5:

                ArrayList<Reservation> reservationList = hotel.filterReservationByName(name); 
                Reservation deleteRes;
                
                int i;
                System.out.println("Name of Guest: ");
                name = scanner.nextLine();

                System.out.println();

                if (reservationList.size() == 0)
                {
                    System.out.println("No reservations.");
                }
                else
                {
                    System.out.println("List of reservations under guest: ");
                    System.out.println();
                    System.out.println("NAME            Check-in date   Check-out date      Room ");
                    for (i = 0; i < reservationList.size(); i++)
                    {
                        System.out.println(i + ". Name:           " + reservationList.get(i).getGuestName());
                        System.out.println("   Check-in date:  " + reservationList.get(i).getCheckInDate());
                        System.out.println("   Check-out date: " + reservationList.get(i).getCheckOutDate());
                        System.out.println("   Room:           " + reservationList.get(i).getRoom().getName());
                    }

                    do
                    {
                        System.out.println("Reservation index (1-" + reservationList.size() + 1 + ") : ");
                        choice = scanner.nextInt();
                        buffer = scanner.nextLine();
                    } while (choice < 1 || choice > reservationList.size() + 1 );

                    deleteRes = reservationList.get(choice - 1);

                    if (hotel.removeReservation(deleteRes.getGuestName(), deleteRes.getCheckInDate(), deleteRes.getCheckOutDate()))
                    {
                        System.out.println("Reservation successfully deleted!");
                    }
                    else
                    {
                        System.out.println("Error deleting reservation.");
                    }
                }   

                break;

                case 6:
                
                do {
                    System.out.println("Would you like to view the list of hotels?");
                    System.out.println("(1) YES           (2) NO");
                    choice = scanner.nextInt();
                    buffer = scanner.nextLine(); // clear buffer
                } while (choice != 1 && choice != 2);

                if (choice == 1)
                {
                    printHotel();
                }
                
                System.out.print("Select hotel: ");
                name = scanner.nextLine();

                if (removeHotel(name))
                {
                    System.out.println("Hotel successfully deleted");
                }
                else
                {
                    System.out.println("Error. Hotel does not exist.");
                }

                break;

                default:
                
                break;
            }
        } while (choice != 7);
    }

    public boolean simulateBooking()
    {
        Scanner scanner = new Scanner(System.in);
        String hotelName, guestName;
        int checkInDate, checkOutDate;
        Hotel chosenHotel;
        String buffer;
        boolean success = false;
        
        System.out.println("Available Hotels: ");
        for (Hotel hotel : hotelList)
        {
            System.out.println(hotel.getName()); // baka pwedeng gumawa pa ng method para yung mga hindi fully booked lang ipprint
        }
        System.out.println("\n\n");

        do
        {
            System.out.print("Select hotel: ");
            hotelName = scanner.nextLine();
            chosenHotel = findHotelByName(hotelName);

            if (chosenHotel == null)
            {
                System.out.println("Invalid hotel. Please try again.");
            }
        } while (chosenHotel == null);

        do
        {
            do
            {
                System.out.print("Check in date: ");
                checkInDate = scanner.nextInt();
                buffer = scanner.nextLine();

                if (checkInDate >= 31 || checkInDate <= 0)
                {
                    System.out.println("Invalid date. Try again.");
                }
            } while (checkInDate >= 31 || checkInDate <= 0);
            
            do
            {
                System.out.print("Check out date: ");
                checkOutDate = scanner.nextInt();
                buffer = scanner.nextLine();

                if (checkOutDate >= 32 || checkOutDate <= 1)
                {
                    System.out.println("Invalid date. Try again.");
                }

            } while (checkOutDate >= 32 || checkOutDate <= 1);

            if (checkInDate == checkOutDate)
            {
                System.out.println("Check-in and check-out dates must not be the same.");
            }

        } while (checkInDate == checkOutDate);


        if (chosenHotel.getAvailableRooms(checkInDate, checkOutDate) > 0)
        {
            System.out.print("Guest name: ");
            guestName = scanner.nextLine();
            
            chosenHotel.createReservation(guestName, checkInDate, checkOutDate);
            success = true;
        }
        else
        {
            System.out.println("No room available. ");
        }

        return success;
    }

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

    public void printHotel()
    {
        int i;
        System.out.println("- List of Existing Hotels -");
        for (i = 0; i < hotelList.size(); i++)
        {
            System.out.println((i + 1) + ". " + hotelList.get(i).getName());
        }
        System.out.println("- - - - END OF LIST - - - -");
    }
}