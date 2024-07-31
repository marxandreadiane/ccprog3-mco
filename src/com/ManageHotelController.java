package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * The ManageHotelController class is responsible for managing the interactions between the
 * ManageHotel view and the HRSystem model.
 */
public class ManageHotelController
{
    private ManageHotel manageHotel;
    private HRSystem hrSystem;
    private JFrame changeName, removeHotel, addRoom, updatePrice, removeRoom, removeReservation, datePrice;
    
    /**
     * Constructs a ManageHotelController with the specified HRSystem.
     * @param hrSystem the HRSystem object containing hotel data
     */
    public ManageHotelController(HRSystem hrSystem)
    {
        this.hrSystem = hrSystem;
        manageHotel = new ManageHotel(hrSystem);

        this.manageHotel.setChangeNameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null)
                {
                    changeName = manageHotel.changeName();
                    changeName.setVisible(true);
                }
                else
                {
                    manageHotel.displayMessage("Kindly input an existing Hotel name.");
                }
            }
        });

        this.manageHotel.setChangeNameButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                String newName = manageHotel.getChangeNameFieldText();

                if (hrSystem.findHotelByName(newName) == null)
                {
                    if (manageHotel.showYesNoDialog())
                    {
                        changeName.dispose();
                        hrSystem.changeHotelName(hotel, newName);
                        manageHotel.getHotelName().setSelectedItem(new String(newName));
                    }
                }
                else
                {
                    manageHotel.displayMessage("Name is already in use.");
                }

            }
        });

        this.manageHotel.setRemoveHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null)
                {
                    removeHotel = manageHotel.removeHotel();
                    removeHotel.setVisible(true);
                }
                else 
                {
                    manageHotel.displayMessage("Kindly input an existing Hotel name.");
                }
            }
        });

        this.manageHotel.setRemoveHotelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                hrSystem.removeHotel(hotelName);
                
                removeHotel.dispose();
                manageHotel.getHotelName().setSelectedItem("");
                manageHotel.displayMessage("Hotel successfully removed.");    
                manageHotel.getHotelName().removeEntry(hotelName);  
            }
        });

        this.manageHotel.setCancelRemovalButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeHotel.dispose();   
            }
        });

        this.manageHotel.setAddRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null)
                {
                    addRoom = manageHotel.addRoom();
                    addRoom.setVisible(true);
                }
                else
                {
                    manageHotel.displayMessage("Kindly input an existing Hotel name.");
                }
                
            }
        });

        this.manageHotel.setAddRoomButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                
                int result = hrSystem.isAddRoomsValid(hotel, manageHotel.getAddRoomStandardText(), manageHotel.getAddRoomDeluxeText(), manageHotel.getAddRoomExecutiveText());

                if (result == 0)
                {
                    manageHotel.displayMessage("Kindly input appropriate values only.");
                }
                else if (result == 1)
                {
                    manageHotel.displayMessage("Hotel has 50 rooms already.");
                } 
                else if (result == 2)
                {
                    manageHotel.displayMessage("New number of rooms exceeds the limit.");
                }
                else if (result == 3)
                {
                    manageHotel.displayMessage("Total new number of rooms must be positive.");
                }
                else if (result == 4)
                {
                    manageHotel.displayMessage("New number of rooms exceeds the limit.");
                }
                else if (result == 5)
                {
                    if (manageHotel.showYesNoDialog())
                    {
                        hrSystem.addRooms(hotel, manageHotel.getAddRoomStandardText(), manageHotel.getAddRoomDeluxeText(), manageHotel.getAddRoomExecutiveText());
                        addRoom.dispose();
                        manageHotel.displayMessage("Rooms successfully added.");
                    }
                }         
            }
        });

        this.manageHotel.setUpdatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null && hotel.getReservationList().isEmpty())
                {
                    updatePrice = manageHotel.updatePrice(hotel);
                    updatePrice.setVisible(true);
                }
                else if (hotel == null)
                {
                    manageHotel.displayMessage("Kindly input an existing Hotel name.");
                }
                else
                {
                    manageHotel.displayMessage("This feature is unavailable as there are existing reservations in this hotel.");
                }
                
            }
        });

        this.manageHotel.setUpdatePriceButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                Double price;

                try
                {
                    price = Double.valueOf(manageHotel.getUpdatePriceFieldText());

                    if (price >= 100)
                    {
                        if (manageHotel.showYesNoDialog())
                        {
                            updatePrice.dispose();
                            hrSystem.setBasePrice(hotel, price);
                            manageHotel.displayMessage("Price successfully updated.");
                        }
                    }
                    else
                    {
                        manageHotel.displayMessage("Price must be >= 100.");
                    }
                }
                catch (Exception exception)
                {
                    manageHotel.displayMessage("Kindly input an appropriate value.");
                }
            }
        });

        this.manageHotel.setRemoveRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null && hotel.getNumberOfRooms() > 1)
                {
                    removeRoom = manageHotel.removeRoom(hotel);
                    removeRoom.setVisible(true);
                }
                else if (hotel == null)
                {
                    manageHotel.displayMessage("Kindly input an existing Hotel name.");
                }
                else
                {
                    manageHotel.displayMessage("This feature is unavailable as this hotel has only one room left.");
                }
            }
        });

        this.manageHotel.setRemoveRoomButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                String roomName = manageHotel.getRemoveRoomFieldText();
                Room room = hotel.findRoomByName(roomName);

                if (room != null && room.getAvailability(1, 31))
                {
                    if (manageHotel.showYesNoDialog())
                    {
                        hrSystem.removeRoom(hotel, roomName);
                        manageHotel.displayMessage("Room successfully removed.");
                        if (hotel.getNumberOfRooms() > 1 && manageHotel.showYesNoDialog("Would you like to remove more rooms?"))
                        {
                            manageHotel.removeRoom(hotel).repaint();
                            manageHotel.clearRemoveRoomField();
                        }

                        removeRoom.dispose();
                    }
                }
                else if (room == null)
                {
                    manageHotel.displayMessage("Room does not exist.");
                }
                else
                {
                    manageHotel.displayMessage("Room has an existing reservation.");
                }

            }
        });

        this.manageHotel.setRemoveReservationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null)
                {
                    removeReservation = manageHotel.removeReservation(hotel);
                    removeReservation.setVisible(true);
                }
                else
                {
                    manageHotel.displayMessage("Kindly input an existing Hotel name.");
                }
            }
        });

        this.manageHotel.setRemoveReservationButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                String reservationDetails = manageHotel.getReservationDetailsFieldSelectedItem();
                Reservation reservation = hotel.findReservation(reservationDetails);

                if (reservation != null)
                {
                    if (manageHotel.showYesNoDialog())
                    {
                        hotel.removeReservation(reservation);
                        manageHotel.displayMessage("Reservation successfully removed.");
                        removeReservation.dispose();
                    }
                }
                else
                {
                    manageHotel.displayMessage("Reservation does not exist.");
                }
            }
        });

        this.manageHotel.setDatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);

                if (hotel != null)
                {
                    datePrice = manageHotel.modifyDatePrice();
                    datePrice.setVisible(true);
                }
                else
                {
                    manageHotel.displayMessage("Kindly input an existing Hotel name.");
                }
            }
        });

        this.manageHotel.setDatePriceButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = manageHotel.getHotelNameSelectedItem();
                Hotel hotel = hrSystem.findHotelByName(hotelName);
                int result = hrSystem.isModifyModifierValid(hotel, manageHotel.getModifyDateText(), manageHotel.getModifyPriceText());

                if (result == 1)
                {
                    manageHotel.displayMessage("Invalid date.");
                }
                else if (result == 2)
                {
                    manageHotel.displayMessage("Invalid price modification.");
                }
                else
                {
                    if (manageHotel.showYesNoDialog())
                    {
                        hrSystem.modifyMultiplier(hotel, manageHotel.getModifyDateText(), manageHotel.getModifyPriceText());
                        manageHotel.displayMessage("Date-Price multiplier successfully updated.");
                        datePrice.dispose();
                    }
                }
            }
        });
    }

}