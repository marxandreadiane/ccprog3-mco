
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ViewHotelController
{
    private HRSystem hrSystem;
    private ViewHotel viewHotel;
    private JPanel highInfo;
    private JPanel lowInfo;

    public ViewHotelController(HRSystem hrSystem)
    {
        this.hrSystem = hrSystem;
        this.viewHotel = new ViewHotel(hrSystem);

        this.viewHotel.setConfirmButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = viewHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null)
                {
                    viewHotel.getHighButton().setVisible(true);
                    viewHotel.getLowButton().setVisible(true);

                    if (highInfo != null && lowInfo != null)
                    {
                        highInfo.setVisible(false);
                        lowInfo.setVisible(false);
                    }
                }
                else
                {
                    viewHotel.displayMessage("Hotel does not exist.");
                }
            }
        });

        this.viewHotel.setShowLowButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = viewHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                lowInfo = viewHotel.lowLevelInfo(hotel);
                viewHotel.getMainPanel().add(lowInfo);
                viewHotel.getMainPanel().repaint();
                viewHotel.getLowButton().setVisible(false);
                }
            });

        this.viewHotel.setShowHighButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = viewHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                highInfo = viewHotel.highLevelInfo(hotel);
                viewHotel.getMainPanel().add(highInfo);
                viewHotel.getMainPanel().repaint();
                viewHotel.getHighButton().setVisible(false);
            }
        });

        this.viewHotel.setConfirmDateButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = viewHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                String date = viewHotel.getDateSelectedItem();
                
                if (date != null && !date.equals("")) {
                    if (date.matches("\\d+")) { 
                        try {
                            int nDate = Integer.parseInt(date);
                
                            if (nDate >= 1 && nDate <= 30) {
                                viewHotel.dateFrame(date, hotel).setVisible(true);
                            } else {
                                viewHotel.displayMessage("Date must be between 1 and 30.");
                            }
                        } catch (NumberFormatException exception) {
                            viewHotel.displayMessage("Invalid date.");
                        }
                    } else {
                        viewHotel.displayMessage("Date must be numeric.");
                    }
                } else {
                    viewHotel.displayMessage("Kindly input date.");
                }   
            }
        });

        this.viewHotel.setConfirmRoomButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = viewHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                String roomName = viewHotel.getRoomSelectedItem();
                Room room = hotel.findRoomByName(roomName);

                if (room != null)
                {
                    viewHotel.roomFrame(hotel, room).setVisible(true);
                }
                else
                {
                    viewHotel.displayMessage("Room does not exist.");
                }
            }
        });

        this.viewHotel.setConfirmReservationButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = viewHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                String reservationDetails = viewHotel.getReservationSelectedItem();
                Reservation reservation = hotel.findReservation(reservationDetails);

                if (reservation != null)
                {
                    viewHotel.reservationFrame(hotel, reservation).setVisible(true);
                }
                else
                {
                    viewHotel.displayMessage("Reservation does not exist.");
                }
            }
        });
    
    }
}