public class TestHRSystem {
    
    private static HRSystem sys = new HRSystem();

    public static void printcase(String name, int i) {
        System.out.println("testing " + String.valueOf(i) + ": " + name);
    }

    public static void print(boolean b) {
        System.out.println("return : " + b + "\n");
    }

    public static void printlines() {
        System.out.println("\n=============================\n\n");
    }

    public static void main(String[] args) {

        boolean b;
        int i;
        Hotel h = null;

        printlines();

        /**
         * Testing: createHotel() and printHotel()
         */
        printcase("createHotel()", 1);
        sys.printHotel();
        b = sys.createHotel("A-Hotel", 20);
        sys.printHotel();
        print(b);

        printcase("createHotel() duplicate", 2);
        b = sys.createHotel("A-Hotel", 18);
        sys.printHotel();
        print(b);

        printlines();

        /**
         * Testing: removeHotel()
         */
        // initialization
        sys.createHotel("B-Hotel", 30);
        sys.createHotel("C-Hotel", 48);

        printcase("removeHotel()", 3);
        b = sys.removeHotel("B-Hotel");
        sys.printHotel();
        print(b);

        printcase("removeHotel()", 4);
        b = sys.removeHotel("D-Hotel");
        sys.printHotel();
        print(b);

        /**
         * Testing: findHotelbyName()
         */
        printcase("findHotelbyName()", 5);
        h = sys.findHotelByName("C-Hotel");
        System.out.println(h);
        System.out.println("name: " + h.getName() + "  rooms: " + String.valueOf(h.getNumberOfRooms()));

        printcase("findHotelbyName()", 5);
        h = sys.findHotelByName("D-Hotel");
        System.out.println(h);
        System.out.println(h == null);
    }
}
