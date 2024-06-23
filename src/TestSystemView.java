public class TestSystemView {

     private static SystemView sv = new SystemView();

     // We're lazy.
     public static void print(int i, String name) {
          System.out.println(i + ". " + name);
     }

     public static void print(int i) {
          System.out.println("return " + i);
     }

     public static void print(String a) {
          System.out.println("return " + a);
     }

     public static void print(double a) {
          System.out.println("return " + a);
     }

     public static void main(String[] args) {
          int i = 1;
          String j = "Hello! ";

          print(i, "buffer()");
          sv.buffer();
          i++;

          print(i, "promptYN");
          int a = sv.promptYN(j);
          print(a);
          int b = sv.promptYN(j);
          print(b);
          int c = sv.promptYN(j);
          print(c);
          i++;

          print(i, "promptName");
          String x = sv.promptName(j);
          print(x);
          i++;

          print(i, "promptInt");
          int d = sv.promptInt(j);
          print(d);
          i++;

          print(i, "promptDouble");
          double f = sv.promptDouble(j);
          print(f);
          i++;

          sv.displayWelcomeMessage();
          sv.displayMainMenu();
          sv.displayCreateHotelMenu();
          sv.displayViewHotelMenu();
          sv.displayManageHotelMenu();
          sv.displayBookingMenu();
          sv.displayHotelInfoMenu("A-Hotel");
          sv.displayLowLevelInfoMenu("A-Hotel");
          sv.displayManagingActions();


     }
}
