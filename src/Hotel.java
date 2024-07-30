import java.util.ArrayList;
import javax.lang.model.util.ElementScanner14;

/**
 * Represents a Hotel with a name, a list of rooms, and a list of reservations.
 */
public class Hotel
{
    private String name;
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    private double basePrice = 1299.00;
    private double deluxeMultiplier = 1.2, executiveMultiplier = 1.35;
    private ArrayList<Double> datePriceModifier = new ArrayList<Double>();
    private int nextBaseRoomNumber = 1;
    private int nextDeluxeRoomNumber = 1;
    private int nextExecutiveRoomNumber = 1;


    // CONSTRUCTOR
    /**
     * Constructs a Hotel with the specified name, specified number of rooms and an empty list of reservations.
     * @param name the name of the hotel
     * @param numOfRooms the number of rooms to be added in the hotel
     */    
    public Hotel(String name, int numOfBaseRooms, int numOfDeluxeRooms, int numOfExecutiveRooms)
    {
        int i;
        
        this.name = name;
        for (i = 0; i < numOfBaseRooms; i++)
        {
            addBaseRoom();
        }
        for (i = 0; i < numOfDeluxeRooms; i++)
        {
            addDeluxeRoom();
        }
        for (i = 0; i < numOfExecutiveRooms; i++)
        {
            addExecutiveRoom();
        }

        // Date Price Modifier
        for (i = 0; i < 30; i++)
        {
            this.datePriceModifier.add(1.0);
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
     * Gets the number of rooms in this hotel.
     * @return the number of rooms
     */
    public int getNumberOfRooms()
    {
        return roomList.size();
    }

    /**
     * Gets the number of standard rooms in this hotel.
     * @return the number of rooms
     */
    public int getNumberOfStandardRooms()
    {
        int standard = 0;

        for (Room room : roomList)
        {
            if (room.getName().charAt(0) == 'S')
            {
                standard++;
            }
        }
        
        return standard;
    }

    /**
     * Gets the number of deluxe rooms in this hotel.
     * @return the number of rooms
     */
    public int getNumberOfDeluxeRooms()
    {
        int deluxe = 0;

        for (Room room : roomList)
        {
            if (room.getName().charAt(0) == 'D')
            {
                deluxe++;
            }
        }

        return deluxe;
    }
    
    /**
     * Gets the number of executive rooms in this hotel.
     * @return the number of rooms
     */
    public int getNumberOfExecutiveRooms()
    {
        int executive = 0;

        for (Room room : roomList)
        {
            if (room.getName().charAt(0) == 'E')
            {
                executive++;
            }
        }

        return executive;
    }


    /**
     * Gets the list of names of the rooms in this hotel.
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
     * Gets the details of the reservations in this hotel.
     * @return the String containing the details of the reservations
     */
    public ArrayList<String> getReservationString()
    {
        ArrayList<String> reservations = new ArrayList<String>();

        for (Reservation reservation : this.reservationList)
        {
            reservations.add(reservation.getGuestName() + " | " + reservation.getCheckInDate() + " - " + reservation.getCheckOutDate() + " | " + reservation.getRoom().getName());
        }
        
        return reservations;
    }

    /**
     * Gets the list of reservations in this hotel.
     * @return the reservation list of this hotel
     */
    public ArrayList<Reservation> getReservationList()
    {
        return this.reservationList;
    }

    /**
     * Gets the price of a room in this hotel.
     * @return the price of a room
     */
    public double getRoomPrice(Room room)
    {
        double roomPrice = this.basePrice;
        
        if (room instanceof ExecutiveRoom)
        {
            roomPrice = roomPrice * executiveMultiplier;
        }
        else if (room instanceof DeluxeRoom)
        {
            roomPrice = roomPrice * deluxeMultiplier;
        }
        
        return roomPrice;
    }

    /**
     * Gets the type of a room in this hotel.
     * @return the type of a room
     */
    public String getRoomType(Room room)
    {
        String roomType = "Standard";
        
        if (room instanceof ExecutiveRoom)
        {
            roomType = "Executive";
        }
        else if (room instanceof DeluxeRoom)
        {
            roomType = "Deluxe";
        }
        
        return roomType;
    }

    /**
     * Gets the price of the deluxe rooms in this hotel.
     * @return the base price of the rooms
     */
    public double getDeluxePrice()
    {
        return this.basePrice * 1.20;
    }

    /**
     * Gets the price of the executive rooms in this hotel.
     * @return the base price of the rooms
     */
    public double getExecutivePrice()
    {
        return this.basePrice * 1.35;
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
            total += reservation.getTotalPrice();
        }

        return total;
    }
        
    // ADD or DELETE METHODS
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
     * @param voucher the voucher code that is subject to application
     * @return 0 if failed, 1 if success, 2 if success with voucher applied, 3 if success but voucher was not applied
     */
    public int createReservation(String guestName, String checkIn, String checkOut, Room room, String voucher)
    {
        int success = 0;
        Reservation reservation;
        int checkInDate = Integer.parseInt(checkIn);
        int checkOutDate = Integer.parseInt(checkOut);
        
        if (checkInDate >= 1 && checkInDate < 31 && checkOutDate > 1 && checkOutDate <= 31
            && checkInDate < checkOutDate && room.getAvailability(checkInDate, checkOutDate))
        {
            reservation = new Reservation(guestName, checkInDate, checkOutDate, room);
            room.bookRoom(checkInDate, checkOutDate);
            reservationList.add(reservation);

            if (voucher == null || voucher.isBlank())
            {
                success = 1;
            }
            else if (voucher != null && reservation.enterVoucher(voucher))
            {
                success = 2;
            }
            else 
            {
                success = 3;
            }

            reservation.calculatePrice(this, room);
        }
        
        return success;
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

    /**
     * Removes a reservation for a guest.
     * @param reservation the reservation to be removed
     * @return true if the reservation is removed successfully, false otherwise
     */
    public boolean removeReservation(Reservation reservation)
    {
        boolean success = false;

        if (reservation != null)
        {
            reservation.getRoom().removeReservedDate(reservation.getCheckInDate(), reservation.getCheckOutDate());
            this.reservationList.remove(reservation);
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
     * Finds a reservation based on the given information.
     * @param reservation A string containing information regarding the reservation (name | check-in - check-out | room number)
     * @return the reservation that matches the string
     */
    public Reservation findReservation(String reservation)
    {
        Reservation selectedReservation = null;
        
        try
        {
            String[] parts = reservation.split(" \\| | - ");
            String guestName = parts[0].trim();
            int checkIn = Integer.parseInt(parts[1].trim());
            int checkOut = Integer.parseInt(parts[2].trim());
            String room = parts[3].trim();

            int i;

            for (i = 0; i < getReservationList().size(); i++)
            {
                Reservation selectedRes = getReservationList().get(i);

                if (selectedRes.getGuestName().equals(guestName) && selectedRes.getCheckInDate() == checkIn &&
                    selectedRes.getCheckOutDate() == checkOut && selectedRes.getRoom().getName().equals(room))
                    {
                        selectedReservation = getReservationList().get(i);
                    }
            }
        }
        catch (Exception exception)
        {
            selectedReservation = null;
        }
        
    
        return selectedReservation;
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

    /**
     * Modifies the price multiplier for a given check-in date.
     * @param checkInDate the check-in date
     * @param datePriceMultiplier the price multiplier to set for the date
     */
    public void modifyDatePriceMultiplier(int checkInDate, double datePriceMultiplier)
    {
        this.datePriceModifier.set(checkInDate - 1, 1.0 * datePriceMultiplier / 100);
    }


    /**
     * Adds a new base room to the hotel.
     * The room name is generated as "S" followed by the next available number.
     */
    public void addBaseRoom()
    {
        String name = "S" + String.format("%d", this.nextBaseRoomNumber);
        roomList.add(new Room(name));

        this.nextBaseRoomNumber++;
    }

    /**
     * Adds a new deluxe room to the hotel.
     * The room name is generated as "D" followed by the next available number.
     */
    public void addDeluxeRoom()
    {
        String name = "D" + String.format("%d", this.nextDeluxeRoomNumber);
        roomList.add(new DeluxeRoom(name));

        this.nextDeluxeRoomNumber++;
    }

    /**
     * Adds a new executive room to the hotel.
     * The room name is generated as "E" followed by the next available number.
     */
    public void addExecutiveRoom()
    {
        String name = "E" + String.format("%d", this.nextExecutiveRoomNumber);
        roomList.add(new ExecutiveRoom(name));

        this.nextExecutiveRoomNumber++;
    }

    /**
     * Gets the price multiplier for a given day.
     * @param day the day 
     * @return the price multiplier for the given day
     */
    public double getDatePriceMultiplier(int day) 
    {
        return this.datePriceModifier.get(day - 1);
    }

    /**
     * Validates the voucher room details based on guest name and check-in/check-out dates.
     * @param guestName the name of the guest
     * @param checkInStr the check-in date as a string
     * @param checkOutStr the check-out date as a string
     * @return a result code indicating the validation status:
     *         0 - valid,
     *         1 - guest name is missing,
     *         2 - check-in or check-out date is invalid,
     *         3 - check-in date is out of range,
     *         4 - check-out date is out of range,
     *         5 - check-in date is not before check-out date
     */
    public int showVoucherRoom(String guestName, String checkInStr, String checkOutStr)
    {
        int result = 0;
        int checkIn, checkOut;

        if (guestName == null || guestName.isEmpty()) {
            result = 1;
        } 
        else if (checkInStr == null || checkInStr.isEmpty() || !checkInStr.matches("\\d+")
                || checkOutStr == null || checkOutStr.isEmpty() || !checkOutStr.matches("\\d+")) 
                {
                result = 2;
        }

        if (result == 0)
        {
            checkIn = Integer.parseInt(checkInStr);
            checkOut = Integer.parseInt(checkOutStr);

            if (checkIn < 1 || checkIn > 30) {
                result = 3;
            } else if (checkOut < 1 || checkOut > 31) {
                result = 4;
            } else if (checkIn >= checkOut) {
                result = 5;
            }
        }

        return result;
    }

}