package com;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

public class Export
{
    private HRSystem hrSystem;
    private FileWriter filename;

    public Export(HRSystem hrSystem, String textfile)
    {
        this.hrSystem = hrSystem;

        try {
            this.filename = new FileWriter(textfile);
            this.filename.write("- - - HOTEL RESERVATION SYSTEM DATA - - -");

            for (Hotel hotel : hrSystem.getHotelList())
            {
                getHotelInfo(hotel);
            }

            this.filename.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }

    private void getHotelInfo(Hotel hotel) throws IOException
    {
        String hotelName = hotel.getName();
        int totalRooms = hotel.getNumberOfRooms();
        int standardRooms = hotel.getNumberOfStandardRooms();
        int deluxeRooms = hotel.getNumberOfDeluxeRooms();
        int executiveRooms = hotel.getNumberOfExecutiveRooms();
        double basePrice = hotel.getRoomPrice(new Room("1"));
        double deluxePrice = hotel.getRoomPrice(new DeluxeRoom("2"));
        double executivePrice = hotel.getRoomPrice(new ExecutiveRoom("3"));

        ArrayList<String> roomList = hotel.getRoomList();
        ArrayList<String> reservationList = hotel.getReservationString();

        double totalEarnings = hotel.getTotalReservationEarnings();

        filename.write("\n\n\n- - - - - - - - - - - - - - - - - - - \n");
        filename.write("Name of the Hotel: " + hotelName + "\n\n");
        filename.write("Total Number of Rooms: " + totalRooms + "\n\n");
        filename.write("Number of Standard Rooms: " + standardRooms + "\n");
        filename.write("Number of Deluxe Rooms: " + deluxeRooms + "\n");
        filename.write("Number of Executive Rooms: " + executiveRooms + "\n\n");
        filename.write("Price of Standard Rooms: " + basePrice + "\n");
        filename.write("Price of Deluxe Rooms: " + deluxePrice + "\n");
        filename.write("Price of Executive Rooms: " + executivePrice + "\n\n");
        filename.write("\nTotal reservation earnings: " + totalEarnings +"\n\n");

        filename.write("- - - List of Rooms - - -\n\n");

        for (String room : roomList)
        {
            filename.write("> " + room);
            filename.write("    (Reserved on dates:");

            for (int date : hotel.findRoomByName(room).getReservedDates())
            {
                filename.write(" " + date);
            }
            filename.write(")\n");
        }


        filename.write("\n\n- - - List of Reservations - - -\n\n");

        if (!reservationList.isEmpty())
        {
            for (String reservation : reservationList)
            {
                Reservation res = hotel.findReservation(reservation);
                filename.write("> " + reservation + " | " + String.format("%.2f", res.getTotalPrice()) + "\n");
            }
        }
        else
        {
            filename.write("NO RESERVATIONS MADE.\n");
        }

        filename.write("\n\n- - - Date-Price Modifier - - -\n\n");

        for (int i = 1; i < 31; i++)
        {
            filename.write("> " + i + " : " + hotel.getDatePriceMultiplier(i) + "\n");
        }
        
    }
}