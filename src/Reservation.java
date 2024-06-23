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
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate)
    {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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

    /**
     * Sets the room associated with this reservation.
     * @param room the room to set this reservation with
     */
    public void setRoom(Room room)
    {
        this.room = room;
    }


    /**
     * Prints information about this reservation.
     */
    public void printReservationInfo()
    {
        System.out.println("Name of guest: " + getGuestName());
        System.out.println("Room information: " + getRoom().getName());
        System.out.println("Check-in date: " + getCheckInDate());
        System.out.println("Check-out date: " + getCheckOutDate());
        System.out.println();
    }
}
