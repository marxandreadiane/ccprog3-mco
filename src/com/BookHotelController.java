package com;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The BookHotelController class is responsible for managing the interactions between the
 * BookHotel view and the HRSystem model.
 */
public class BookHotelController {
    private BookHotel bookHotel;
    private HRSystem hrSystem;

    /**
     * Constructs a BookHotelController with the specified HRSystem.
     * @param hrSystem the HRSystem object containing hotel data
     */
    public BookHotelController(HRSystem hrSystem) {
        this.bookHotel = new BookHotel(hrSystem);
        this.hrSystem = hrSystem;

        this.bookHotel.setConfirmButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = bookHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null)
                {
                    bookHotel.otherInformation(hotel);
                    bookHotel.getPanel().revalidate(); 
                    bookHotel.getPanel().repaint(); 
                }
                else
                {
                    bookHotel.displayMessage("Invalid hotel.");
                }
            }
        });

        this.bookHotel.setConfirmDatesListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = bookHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                String checkInStr = bookHotel.getInputCheckIn();
                String checkOutStr = bookHotel.getInputCheckOut();
                String guestName = bookHotel.getInputName();
                
                if (hotel != null)
                {
                    int result = hotel.showVoucherRoom(guestName, checkInStr, checkOutStr);

                    if (result == 0)
                    {
                        bookHotel.roomVoucher(hotel);
                        bookHotel.getPanel().revalidate(); 
                        bookHotel.getPanel().repaint();
                    }
                    else if (result == 1)
                    {
                        bookHotel.displayMessage("Kindly input guest name.");
                    }
                    else if (result == 2)
                    {
                        bookHotel.displayMessage("Dates must be numeric and not empty.");
                    }
                    else if (result == 3)
                    {
                        bookHotel.displayMessage("Invalid check-in date.");
                    }
                    else if (result == 4)
                    {
                        bookHotel.displayMessage("Invalid check-out date.");
                    }
                    else if (result == 5)
                    {
                        bookHotel.displayMessage("Check-out date must be at least one day later than the check-in date.");
                    }
                }
            }
        });
        
        this.bookHotel.setBookButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = bookHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
        
                if (hotel != null) {
                    String roomName = bookHotel.getRoomSelectedItem();
                    Room room = hotel.findRoomByName(roomName);
        
                    if (room != null) {
                        String voucher = bookHotel.getVoucherText();        
                        int result = hotel.createReservation(bookHotel.getInputName(), bookHotel.getInputCheckIn(), bookHotel.getInputCheckOut(), room, voucher);
    
                        if (result == 0) {
                            bookHotel.displayMessage("Failed to create booking. Room has been reserved.");
                        } else if (result == 1) {
                            bookHotel.displayMessage("Booking successfully created. No voucher applied.");
                            bookHotel.clearTextFields();
                            bookHotel.getMiniPanel().setVisible(false);
                        } else if (result == 2) {
                            bookHotel.displayMessage("Booking successfully created. Voucher has been applied.");
                            bookHotel.clearTextFields();
                            bookHotel.getMiniPanel().setVisible(false);
                        } else {
                            if (bookHotel.showYesNoDialog()) 
                            {
                                bookHotel.displayMessage("Booking successfully created.");
                                bookHotel.clearTextFields();
                                bookHotel.getMiniPanel().setVisible(false);
                            }
                            else
                            {
                                Reservation reservation = hotel.findReservation(bookHotel.getInputName() + " | " + bookHotel.getInputCheckIn() + " - " + bookHotel.getInputCheckOut() + " | " + roomName);
                                hotel.removeReservation(reservation);
                            }
                            
                        }
                    } else {
                        bookHotel.displayMessage("Kindly select a valid room.");
                    }
                } else {
                    bookHotel.displayMessage("Kindly input an existing hotel first.");
                }
            }
        });        
    }
}
