package test;

import java.util.ArrayList;

public class TestHotelRoomReservation {

    private static HRSystem sys = new HRSystem();

    public static void printcase(String name, int i) {
        System.out.println("testing " + String.valueOf(i) + ": " + name);
    }

    public static void print(boolean b) {
        System.out.println("return : " + b);
    }

    public static void print(String b) {
        System.out.println("return : " + b);
    }

    public static void print(double b) {
        System.out.println("return : " + b);
    }

    public static void print(int b) {
        System.out.println("return : " + b);
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
        sys.createHotel("A-Hotel", "24", "22", "1");
        sys.createHotel("B-Hotel", "0", "15", "15");
        sys.createHotel("C-Hotel", "1", "0", "0");
        Hotel hotel1 = sys.findHotelByName("A-Hotel");
        Hotel hotel2 = sys.findHotelByName("B-Hotel");

        /**
         * TESTING: HOTEL CLASS
         */
        System.out.println("HOTEL CLASS\n");

        /**
         * Testing 1-2: GET and SET for NAME and BASEPRICE
         */
        System.out.println("Tests 1-2: Checking if Setters work");
        hotel2.setName("Bida-Jabi");
        hotel2.setBasePrice(110);
        print(hotel2.getName());
        print(hotel2.getBasePrice());
        System.out.println("");

        /**
         * Testing 3-17: ROOM OPERATIONS
         */
        System.out.println("Tests 3-6: getNumOfRooms and subtype rooms");
        i = hotel1.getNumberOfRooms();
        print(i);
        i = hotel1.getNumberOfStandardRooms();
        print(i);
        i = hotel1.getNumberOfDeluxeRooms();
        print(i);
        i = hotel1.getNumberOfExecutiveRooms();
        print(i);
        System.out.println("");

        System.out.println("Test 7: getRoomList()");
        ArrayList<String> rooms = hotel1.getRoomList();
        for (String room : rooms) {
            System.out.print(room + " ");
        }
        System.out.println("\n");

        System.out.println("Test 8: addBaseRoom()");
        hotel1.addBaseRoom();
        print(hotel1.getNumberOfStandardRooms());
        System.out.println("");

        System.out.println("Test 9: addDeluxeRoom()");
        hotel1.addDeluxeRoom();
        print(hotel1.getNumberOfDeluxeRooms());
        System.out.println("");

        System.out.println("Test 10: addExecutiveRoom()");
        hotel1.addExecutiveRoom();
        print(hotel1.getNumberOfExecutiveRooms());
        System.out.println("");

        System.out.println("Test 11: removeRoom() valid");
        hotel1.removeRoom("S25");
        rooms = hotel1.getRoomList();
        for (String room : rooms) {
            System.out.print(room + " ");
        }
        System.out.println("\n");

        System.out.println("Test 12: removeRoom() null");
        b = hotel1.removeRoom("S25");
        print(b);
        System.out.println("");

        System.out.println("Test 13: findRoomByName() valid");
        Room room = hotel1.findRoomByName("E2");
        print(room.getName());
        System.out.println("");

        System.out.println("Test 14: findRoomByName() null");
        room = hotel1.findRoomByName("E3");
        print(room == null);
        System.out.println("");
        
        System.out.println("Tests 15-17: getRoomType() for all three types");
        room = hotel1.findRoomByName("S1");
        print(hotel1.getRoomType(room));
        print(hotel1.getRoomPrice(room));
        room = hotel1.findRoomByName("D1");
        print(hotel1.getRoomType(room));
        print(hotel1.getRoomPrice(room));
        room = hotel1.findRoomByName("E1");
        print(hotel1.getRoomType(room));
        print(hotel1.getRoomPrice(room));
        System.out.println("");

        /**
         * Testing 18-19: Other Room Prices
         */
        System.out.println("Tests 18-19: getDeluxePrice and getExecutivePrice rooms");
        print(hotel1.getDeluxePrice());
        print(hotel1.getExecutivePrice());
        System.out.println("");

        /**
         * Testing 20- : RESERVATION OPERATIONS
         */
        System.out.println("Test 20: create valid reservation");
        room = hotel1.findRoomByName("S1");
        i = hotel1.createReservation("Enzo", "12", "13", room, null);
        System.out.println(i);
        for (Reservation reservation : hotel1.getReservationList()) 
            print(reservation.getGuestName() + reservation.getCheckInDate() + reservation.getCheckOutDate() + reservation.getRoom().getName());
        System.out.println("");

        System.out.println("Test 21: create overlapping reservation");
        room = hotel1.findRoomByName("S1");
        i = hotel1.createReservation("Diane", "12", "13", room, null);
        System.out.println(i);
        System.out.println(hotel1.getReservationString());
        System.out.println("");

        System.out.println("Test 22: invalid date");
        room = hotel1.findRoomByName("S2");
        i = hotel1.createReservation("Diane", "12", "32", room, null);
        System.out.println(i);
        System.out.println("");
       
        System.out.println("Test 23: invalid voucher");
        room = hotel1.findRoomByName("S2");
        i = hotel1.createReservation("Diane", "16", "17", room, "STAY4_GET1");
        System.out.println(i);
        System.out.println("");

        System.out.println("Test 24: valid voucher");
        room = hotel1.findRoomByName("S4");
        i = hotel1.createReservation("Enzo", "14", "20", room, "PAYDAY");
        System.out.println(i);
        System.out.println("");

        System.out.println("Test 25-26: Display multiple reservations");
        room = hotel1.findRoomByName("S9");
        hotel1.createReservation("Diane", "14", "15", room, null);
        for (Reservation reservation : hotel1.getReservationList()) 
            print(reservation.getGuestName() + reservation.getCheckInDate() + reservation.getCheckOutDate() + reservation.getRoom().getName());
        System.out.println(hotel1.getReservationString());
        System.out.println("");

        System.out.println("Test 27: Remove valid reservation");
        room = hotel1.findRoomByName("S1");
        b = hotel1.removeReservation("Enzo", 12, 13, room);
        print(b);
        System.out.println(hotel1.getReservationString());
        System.out.println("");
        
        System.out.println("Test 28: Remove invalid reservation");
        b = hotel1.removeReservation("Enzo", 12, 13, room);
        print(b);
        System.out.println("");
        
        System.out.println("Test 29: Remove valid reservation w/ reservation object");
        Reservation reservation = hotel1.findReservation("Enzo | 14 - 20 | S4");
        b = hotel1.removeReservation(reservation);
        print(b);
        System.out.println("");

        System.out.println("Test 30: Remove invalid reservation w/ reservation object");
        reservation = hotel1.findReservation("Enzo | 14 - 20 | S4");
        b = hotel1.removeReservation(reservation);
        print(b);
        System.out.println("");

        /**
         * Testing 31-34 : Room Availability and Date Price Modifier
         */
        System.out.println("Test 31: getAvailableRooms()");
        i = hotel1.getAvailableRooms(14);
        print(i);
        System.out.println("");

        System.out.println("Test 32: getBookedRooms()");
        i = hotel1.getBookedRooms(14);
        print(i);
        System.out.println("");

        System.out.println("Test 33: filterAvailableRooms()");
        for (String r :  hotel1.filterAvailableRooms(14, 15)) {
            System.out.print(r + " ");
        }
        System.out.println("\n");

        System.out.println("Test 34: Date Price Modifier");
        hotel1.modifyDatePriceMultiplier(14, 50);
        d = hotel1.getDatePriceMultiplier(14);
        print(d);
        System.out.println("");

        /**
         * Testing 35-340 : MISCELLANEOUS - get total price/earnings, check date validity for 
         */
        // generate reservations
        room = hotel2.findRoomByName("D4");
        hotel2.createReservation("Kirby", "12", "18", room, "STAY4_GET1");
        room = hotel2.findRoomByName("E5");
        hotel2.createReservation("Yoru", "15", "16", room, "PAYDAY");
       
        System.out.println("Test 35: Get Total Earnings - hotel1");
        d = hotel1.getTotalReservationEarnings();
        print(d);
        System.out.println("");

        System.out.println("Test 36: Get Total Earnings - hotel2");
        d = hotel2.getTotalReservationEarnings();
        print(d);
        System.out.println("");

        System.out.println("Test 37-42: Get Total Earnings - hotel2");
        i = hotel1.showVoucherRoom("Enzo", "1", "3"); System.out.println(i);
        i = hotel1.showVoucherRoom(null, "1", "3"); System.out.println(i);
        i = hotel1.showVoucherRoom("Enzo", "hello", "2"); System.out.println(i);
        i = hotel1.showVoucherRoom("Enzo", "0", "3"); System.out.println(i);
        i = hotel1.showVoucherRoom("Enzo", "1", "32"); System.out.println(i);
        i = hotel1.showVoucherRoom("Enzo", "5", "3"); System.out.println(i);
        System.out.println("");





        /**
         * TESTING: ROOM CLASS
         */
        System.out.println("ROOM CLASS\n");

        room = hotel1.findRoomByName("S2");

        System.out.println("Test 1: getName()");
        print(room.getName());
        System.out.println("");
        
        System.out.println("Test 2: getReservedDates()");
        for (int r : room.getReservedDates())
            print(r);
        System.out.println("");
        
        System.out.println("Test 3-4: getAvailability : solo date");
        print(room.getAvailability(17));
        print(room.getAvailability(16));
        System.out.println("");

        System.out.println("Test 5-6: getAvailability : dual date");
        print(room.getAvailability(14, 25));
        print(room.getAvailability(17, 20));
        System.out.println("");

        System.out.println("Test 7: bookRoom");
        room.bookRoom(15, 16);
        for (int r : room.getReservedDates())
            print(r);
        System.out.println("");

        System.out.println("Test 8: removeReservedDate");
        room.removeReservedDate(16, 17);
        for (int r : room.getReservedDates())
            print(r);
        System.out.println("");
        
        System.out.println("Test 9: isFullyBooked");
        print(room.isFullyBooked());
        System.out.println("");

        System.out.println("Test 10: isFullyBooked");
        room.bookRoom(1, 15);
        room.bookRoom(16, 31);
        print(room.isFullyBooked());
        System.out.println("");





        /**
         * TESTING: RESERVATION CLASS
         */
        System.out.println("RESERVATION CLASS\n");

        Reservation r1 = hotel2.getReservationList().get(0); // Kirby 12-18 D4 STAY4_GET1
        Reservation r2 = hotel2.getReservationList().get(1); // Yoru 15-16 E5 PAYDAY
        
        System.out.println("Test 1: getGuestName()");
        print(r1.getGuestName());
        System.out.println("");

        System.out.println("Test 2: getCheckInDate()");
        print(r1.getCheckInDate());
        System.out.println("");

        System.out.println("Test 3: getCheckOutDate()");
        print(r1.getCheckOutDate());
        System.out.println("");

        System.out.println("Test 4: getRoom()");
        print(r1.getRoom().getName());
        System.out.println("");

        System.out.println("Test 5: calculatePrice(), getTotalPrice()");
        r1.calculatePrice(hotel2, r1.getRoom());
        System.out.println(r1.getTotalPrice());
        System.out.println("");

        System.out.println("Test 6-11: enterVoucher()");
        System.out.println(r1.enterVoucher(""));
        System.out.println(r1.enterVoucher("I_WORK_HERE"));
        System.out.println(r1.enterVoucher("PAYDAY"));
        System.out.println(r2.enterVoucher("PAYDAY"));
        System.out.println(r1.enterVoucher("STAY4_GET1"));
        System.out.println(r2.enterVoucher("STAY4_GET1"));
        System.out.println("");

        System.out.println("Test 6-11: getBreakdown()");
        ArrayList<String> priceBreakdown= r1.getBreakdown(hotel2, r1.getRoom());
        for (String pricePerDay : priceBreakdown) {
            System.out.print(pricePerDay + " | ");
        }
        System.out.println("");


    }
}
