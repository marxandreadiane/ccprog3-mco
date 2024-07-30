package test;

import java.util.ArrayList;

public class TestHotelRoomReservation {

    private static HRSystem sys = new HRSystem();

    public static void printcase(String name, int i) {
        System.out.println("testing " + String.valueOf(i) + ": " + name);
    }

    public static void print(boolean b) {
        System.out.println("return : " + b + "\n");
    }

    public static void print(String b) {
        System.out.println("return : " + b + "\n");
    }

    public static void print(double b) {
        System.out.println("return : " + b + "\n");
    }

    public static void print(int b) {
        System.out.println("return : " + b + "\n");
    }

    public static void printlines() {
        System.out.println("\n=============================\n\n");
    }

    public static void main(String[] args) {

        // Variable Initialization
        boolean b;
        int i;
        double d;
        String s;
        
        // System Initialization
        sys.createHotel("A-Hotel", 49);
        sys.createHotel("B-Hotel", 30);
        sys.createHotel("C-Hotel", 1);
        Hotel hotel1 = sys.findHotelByName("A-Hotel");
        Hotel hotel2 = sys.findHotelByName("B-Hotel");
       
        printlines();

        /**
         * TESTING: HOTEL CLASS
         */

        /**
         * Testing 1-3: set/get name/basePrice
         */
        hotel2.setName("Bida-Jabi");
        hotel2.setBasePrice(110);
        printcase("getsetnamebasePrice : name", 1);
        print(hotel2.getName());
        printcase("getsetnamebasePrice : price", 2);
        print(hotel2.getBasePrice());

        /**
         * Testing 4-10 Create, Add, Count, Find Rooms
         */
        printcase("getNumOfRooms()", 3);
        i = hotel1.getNumberOfRooms();
        print(i);

        printcase("getRoomList()", 4);
        ArrayList<String> rooms = hotel1.getRoomList();
        for (String room : rooms) {
            print(room);
        }

        printcase("addRoom()", 5);
        hotel1.addRoom();
        print(hotel1.getNumberOfRooms());

        printcase("addRoom() : restriction", 6);
        hotel1.addRoom();
        print(hotel1.getNumberOfRooms());

        printcase("removeRoom()", 7);
        hotel1.removeRoom("A47");
        rooms = hotel1.getRoomList();
        for (String room : rooms) {
            print(room);
        }

        printcase("removeRoom() : null", 8);
        b = hotel1.removeRoom("A47");
        print(b);

        printcase("findRoomByName()", 9);
        Room room = hotel1.findRoomByName("A8");
        print(room.getName());

        printcase("findRoomByName()", 10);
        room = hotel1.findRoomByName("A47");
        print(room == null);

        /**
         * Testing 11-16 : Create, Remove and Find Reservations
         */
        room = hotel1.findRoomByName("A8");
        printcase("createReservation() and getReservationList", 11);
        hotel1.createReservation("Enzo", 12, 13, room);
        for (Reservation reservation : hotel1.getReservationList()) 
            print(reservation.getGuestName() + reservation.getCheckInDate() + reservation.getCheckOutDate() + reservation.getRoom().getName());

        room = hotel1.findRoomByName("A9");
        printcase("createReservation() and getReservationList : multiple", 12);
        hotel1.createReservation("Diane", 12, 13, room);
        for (Reservation reservation : hotel1.getReservationList()) 
            print(reservation.getGuestName() + reservation.getCheckInDate() + reservation.getCheckOutDate() + reservation.getRoom().getName());

        room = hotel1.findRoomByName("A8");
        printcase("removeReservation", 13);
        hotel1.removeReservation("Enzo", 12, 13, room);
        for (Reservation reservation : hotel1.getReservationList()) 
            print(reservation.getGuestName() + reservation.getCheckInDate() + reservation.getCheckOutDate() + reservation.getRoom().getName());
        
        printcase("removeReservation : null", 14);
        b = hotel1.removeReservation("Enzo", 12, 13, room);
        print(b);

        // generate reservations
        room = hotel1.findRoomByName("A8");
        hotel1.createReservation("Enzo", 15, 18, room);
        room = hotel1.findRoomByName("A11");
        hotel1.createReservation("CJ", 14, 17, room);
        room = hotel1.findRoomByName("A9");
        hotel1.createReservation("Enzo", 15, 18, room);
       
        
        printcase("filterReservationByName()", 15);
        for (Reservation reservation : hotel1.filterReservationByName("Enzo"))
            print(reservation.getGuestName() + reservation.getCheckInDate() + reservation.getCheckOutDate() + reservation.getRoom().getName());
        
        printcase("filterReservationByName() : null", 16);
        print(hotel1.filterReservationByName("ENzo").size() == 0);


        /**
         * Testing 17-19 : Room Availability
         */
        printcase("getAvailableRooms()", 17);
        i = hotel1.getAvailableRooms(12);
        print(i);

        printcase("getBookedRooms()", 18);
        i = hotel1.getBookedRooms(12);
        print(i);

        printcase("filterAvailableRooms()", 19);
        for (String r :  hotel1.filterAvailableRooms(12, 13)) {
            print(r);
        }

        /**
         * Testing 20-21 : Miscellaneous
         */
        // generate reservations
        room = hotel2.findRoomByName("B2");
        hotel2.createReservation("Kirby", 12, 18, room);
        room = hotel2.findRoomByName("B18");
        hotel2.createReservation("Yoru", 15, 16, room);
       
        printcase("getHighLevel()", 20);
        d = hotel1.getTotalReservationEarnings();
        print(d);

        printcase("getHighLevel()", 21);
        d = hotel2.getTotalReservationEarnings();
        print(d);

        printlines();

        /**
         * TESTING: ROOM CLASS
         */
        room = hotel1.findRoomByName("A8");

        printcase("getName()", 1);
        print(room.getName());
        
        printcase("getReservedDates()", 2);
        for (int r : room.getReservedDates())
            print(r);
        
        printcase("getAvailability : solo date", 3);
        print(room.getAvailability(18));

        printcase("getAvailability : solo date", 4);
        print(room.getAvailability(17));

        printcase("getAvailability : dual date", 5);
        print(room.getAvailability(14, 25));

        printcase("getAvailability : dual date", 6);
        print(room.getAvailability(18, 20));

        printcase("bookRoom", 7);
        room.bookRoom(11, 13);
        for (int r : room.getReservedDates())
            print(r);

        printcase("removeReservedDate", 8);
        room.removeReservedDate(11, 13);
        for (int r : room.getReservedDates())
            print(r);
        
        printcase("isFullyBooked", 9);
        print(room.isFullyBooked());

        printcase("isFullyBooked", 10);
        room.bookRoom(1, 15);
        room.bookRoom(18, 31);
        print(room.isFullyBooked());
        
        /**
         * TESTING: RESERVATION CLASS
         */

        printlines();

        Reservation reservation = hotel2.getReservationList().get(0);
        
        printcase("getGuestName()", 1);
        print(reservation.getGuestName());

        printcase("getCheckInDate()", 2);
        print(reservation.getCheckInDate());

        printcase("getCheckOutDate()", 3);
        print(reservation.getCheckOutDate());

        printcase("getRoom()", 4);
        print(reservation.getRoom().getName());
        
    }
}
