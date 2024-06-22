public class Reservation
{
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room room;
    private double total;

    public Reservation(String guestName, int checkInDate, int checkOutDate)
    {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getGuestName()
    {
        return this.guestName;
    }

    public int getCheckInDate()
    {
        return this.checkInDate;
    }

    public int getCheckOutDate()
    {
        return this.checkOutDate;
    }

    public Room getRoom()
    {
        return this.room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public double getTotal()
    {
        return (this.checkOutDate - this.checkInDate) * this.room.getPrice();
    }

    public void printReservationInfo()
    {
        System.out.print("Name of guest: " + getGuestName());
        System.out.print("Room information: " + getRoom().getName());
        System.out.print("Check-in date: " + getCheckInDate());
        System.out.println("Check-out date: " + getCheckOutDate());
        System.out.println("Total price: " + getTotal());
        System.out.println("Price per night: " + getRoom().getPrice());
    }
}