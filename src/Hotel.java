
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel
{
    private String name;
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

    // CONSTRUCTOR
    /**
     * Constructs a Hotel with the specified name, with an empty array of rooms and reservations
     * @param name the name of the hotel
     */
    public Hotel(String name)
    {
        this.name = name;
    }


    // GETTERS and SETTERS
    public String getName()
    {
        return this.name;
    }

    public void getHighLevel()
    {
        String hotelInformation = "Hotel Name: " + getName() + 
                                "\nTotal Number of Rooms: " + roomList.size() +
                                "\nEstimated earnings for the month: " + getTotalReservation();

        System.out.println(hotelInformation);
    }

    public void getLowLevel()
    {
        //String hotelInformation;
        Scanner scanner = new Scanner(System.in);
        int choice, date, i;
        String name, buffer;
        Room selectedRoom;

        do
        {
            System.out.println("1. Hotel availability");
            System.out.println("2. Room information");
            System.out.println("3. Reservation information");
            System.out.println("4. Quit");
            System.out.println("\n");

            System.out.print("Choice: ");
            choice = scanner.nextInt();

            switch(choice)
            {
                case 1: 
                do
                {
                    System.out.print("Select date: ");
                    date = scanner.nextInt();
                    buffer = scanner.nextLine();
                } while (date > 31 || date < 1);

                System.out.println("Total number of available rooms: " + getAvailableRooms(date));
                System.out.println("Total number of booked rooms: " + getBookedRooms(date));
                break;

                case 2: 
     
                do
                {
                    System.out.print("Select room: ");
                    name = scanner.nextLine();
                    selectedRoom = findRoomByName(name);
                } while (selectedRoom == null);

                System.out.println("Room name: " + selectedRoom.getName());
                System.out.println("Room price per night " + selectedRoom.getName());
                System.out.print("Available dates: ");
                selectedRoom.printAvailableDates();
                break;
                

                case 3: 

                System.out.print("Input name of guest: ");
                name = scanner.nextLine();

                for (Reservation reservation : reservationList)
                {
                    i = 0;
                    if (reservation.getGuestName().equals(name))
                    {
                        reservationList.get(i).printReservationInfo();
                    }
                    i++;
                }

                break;

                default: break;
            }
        } while (choice != 4);
    }

    private double getTotalReservation()
    {
        double total = 0;

        for (Reservation reservation : reservationList)
        {
            total += reservation.getTotal();
        }

        return total;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public int getAvailableRooms(int checkInDate, int checkOutDate)
    {
        int nRooms = 0;

        for (Room room : roomList)
        {
            if (room.getAvailability(checkInDate, checkOutDate) == true)
            {
                nRooms++;
            }
        }
        return nRooms;
    }
    
    private int getAvailableRooms(int date)
    {
        int nRooms = 0;

        for (Room room : roomList)
        {
            if (room.getAvailability(date) == true)
            {
                nRooms++;
            }
        }
        return nRooms;
    }

    private int getBookedRooms(int date)
    {
        int nRooms = 0;

        for (Room room : roomList)
        {
            if (room.getAvailability(date) == false)
            {
                nRooms++;
            }
        }
        return nRooms;
    }

    public boolean setBasePrice(double price)
    {
        boolean success = true;

        if (price >= 100)
        {
            for (Room room : roomList)
            {
                room.setPrice(price);
            }
        }
        else
        {
            success = false;
        }

        return success;
    }

    // ADD or DELETE METHODS
    public boolean addRoom(String name)
    {
        boolean success = false;

        if (findRoomByName(name) == null)
        {
            roomList.add(new Room(name));
            success = true;
        }

        return success;
    }

    public boolean removeRoom(String name)
    {
        boolean success = false;
        
        if (findRoomByName(name) != null)
        {
            roomList.remove(findRoomByName(name));
            success = true;
        }
        return success;
    }

    public boolean createReservation(String guestName, int checkInDate, int checkOutDate)
    {
        Reservation reservation;
        boolean success = false;
        int i = 0;

        while (!success && i < roomList.size())
        {
            if (roomList.get(i).getAvailability(checkInDate, checkOutDate))
            {
                reservation = new Reservation(guestName, checkInDate, checkOutDate);
                reservation.setRoom(roomList.get(i));
                
                reservationList.add(reservation);
                success = true;
            }
            i++;
        }
        
        return true;
    }

    public boolean removeReservation(String guestName, int checkInDate, int checkOutDate)
    {
        boolean success = false;
        int i = 0;

        for (Reservation reservation : reservationList)
        {
            if (reservation.getGuestName().equals(guestName)
                && reservation.getCheckInDate() == checkInDate
                && reservation.getCheckOutDate() == checkOutDate
                && !success)
            {
                reservationList.remove(i);
                success = true;
            }
            i++;
        }
        
        return success;
    }

    // SEARCH METHODS
    public Room findRoomByName(String name)
    {
        for (Room room : roomList)
        {
            if (room.getName().equals(name))
            {
                return room;
            }
        }

        return null;
    }

    public ArrayList<Reservation> filterReservationByName(String name)
    {
        int i;
        ArrayList<Reservation> filteredList = new ArrayList<Reservation>();
        
        for (i = 0; i < reservationList.size(); i++)
        {
            if (reservationList.get(i).getGuestName().equals(name))
            {
                filteredList.add(reservationList.get(i));
            }
        }

        return filteredList;
    }
}