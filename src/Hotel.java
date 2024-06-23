import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Hotel with a name, a list of rooms, and a list of reservations.
 */
public class Hotel
{
    private static Scanner scanner = new Scanner(System.in);

    private String name;
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    private int roomNumber = 1;
    private double basePrice = 1299.00;

    // CONSTRUCTOR
    /**
     * Constructs a Hotel with the specified name, specified number of rooms and an empty list of reservations
     * @param name the name of the hotel
     */    
    public Hotel(String name, int numOfRooms)
    {
        int i;
        
        this.name = name;
        for (i = 0; i < numOfRooms; i++)
        {
            addRoom();
        }
    }


    // BASE GETTERS and SETTERS
    /**
     * Gets the name of this hotel.
     * @return the name of this hotel
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Gets the number of rooms in the hotel
     * @param date the date to check bookings
     * @return the number of booked rooms
     */
    public int getNumberOfRooms()
    {
        return roomList.size();
    }

    /**
     * 
     * @param name
     */
    public ArrayList<Reservation> getReservationList()
    {
        return this.reservationList;
    }

    /**
     * Sets the name of this hotel.
     * @param name the new name of this hotel
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the base price for all rooms in this hotel.
     * @param price the base price to set
     * @return true if the price is set successfully, false if the price is less than 100
     */
    public boolean setBasePrice(double price)
    {
        boolean success = true;

        if (price >= 100)
        {
            this.basePrice = price;
        }
        else
        {
            success = false;
        }

        return success;
    }

    
    // SPECIFIC GETTERS FOR HIGH AND LOW LEVEL DATA
    /**
     * Gets the number of available rooms for a given date range.
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return the number of available rooms
     */
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
    
    /**
     * Gets the number of available rooms for a specific date.
     * @param date the date to check availability
     * @return the number of available rooms
     */
    public int getAvailableRooms(int date)
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

    /**
     * Gets the number of booked rooms for a specific date.
     * @param date the date to check bookings
     * @return the number of booked rooms
     */
    public int getBookedRooms(int date)
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

    /**
     * Gets the total estimated earnings from all reservations.
     * @return the total earnings from reservations
     */
    private double getTotalReservationEarnings()
    {
        double total = 0;
        for (Reservation reservation : reservationList)
        {
            total += (reservation.getCheckOutDate() - reservation.getCheckInDate()) * this.basePrice;
        }

        return total;
    }

    /**
     * Prints high-level information about this hotel, including name, total number of rooms,
     * and estimated earnings for the month.
     */
    public void getHighLevel()
    {
        String hotelInformation = "Hotel Name: " + getName() + 
                                "\nTotal Number of Rooms: " + roomList.size() +
                                "\nEstimated earnings for the month: " + getTotalReservationEarnings();

        System.out.println(hotelInformation);
    }
    
    
    // ADD or DELETE METHODS
    /**
     * Adds a room to this hotel.
     * @param name the name of the room to add
     * @return true if the room is added successfully, false if the room already exists
     */
    public boolean addRoom()
    {
        boolean success = false;
        String name = getName().charAt(0) + String.format("%02d", this.roomNumber);

        if (roomList.size() < 50)
        {
            roomList.add(new Room(name));
            success = true;
        }

        this.roomNumber++;
        return success;
    }

    /**
     * Removes a room from this hotel.
     * @param name the name of the room to remove
     * @return true if the room is removed successfully, false if the room does not exist
     */
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

    /**
     * Creates a reservation for a guest.
     * @param guestName the name of the guest
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return true if the reservation is created successfully, false otherwise
     */
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
                roomList.get(i).bookRoom(checkInDate, checkOutDate);
                reservation.setRoom(roomList.get(i));

                reservationList.add(reservation);
                success = true;
            }
            i++;
        }
        
        return true;
    }

    /**
     * Removes a reservation for a guest.
     * @param guestName the name of the guest
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return true if the reservation is removed successfully, false otherwise
     */
    public boolean removeReservation(String guestName, int checkInDate, int checkOutDate)
    {
        boolean success = false;
        int i;

        for (i = 0; i < reservationList.size(); i++)
        {
            if (reservationList.get(i).getGuestName().equals(guestName)
                && reservationList.get(i).getCheckInDate() == checkInDate
                && reservationList.get(i).getCheckOutDate() == checkOutDate)
            {
                reservationList.remove(i);
                success = true;
            }
        }
        
        return success;
    }

    // SEARCH METHODS
    /**
     * Finds a room by its name.
     * @param name the name of the room to find
     * @return the room with the specified name, or null if no such room exists
     */
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

    /**
     * Filters reservations by guest name.
     * @param name the name of the guest
     * @return a list of reservations for the specified guest
     */
    public ArrayList<Reservation> filterReservationByName(String name)
    {
        ArrayList<Reservation> filteredList = new ArrayList<Reservation>();

        for (Reservation reservation : this.reservationList)
        {
            if (reservation.getGuestName().equals(name))
            {
                filteredList.add(reservation);
            }
        }

        return filteredList;
    }
}
