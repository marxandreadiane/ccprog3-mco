import java.util.ArrayList;

/**
 * Represents a room in a hotel.
 */
public class Room
{
    private String name;
    private ArrayList<Integer> reservedDates = new ArrayList<Integer>();

    // CONSTRUCTORS
    /**
     * Constructs a Room with the specified name.
     * @param name the name of the room
     */
    public Room(String name)
    {
        this.name = name;
    }

    // GETTERS AND SETTERS
    /**
     * Gets the name of this room.
     * @return the name of this room
     */
    public String getName()
    {
        return this.name;
    }

    // AVAILABILITY METHODS
    /**
     * Checks if this room is available on the given date.
     * @param checkInDate the date to check availability for
     * @return true if this room is available, false otherwise
     */
    public boolean getAvailability(int checkInDate)
    {
        boolean available = true;
        int tempDate = checkInDate;

        if (reservedDates.contains(tempDate))
            {
                available = false;
            }

        return available;
    }

    /**
     * Checks if this room is available for the given date range.
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return true if this room is available for the entire date range, false otherwise
     */
    public boolean getAvailability(int checkInDate, int checkOutDate)
    {
        boolean available = true;
        int tempDate = checkInDate;

        while (available && tempDate < checkOutDate)
        {
            if (reservedDates.contains(tempDate))
            {
                available = false;
            }

            tempDate++;
        }

        return available;
    }

    /**
     * Books this room for the given date range.
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     */
    public void bookRoom(int checkInDate, int checkOutDate)
    {
        int tempDate = checkInDate; 

        while (tempDate < checkOutDate)
        {
            reservedDates.add(tempDate);
    
            tempDate++;
        }
    }

    /**
     * Removes the specified reserved dates from the list
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     */
    public void removeReservedDate(int checkInDate, int checkOutDate)
    {
        Integer tempDate = checkInDate; 

        while (tempDate < checkOutDate)
        {
            reservedDates.remove(tempDate);
    
            tempDate++;
        }
    }

    /**
     * Prints the available dates for this room.
     */
    public void printAvailableDates()
    {
        int i;

        for (i = 1; i < 31; i++)
        {
            if (!reservedDates.contains(i))
            {
                System.out.print(i + " ");
            }
        }
        System.out.print("\n");
    }

    /**
     * Checks if this room is fully booked for the month.
     * @return true if this room is fully booked, false otherwise
     */
    public boolean isFullyBooked()
    {
        return reservedDates.size() == 30;
    }
}
