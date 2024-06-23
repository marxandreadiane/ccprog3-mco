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

    public static void printlines() {
        System.out.println("\n=============================\n\n");
    }

    public static void main(String[] args) {

        // Variable Initialization
        boolean b;
        
        // System Initialization
        sys.createHotel("A-Hotel", 28);
        sys.createHotel("B-Hotel", 30);
        sys.createHotel("C-Hotel", 48);
        Hotel hotel1 = sys.findHotelByName("A-Hotel");
        Hotel hotel2 = sys.findHotelByName("B-Hotel");
       
        printlines();

        /**
         * Testing 1-3: set/get name/basePrice
         */
        hotel2.setName("Bida-Jabi");
        hotel2.setBasePrice(110);
        printcase("getsetnamebasePrice : name", 1);
        print(hotel2.getName());
        printcase("getsetnamebasePrice : price", 2);
        print(hotel2.getBasePrice());

        b = hotel2.setBasePrice(80);
        printcase("getsetnamebasePrice : overlimit", 3);
        print(b);


    }
}
