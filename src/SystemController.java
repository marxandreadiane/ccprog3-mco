import java.util.ArrayList;

public class SystemController {
    private SystemView systemView;
    private HRSystem system;

    // CONSTRUCTORS
    /** 
     * 
     */
    public SystemController(SystemView systemView, HRSystem system)
    {
        this.systemView = systemView;
        this.system = system;
    }

    // MAIN METHODS
    // Create Hotel
    /**
     * Create a hotel in system (of class HRSystem)
     */
    public void createHotel() 
    {
        int choice, numOfRooms;
        boolean tryAgain;
        do
        {
            tryAgain = false;
            
            systemView.displayCreateHotelMenu();
            String name = systemView.promptName("Name of the new hotel: ");

            do
            {
                numOfRooms = systemView.promptInt("How many rooms would you like to have? ");

                if (numOfRooms > 50)
                {
                    System.out.println("Number exceeds the maximum count. Enter another number.");
                }
                else if (numOfRooms <= 0)
                {
                    System.out.println("Number is invalid. Enter another number.");
                }
            } while (numOfRooms <= 0 || numOfRooms > 50);


            if (system.createHotel(name, numOfRooms))
            {
                System.out.println("\nHotel successfully created!\n");
                
                do
                {
                    choice = systemView.promptYN("Would you like to create another hotel?");

                    if (choice == 1)
                    {
                        tryAgain = true;
                    }   
                } while (choice != 1 && choice != 2);
            }
            else
            {
                do
                {
                    choice = systemView.promptYN("\nError! Hotel already exists. Would you like to try again?");

                    if (choice == 1)
                    {
                        tryAgain = true;
                    }   
                } while (choice != 1 && choice != 2);
            }
            
        } while (tryAgain);
    }

