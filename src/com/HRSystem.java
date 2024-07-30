package com;
import java.util.ArrayList;
import javax.lang.model.util.ElementScanner14;

/**
 * Represents a Hotel Reservation System that manages multiple hotels.
 */
public class HRSystem {

    private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    // CONSTRUCTOR - none; is set to default HRSystem().

    // SPECIFIC GETTERS/FINDERS
    /** 
     * Gets the list of hotels in this hotel reservation system
     * @return the list of hotels in this system
     */
    public ArrayList<Hotel> getHotelList()
    {
        return this.hotelList;
    }

    /** 
     * Gets the names of hotels in this hotel reservation system
     * @return the names of hotels in this system
     */
    public ArrayList<String> getHotelNames()
    {
        ArrayList<String> names = new ArrayList<String>();

        for (Hotel hotel : hotelList)
        {
            names.add(hotel.getName());
        }
        
        return names;
    }

    // ADD, REMOVE and SEARCH HOTEL METHODS
    /**
     * Creates a new hotel with the specified name.
     * @param name the name of the new hotel
     * @param numOfRooms the number of rooms to be created along with the hotel
     * @return true if the hotel is created successfully, false if the hotel already exists
     */
    public int createHotel(String name, String standard, String deluxe, String executive)
    {
        int success = 0;
        int numOfBaseRooms, numOfDeluxeRooms, numOfExecutiveRooms, newRooms;

        if (!(name == null || name.equals("")) 
            && standard != null && standard.matches("\\d+")
            && deluxe != null && deluxe.matches("\\d+")
            && executive != null && executive.matches("\\d+"))
        {
            numOfBaseRooms = Integer.parseInt(standard);
            numOfDeluxeRooms = Integer.parseInt(deluxe);
            numOfExecutiveRooms = Integer.parseInt(executive);
            newRooms = numOfBaseRooms + numOfDeluxeRooms + numOfExecutiveRooms;
        }
        else
        {
            return success;
        }

        if (findHotelByName(name) != null)
        {
            success = 1;
        }
        else if (!(newRooms <= 50 && newRooms >= 1))
        {
            success = 2;
        }
        else if (!(numOfBaseRooms >= 0 && numOfDeluxeRooms >= 0 && numOfExecutiveRooms >= 0))
        {
            success = 3;
        }
        else
        {
            hotelList.add(new Hotel(name, numOfBaseRooms, numOfDeluxeRooms, numOfExecutiveRooms));
            success = 4;
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
        Hotel hotel = findHotelByName(name);
        boolean success = false;

        if (hotel != null && hotel.filterAvailableRooms(1, 31).size() == hotel.getNumberOfRooms())
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
     * Validates the addition of rooms to the hotel.
     * @param hotel the hotel to which rooms are to be added
     * @param baseStr the number of base rooms as a string
     * @param deluxeStr the number of deluxe rooms as a string
     * @param executiveStr the number of executive rooms as a string
     * @return a result code indicating the validation status:
     *         0 - invalid input,
     *         1 - hotel already has 50 rooms,
     *         2 - total rooms exceed the limit,
     *         3 - no rooms to add,
     *         4 - invalid room count,
     *         5 - valid input
     */
    public int isAddRoomsValid(Hotel hotel, String baseStr, String deluxeStr, String executiveStr)
    {
        int success = 0;
        int base, deluxe, executive, newRooms;

        if (baseStr != null && baseStr.matches("\\d+") 
            && deluxeStr != null && deluxeStr.matches("\\d+")
            && executiveStr != null && executiveStr.matches("\\d+"))
        {
            base = Integer.parseInt(baseStr);
            deluxe = Integer.parseInt(deluxeStr);
            executive = Integer.parseInt(executiveStr);
            newRooms = base + deluxe + executive;
        }
        else
        {
            return success;
        }
        
        if (hotel.getNumberOfRooms() == 50)
        {
            success = 1;
        } 
        else if (!(newRooms <= 50 - hotel.getNumberOfRooms()))
        {
            success = 2;
        }
        else if (!(base + deluxe + executive > 0))
        {
            success = 3;
        }
        else if (!(base >= 0 && deluxe >= 0 && executive >= 0))
        {
            success = 4;
        }
        else
        {
            success = 5;
        }
        
        return success;
    }

    /**
     * Adds rooms to the hotel.
     * @param hotel the hotel to which rooms are to be added
     * @param baseStr the number of base rooms as a string
     * @param deluxeStr the number of deluxe rooms as a string
     * @param executiveStr the number of executive rooms as a string
     */
    public void addRooms(Hotel hotel, String baseStr, String deluxeStr, String executiveStr)
    {
        int i;
        int base = Integer.parseInt(baseStr);
        int deluxe = Integer.parseInt(deluxeStr);
        int executive = Integer.parseInt(executiveStr);

        for (i = 0; i < base; i++)
        {
            hotel.addBaseRoom();
        }
        for (i = 0; i < deluxe; i++)
        {
            hotel.addDeluxeRoom();
        }
        for (i = 0; i < executive; i++)
        {
            hotel.addExecutiveRoom();
        }
    }

    /**
     * Removes a room from the hotel.
     * @param hotel the hotel from which the room is to be removed
     * @param room the name of the room to be removed
     * @return true if the room was successfully removed, false otherwise
     */
    public boolean removeRoom(Hotel hotel, String room)
    {
        boolean success = false;
        if (hotel.getNumberOfRooms() > 1 && hotel.findRoomByName(room) != null && hotel.findRoomByName(room).getAvailability(1, 31))
        {
            success = true;
            hotel.removeRoom(room);
        }

        return success;
    }

    /**
     * Sets the base price for the hotel rooms.
     * @param hotel the hotel for which the base price is to be set
     * @param price the new base price
     * @return true if the base price was successfully set, false otherwise
     */
    public boolean setBasePrice(Hotel hotel, Double price)
    {
        boolean success = false;

        if (price >= 100 && hotel.filterAvailableRooms(1, 31).size() == hotel.getNumberOfRooms())
        {
            success = true;
            hotel.setBasePrice(price);
        }

        return success;
    }

    /**
     * Removes a reservation from the hotel.
     * @param hotel the hotel from which the reservation is to be removed
     * @param guestName the name of the guest
     * @param checkIn the check-in date
     * @param checkOut the check-out date
     * @param room the room reserved
     * @return true if the reservation was successfully removed, false otherwise
     */
    public boolean removeReservation(Hotel hotel, String guestName, int CheckIn, int CheckOut, Room room)
    {
        boolean success = false;

        if (hotel.removeReservation(guestName, CheckIn, CheckOut, room))
        {
            success = true;
        }

        return success;
    }

    /**
     * Modifies the price multiplier for a specific date.
     * @param hotel the hotel for which the price multiplier is to be modified
     * @param dateStr the date as a string
     * @param priceModifierStr the price multiplier as a string
     */
    public void modifyMultiplier(Hotel hotel, String dateStr, String priceModifierStr)
    {
        int date = Integer.parseInt(dateStr);
        double priceModifier = Double.parseDouble(priceModifierStr);;
        
        hotel.modifyDatePriceMultiplier(date, priceModifier);
    }

    /**
     * Validates the modification of the price multiplier.
     * @param hotel the hotel for which the price multiplier is to be modified
     * @param dateStr the date as a string
     * @param priceModifierStr the price multiplier as a string
     * @return a result code indicating the validation status:
     *         0 - valid input,
     *         1 - invalid date,
     *         2 - invalid price multiplier
     */
    public int isModifyModifierValid(Hotel hotel, String dateStr, String priceModifierStr)
    {
        int result = 0;
        int date;
        double priceModifier;

        try
        {
            date = Integer.parseInt(dateStr);
            if (!(date >= 1 && date < 31))
            {
                result = 1;
            }
        } 
        catch(Exception exception)
        {
            result = 1;
        }

        try
        {
            priceModifier = Double.parseDouble(priceModifierStr);
            if (!(priceModifier >= 50 && priceModifier <= 150))
            {
                result = 2;
            }
        } 
        catch(Exception exception)
        {
            result = 2;
        }

        return result;
    }

    /**
     * Changes the name of the hotel.
     * @param hotel the hotel whose name is to be changed
     * @param name the new name for the hotel
     * @return true if the name was successfully changed, false otherwise
     */
    public boolean changeHotelName(Hotel hotel, String name)
    {
        boolean success = false;

        if (findHotelByName(name) == null)
        {
            hotel.setName(name);
        }

        return success;
    }
}