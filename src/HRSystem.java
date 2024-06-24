import java.util.ArrayList;

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


}