    // View Hotel
    /**
     * Displays high-level or low-level information about a specified hotel.
     */
    public void viewHotel() 
    {
        Hotel selectedHotel = null;
        int choice;

        systemView.displayViewHotelMenu();

        // Prompt if View List of Hotels
        promptViewListOfHotels();
        
        // Select Hotel
        selectedHotel = promptSelectHotel();
        if (selectedHotel == null)
            return;
        

        // Prompt Info to be Viewed
        do
        {
            systemView.displayHotelInfoMenu(selectedHotel.getName());
            choice = systemView.promptInt("Choice: ");

            switch(choice)
            {
                // High-Level Info
                case 1:
                    System.out.println("\nHere are the high-level information of the hotel:");
                    selectedHotel.getHighLevel();
                    break;

                // Low-Level Info
                case 2:
                    promptLowLevelInfo(selectedHotel);
                    break;

                // Quit
                case 3:
                    break;
                
                default: 
                    System.out.println("Invalid response! Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    /**
     * Manages a specified hotel, allowing the user to change the hotel name, add or remove rooms,
     * update base room price, remove reservations, and remove hotels.
     */
    public void manageHotel() 
    {
        Hotel selectedHotel = null; 
        int choice;
        String name;
        boolean tryAgain, manageAgain;

        systemView.displayManageHotelMenu();

        // Prompt if View List of Hotels
        promptViewListOfHotels();
        
        // Select Hotel
        selectedHotel = promptSelectHotel();
        if (selectedHotel == null)
            return;
        
        // Prompt managing actions if there is a hotel selected
        do 
        {
            manageAgain = true;
            systemView.displayManagingActions();
            choice = systemView.promptInt("Choice: ");

            switch(choice)
            {
                // Exit
                case 0: 
                    manageAgain = false;
                    break;
                
                // Change name
                case 1: 

                    do
                    {
                        tryAgain = false;

                        name = systemView.promptName("\nNew hotel name: ");
                    
                        if (system.findHotelByName(name) == null)
                        {
                            choice = systemView.promptYN("\nConfirm the name change of this hotel?");

                            if (choice == 1)
                            {
                                selectedHotel.setName(name);
                                System.out.println("\nHotel name successfully changed.");
                            }
                            else
                            {
                                System.out.println("\nName change process cancelled.");
                            }
                        }
                        else
                        {
                            System.out.println("Hotel already exists.");
                            choice = systemView.promptYN("\nWould you like to provide another name?");
                            if (choice == 1)
                            {
                                tryAgain = true;
                            }
                        }
                    } while (tryAgain);

                    break;

                // Add rooms
                case 2: 
                    do
                    {
                        int i;
                        tryAgain = false;

                        do
                        {
                            choice = systemView.promptInt("\nHow many rooms would you like to add? ");

                            if (choice > 50 - selectedHotel.getNumberOfRooms())
                            {
                                System.out.println("Number exceeds the maximum count. Enter another number.");
                            }
                            else if (choice < 0)
                            {
                                System.out.println("Number is invalid. Enter another number.");
                            }
                        } while (choice < 0 || choice > 50 - selectedHotel.getNumberOfRooms());
                    
                        choice = systemView.promptYN("\nConfirm the addition of rooms in this hotel?");

                        if (choice == 1)
                        {
                            for (i = 0; i < choice; i++)
                            {
                                selectedHotel.addRoom();
                            }
                            System.out.println("\nRoom(s) successfully added.");
                            }
                        else
                        {
                            System.out.println("\nRoom(s) not added.");
                        }
                        
                        choice = systemView.promptYN("\nWould you like to add more rooms?");
                        if (choice == 1)
                        {
                            tryAgain = true;
                        }

                    } while (tryAgain);

                    break;

                // Remove room
                case 3: 
                    do
                    {
                        tryAgain = false;

                        choice = systemView.promptYN("\nWould you like to see the list of rooms?");
                        if (choice == 1)
                        {
                            System.out.println("\nList of rooms: ");
                            displayRoomList(selectedHotel.getRoomList());
                        }
                        
                        name = systemView.promptName("\nEnter the name of the room: ");

                        if (selectedHotel.findRoomByName(name) != null)
                        {
                            if (selectedHotel.findRoomByName(name).getAvailability(1, 31))
                            {
                                choice = systemView.promptYN("\nRemove room " + name + "?");
                                if (choice == 1)
                                {
                                    selectedHotel.removeRoom(name);
                                    System.out.println("\nRoom successfully removed.");
                                }
                                else
                                {
                                    System.out.println("\nDeletion cancelled.");
                                }
                            }
                            else
                            {
                                System.out.println("\nRoom has an existing reservation. It cannot be removed.");
                            }
                        }
                        else
                        {
                            System.out.println("\nRoom does not exist.");
                        }

                        choice = systemView.promptYN("\nWould you like to remove another room?");
                        if (choice == 1)
                        {
                            tryAgain = true;
                        }
                    } while (tryAgain);
                    break;

                // Set new base price
                case 4: 
                    double price;
  
                    do
                    {
                        price = systemView.promptDouble("\nNew base price: ");
                        if (price < 100)
                        {
                            System.out.println("Price must be >= 100. Enter another price.");
                        }
                    } while (price < 100);

                    choice = systemView.promptYN("\nSet new base price?");
                    if (choice == 1)
                    {
                        selectedHotel.setBasePrice(price);
                        System.out.println("\nPrice successfully updated.");
                    }
                    else
                    {
                        System.out.println("\nProcess cancelled.");
                    }
                    
                    break;

                // Remove reservation
                case 5: 
                    ArrayList<Reservation> reservationList = new ArrayList<Reservation>(); 
                    Reservation deleteRes;

                    do
                    {
                        tryAgain = false;
                        name = systemView.promptName("\nName of guest: ");

                        reservationList = selectedHotel.filterReservationByName(name);
                        System.out.println();

                        if (reservationList.isEmpty())
                        {
                            System.out.println("\nNo reservations.");
                        }
                        else
                        {
                            System.out.println("\nList of reservations under guest \"" + name + "\":");
                            displayReservationList(reservationList);
                            
                            do
                            {
                                choice = systemView.promptInt("Reservation index (1-" + reservationList.size() + "): ");
                            } while (choice < 1 || choice > reservationList.size() + 1 );

                            deleteRes = reservationList.get(choice - 1);

                            choice = systemView.promptYN("Remove reservation?");
                            if (choice == 1)
                            {
                                selectedHotel.removeReservation(deleteRes.getGuestName(), deleteRes.getCheckInDate(), deleteRes.getCheckOutDate());
                                System.out.println("\nReservation successfully deleted!");
                            }
                            else
                            {
                                System.out.println("\nProcess cancelled.");
                            }

                            choice = systemView.promptYN("Would you like to remove another reservation?");
                            if (choice == 1)
                            {
                                tryAgain = true;
                            }
                        } 
                    } while (tryAgain);  
                    break;

                // Remove hotel
                case 6: 
                    choice = systemView.promptYN("\nWould you like to delete hotel \"" + selectedHotel.getName() + "\"?");

                    if (choice == 1)
                    {
                        System.out.println("\nHotel successfully deleted.");
                        system.removeHotel(selectedHotel.getName());
                        manageAgain = false;
                    }
                    else
                    {
                        System.out.println("\nProcess cancelled.");
                    }
                    break;

                default: 
                    System.out.println("Invalid response! Please try again.");
                    break;
            }

        } while (choice != 0 && manageAgain);
    }

    /**
     * Simulates the booking process for a guest.
     */
    public void simulateBooking() 
    {
        Hotel selectedHotel;
        int checkInDate, checkOutDate, choice;
        String name;
        
        systemView.displayBookingMenu();
             
        // Prompt if View List of Hotels
        promptViewListOfHotels();

        // Select Hotel
        selectedHotel = promptSelectHotel();
        if (selectedHotel == null)
            return;

        System.out.println();

        // Getting valid check-in and check-out dates
        do
        {
            checkInDate = systemView.promptInt("Check-in date: ");
            if (checkInDate >= 31 || checkInDate <= 0)
            {
                System.out.println("Invalid date. Try again.");
            }
            } while ((checkInDate >= 31 || checkInDate <= 0));

        do
        {
            checkOutDate = systemView.promptInt("Check-out date: ");
            if (checkOutDate >= 32 || checkOutDate <= 1 || checkOutDate <= checkInDate)
            {
                System.out.println("Invalid date. Try again.");
            }
        } while ((checkOutDate >= 32 || checkOutDate <= 1 || checkOutDate <= checkInDate));


        if (selectedHotel.getAvailableRooms(checkInDate, checkOutDate) > 0)
        {
            System.out.println("\nRoom available!");
            name = systemView.promptName("Guest name: ");

            choice = systemView.promptYN("\nConfirm reservation for " + name + " for dates " + checkInDate + " to " + checkOutDate + "?");
            if (choice == 1)
            {
                selectedHotel.createReservation(name, checkInDate, checkOutDate);
                System.out.println("\nReservation booked successfully.");
            }
            else
            {
                System.out.println("\nReservation cancelled.");
            }
        }
        else
        {
            System.out.println("Sorry. No room available.");
            System.out.println("Booking unsuccessful.");
        }
    }


    // SUB-METHODS FOR VIEWING AND MANAGING
    // view list of hotel
    public void promptViewListOfHotels() 
    {
        int choice;
        do 
        {
            choice = systemView.promptYN("\nWould you like to view the list of hotels?");
        } while (choice != 1 && choice != 2);

        if (choice == 1)
        {
            system.printHotel();
        }
        System.out.println();
    }
    
    // select a hotel to view/manage, returns Hotel or null
    public Hotel promptSelectHotel() 
    {
        String name;
        do
        {
            name = systemView.promptName("Name of hotel (type \"0\" without double quotes to exit): ");

            if (name.equals("0")) 
            {
                break;
            }
            if (system.findHotelByName(name) == null)
            {
                System.out.println("\nHotel not found. Try again.");
            }
            else
            {
                return system.getHotel(name);
            }

        } while (system.findHotelByName(name) == null);
        
        return null;
    }


    // prompt and view certain low-level information in Hotel
    public void promptLowLevelInfo(Hotel selectedHotel) 
    {
        int date, choice;
        String name;
        Room selectedRoom = null;
        boolean tryAgain;

        do
        {
        systemView.displayLowLevelInfoMenu(selectedHotel.getName());
        choice = systemView.promptInt("Choice: ");

            switch(choice)
            {
                // Room Availability
                case 1: 
                    do
                    {
                        tryAgain = false;

                        do
                        {
                            date = systemView.promptInt("\nSelect date: ");
                            if (date > 31 || date < 1)
                            {
                                System.out.println("Invalid date. Try again.");
                            }
                        } while (date > 30 || date < 1);
        
                        System.out.println("\nTotal number of available rooms: " + selectedHotel.getAvailableRooms(date));
                        System.out.println("Total number of booked rooms: " + selectedHotel.getBookedRooms(date));

                        choice = systemView.promptYN("\nWould you like to check another date?");
                        if (choice == 1)
                        {
                            tryAgain = true;
                        }
                    } while (tryAgain);
        
                    break;
    
                // Room Info
                case 2: 
                    do
                    {
                        tryAgain = false;

                        choice = systemView.promptYN("\nWould you like to see the list of rooms?");
                        if (choice == 1)
                        {
                            System.out.println("\nList of rooms: ");
                            displayRoomList(selectedHotel.getRoomList());
                        }
                            
                        do
                        {
                            name = systemView.promptName("\nEnter the name of the room: ");
                            selectedRoom = selectedHotel.findRoomByName(name);
                            if (selectedRoom == null)
                            {
                                System.out.println("Room not found. Try again.");
                            }
                        } while (selectedRoom == null);
        
                        System.out.println("\nRoom information");
                        System.out.println("Room name: " + selectedRoom.getName());
                        System.out.println("Price per night: " + selectedHotel.getBasePrice());
                        System.out.print("Available dates: ");
                        selectedRoom.printAvailableDates();

                        choice = systemView.promptYN("\nWould you like to check another room?");
                        if (choice == 1)
                        {
                            tryAgain = true;
                        }
                    } while (tryAgain);
                    
                    break;
                
                // Reservation Info
                case 3: 
                    do
                    {
                        Reservation checkRes;
                        tryAgain = false;

                        ArrayList<Reservation> reservationList = selectedHotel.getReservationList();

                        if (reservationList.isEmpty())
                        {
                            System.out.println("\nNo reservations.");
                        }
                        else
                        {
                            choice = systemView.promptYN("\nWould you like to see the list of reservations?");
                            if (choice == 1)
                            {
                                System.out.println("\nList of reservations: ");
                                displayReservationList(reservationList);
                            }
                            
                            do
                            {
                                choice = systemView.promptInt("Select reservation index (1-" + reservationList.size() + "): ");
                                if (choice < 1 || choice > reservationList.size() + 1)
                                {
                                    System.out.println("Invalid index. Try again.");
                                }
                            } while (choice < 1 || choice > reservationList.size() + 1);

                            checkRes = reservationList.get(choice - 1);

                            System.out.println("\nReservation Details ");
                            System.out.println("  Guest: " + checkRes.getGuestName());
                            System.out.println("  Room: " + checkRes.getRoom().getName());
                            System.out.println("  Check-in date: " + checkRes.getCheckInDate());
                            System.out.println("  Check-out date: " + checkRes.getCheckOutDate());
                            System.out.println("  Total booking price: " + (checkRes.getCheckOutDate() - checkRes.getCheckInDate()) * selectedHotel.getBasePrice());
                            System.out.println("  Price per night: " + selectedHotel.getBasePrice());

                            choice = systemView.promptYN("\nWould you like to check another reservation?");
                            if (choice == 1)
                            {
                                tryAgain = true;
                            }
                        } 
                    } while (tryAgain);  


    
                    break;
    
                default: break;
            }
        } while (choice != 4);
    }

    private void displayReservationList(ArrayList<Reservation> reservationList)
    {
        int i;

        System.out.println();
        for (i = 0; i < reservationList.size(); i++)
        {
            System.out.print("1.");
            reservationList.get(i).printReservationInfo();
        }
    }

    private void displayRoomList(ArrayList<Room> roomList)
    {
        int i;

        System.out.println();
        for (i = 0; i < roomList.size(); i++)
        {
            System.out.println((i+1) + ". " + roomList.get(i).getName());
        }
    }
}
 
