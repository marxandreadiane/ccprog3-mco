package test;

public class TestHRSystem {
    private static HRSystem sys = new HRSystem();
    private static SystemView sysview = new SystemView();

    public static void printcase(String name, int i) {
        System.out.println("testing " + String.valueOf(i) + ": " + name);
    }

    public static void print(boolean b) {
        System.out.println("return : " + b + "\n");
    }

    public static void printlines() {
        System.out.println("=============================\n");
    }

    public static void main(String[] args) {

        boolean b;
        int i;
        double d;
        Hotel h = null;

        /**
         * Testing 1: createHotel()
         */
        System.out.println("Test Cases 1-4: createHotel()\n");

        System.out.println("Test 1: 20 20 -10, should return 0");
        i = sys.createHotel("A-Hotel", "20", "20", "-10");
        sysview.displayHotels(sys.getHotelList());
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 2: 20 20 10, should return 0");
        i = sys.createHotel("A-Hotel", "20", "20", "10");
        sysview.displayHotels(sys.getHotelList());
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 3: 20 20 10, should return 0");
        i = sys.createHotel("B-Hotel", "20", "20", "20");
        sysview.displayHotels(sys.getHotelList());
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 4: 20 20 10, should return 0");
        i = sys.createHotel("A-Hotel", "20", "20", "10");
        System.out.println("Return : " + i);

        printlines();

        /**
         * Testing 2: removeHotel()
         */
        // initialization
        sys.createHotel("B-Hotel", "0", "20", "10");
        sys.createHotel("C-Hotel", "1", "0", "0");

        System.out.println("Test Cases 5-6: removeHotel()\n");

        System.out.println("Initialization: All Hotels");
        sysview.displayHotels(sys.getHotelList());
        System.out.println("\n");

        System.out.println("Test 5: Remove B-Hotel, should return True");
        b = sys.removeHotel("B-Hotel");
        sysview.displayHotels(sys.getHotelList());
        System.out.println("Return : " + b + "\n");
        
        System.out.println("Test 6: Remove non-existent hotel, should return False");
        b = sys.removeHotel("D-Hotel");
        sysview.displayHotels(sys.getHotelList());
        System.out.println("Return : " + b + "\n");

        printlines();

        /**
         * Testing 3: findHotelbyName()
         */
        System.out.println("Test Cases 7-8: removeHotel()\n");

        System.out.println("Test 7: Find C-Hotel, should return C-Hotel and its info");
        h = sys.findHotelByName("C-Hotel");
        System.out.println("name: " + h.getName() + "  rooms: " + String.valueOf(h.getNumberOfRooms()) + "\n");

        System.out.println("Test 8: Find D-Hotel, h.equals(null) should return true");
        h = sys.findHotelByName("D-Hotel");
        System.out.println(h == null);

        printlines();

        /**
         * Testing 4: isAddRoomsValid()
         */
        System.out.println("Test Cases 9-12: isAddRoomsValid()\n");

        System.out.println("Test 9: Add rooms to a hotel with 0 rooms, should return 5");
        h = new Hotel("TestHotel1", 0, 0, 0);
        i = sys.isAddRoomsValid(h, "10", "10", "10");
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 10: Add rooms to a hotel with 50 rooms, should return 1");
        h = new Hotel("TestHotel2", 20, 20, 10);
        i = sys.isAddRoomsValid(h, "5", "5", "5");
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 11: Add rooms exceeding 50 limit, should return 2");
        h = new Hotel("TestHotel3", 20, 20, 5);
        i = sys.isAddRoomsValid(h, "10", "10", "10");
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 12: Add negative rooms, should return 4 (or not 5)");
        h = new Hotel("TestHotel4", 20, 20, 5);
        i = sys.isAddRoomsValid(h, "-10", "10", "10");
        System.out.println("Return : " + i + "\n");

        printlines();

        /**
         * Testing 5: addRooms()
         */
        System.out.println("Test Cases 13: addRooms()\n");

        System.out.println("Test 5: Add 15 rooms to a hotel with 20 rooms, should return 35");
        h = new Hotel("TestHotel5", 10, 5, 5);
        sys.addRooms(h, "5", "5", "5");
        System.out.println(h.getNumberOfRooms());

        printlines();

        /**
         * Testing 6: removeRoom()
         */
        System.out.println("Test Cases 14-15: removeRoom()\n");

        System.out.println("Test 14: Remove a room that exists, should return true");
        h = new Hotel("TestHotel6", 10, 5, 5);
        b = sys.removeRoom(h, "S1");
        System.out.println("Return : " + b + "\n");

        System.out.println("Test 15: Remove a room that does not exist, should return false");
        b = sys.removeRoom(h, "S11");
        System.out.println("Return : " + b + "\n");

        printlines();

         /**
         * Testing 7: setBasePrice()
         */
        System.out.println("Test Cases 16-17: setBasePrice()\n");

        System.out.println("Test 16: Set base price to a valid amount, should return true");
        h = new Hotel("TestHotel7", 10, 5, 5);
        b = sys.setBasePrice(h, 150.0);
        System.out.println("Return : " + b + "\n");

        System.out.println("Test 17: Set base price to an invalid amount, should return false");
        b = sys.setBasePrice(h, 90.0);
        System.out.println("Return : " + b + "\n");

        printlines();

        /**
         * Testing 8 : removeReservation()
         */
        System.out.println("Test Cases 18-19: removeReservation()\n");

        System.out.println("Test 18: Remove a valid reservation, should return true");
        Room room = h.findRoomByName("S1");
        h.createReservation("Guest1", "1", "3", room, "");
        b = sys.removeReservation(h, "Guest1", 1, 3, room);
        System.out.println("Return : " + b + "\n");

        System.out.println("Test 19: Remove a non-existent reservation, should return false");
        b = sys.removeReservation(h, "Guest2", 1, 3, room);
        System.out.println("Return : " + b + "\n");

        printlines();

        /**
         * Testing 9 : modifyMultiplier() and isModifyModifierValid()
         */
        System.out.println("Test Cases 20-23: modifyMultiplier() and isModifyModifierValid()\n");

        System.out.println("Test 20: Validate modification with valid input, should return 0");
        i = sys.isModifyModifierValid(h, "15", "110.0");
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 21: Validate modification with invalid date, should return 1");
        i = sys.isModifyModifierValid(h, "32", "100.0");
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 22: Validate modification with invalid price modifier, should return 2");
        i = sys.isModifyModifierValid(h, "15", "200.0");
        System.out.println("Return : " + i + "\n");

        System.out.println("Test 23: Modify multiplier");
        h = new Hotel("TestHotel8", 10, 5, 5);
        sys.modifyMultiplier(h, "15", "80.0");
        d = h.getDatePriceMultiplier(15);
        System.out.println("Multiplier : " + d);
        
        printlines();

        /**
         * Testing 10 : changeHotelName()
         */
        System.out.println("Test Cases 24-25: changeHotelName()\n");

        System.out.println("Test 24: Change hotel name to a unique name, should return true");
        h = sys.findHotelByName("A-Hotel");
        b = sys.changeHotelName(h, "E-Hotel");
        System.out.println("Return : " + b + "\n");

        System.out.println("Test 25: Change hotel name to an existing name, should return false");
        b = sys.changeHotelName(h, "C-Hotel");
        System.out.println("Return : " + b + "\n");

        printlines();
    }
}
