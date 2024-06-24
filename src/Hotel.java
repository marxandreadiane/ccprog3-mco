import java.util.ArrayList;

/**
 * Represents a Hotel with a name, a list of rooms, and a list of reservations.
 */
public class Hotel
{

    private String name;
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    private int roomNumber = 1;
    private double basePrice = 1299.00;

    // CONSTRUCTOR
    /**
     * Constructs a Hotel with the specified name, specified number of rooms and an empty list of reservations
     * @param name the name of the hotel
     * @param numOfRooms the number of rooms to be added in the hotel
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
     * Gets the number of rooms in this hotel
     * @return the number of rooms
     */
    public int getNumberOfRooms()
    {
        return roomList.size();
    }

    /**
     * Gets the list of names of the rooms in this hotel
     * @return the list of names of the rooms
     */
    public ArrayList<String> getRoomList()
    {
        ArrayList<String> rooms = new ArrayList<String>();

        for (Room room : this.roomList)
        {
            rooms.add(room.getName());
        }
        
        return rooms;
    }

    /**
     * Gets the list of reservations in this hotel
     * @return the reservation list of this hotel
     */
    public ArrayList<Reservation> getReservationList()
    {
        return this.reservationList;
    }

    /**
     * Gets the base price of the rooms in this hotel
     * @return the base price of the rooms
     */
    public double getBasePrice()
    {
        return this.basePrice;
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
     */
    public void setBasePrice(double price)
    {
        this.basePrice = price;
    }

    
    // SPECIFIC GETTERS FOR HIGH AND LOW LEVEL DATA
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
    public double getTotalReservationEarnings()
    {
        double total = 0;
        for (Reservation reservation : reservationList)
        {
            total += (reservation.getCheckOutDate() - reservation.getCheckInDate()) * this.basePrice;
        }

        return total;
    }
        
    // ADD or DELETE METHODS
    /**
     * Adds a room to this hotel.
     */
    public void addRoom()
    {
        String name = getName().charAt(0) + String.format("%d", this.roomNumber);
        roomList.add(new Room(name));

        this.roomNumber++;
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
     * @param room the room to be reserved
     */
    public void createReservation(String guestName, int checkInDate, int checkOutDate, Room room)
    {
        Reservation reservation;
        
        reservation = new Reservation(guestName, checkInDate, checkOutDate, room);
        room.bookRoom(checkInDate, checkOutDate);
        reservationList.add(reservation);
    }

    /**
     * Removes a reservation for a guest.
     * @param guestName the name of the guest
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @param room the reserved room
     * @return true if the reservation is removed successfully, false otherwise
     */
    public boolean removeReservation(String guestName, int checkInDate, int checkOutDate, Room room)
    {
        boolean success = false;
        int i;

        for (i = 0; i < reservationList.size(); i++)
        {
            if (reservationList.get(i).getGuestName().equals(guestName)
                && reservationList.get(i).getCheckInDate() == checkInDate
                && reservationList.get(i).getCheckOutDate() == checkOutDate
                && reservationList.get(i).getRoom() == room)
            {
                reservationList.get(i).getRoom().removeReservedDate(checkInDate, checkOutDate);
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

    /**
     * Filters available rooms for a given date range.
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return the list of names of all available rooms
     */
    public ArrayList<String> filterAvailableRooms(int checkInDate, int checkOutDate)
    {
        ArrayList<String> availableRooms = new ArrayList<String>();

        for (Room room : this.roomList)
        {
            if (room.getAvailability(checkInDate, checkOutDate) == true)
            {
                availableRooms.add(room.getName());
            }
        }
        return availableRooms;
    }
}
