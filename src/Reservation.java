/**
 * Represents a hotel reservation.
 */
public class Reservation
{
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room room;

    // CONSTRUCTORS
    /**
     * Constructs a Reservation with the provided guest name, check-in date, and check-out date.
     * @param guestName the name of the guest
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @param room the room to be reserved
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room)
    {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
    }

    // GETTERS AND SETTERS
    /**
     * Gets the name of the guest.
     * @return the guest's name
     */
    public String getGuestName()
    {
        return this.guestName;
    }

    /**
     * Gets the check-in date.
     * @return the check-in date
     */
    public int getCheckInDate()
    {
        return this.checkInDate;
    }

    /**
     * Gets the check-out date.
     * @return the check-out date
     */
    public int getCheckOutDate()
    {
        return this.checkOutDate;
    }

    /**
     * Gets the room associated with this reservation.
     * @return the room
     */
    public Room getRoom()
    {
        return this.room;
    }
}
