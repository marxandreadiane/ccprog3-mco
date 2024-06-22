import java.util.ArrayList;

public class Room
{
    private String name;
    private double price = 1299.0;
    private ArrayList<Integer> reservedDates = new ArrayList<Integer>();

    public Room(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

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

    public void bookRoom(int checkInDate, int checkOutDate)
    {
        int tempDate = checkInDate; 

        while (tempDate < checkOutDate)
        {
            reservedDates.add(tempDate);
    
            tempDate++;
        }
    }

    public void printAvailableDates()
    {
        int i;

        for (i = 1; i < 31; i++)
        {
            if (!reservedDates.contains(i))
            {
                System.out.print("i ");
            }
        }
        System.out.print("\n");
    }

    public boolean isFullyBooked()
    {
        return reservedDates.size() == 30;
    }
}