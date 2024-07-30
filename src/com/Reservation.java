package com;

import java.util.ArrayList;

/**
 * Represents a hotel reservation.
 */
public class Reservation
{
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room room;

    // new variables
    private boolean voucherIWorkHere;
    private boolean voucherStay4_Get1 = false;
    private boolean voucherPayDay = false;
    private double voucherDiscountMultiplier = 1;
    private int minusOneDay = 0;
    private double totalPrice = 0;

    // CONSTRUCTORS
    /**
     * Constructs a Reservation with the provided guest name, check-in date, and check-out date.
     * @param guestName the name of the guest
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @param room the room to be reserved
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room)
    {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
    }

    // GETTERS AND SETTERS
    /**
     * Gets the name of the guest.
     * @return the guest's name
     */
    public String getGuestName()
    {
        return this.guestName;
    }

    /**
     * Gets the check-in date.
     * @return the check-in date
     */
    public int getCheckInDate()
    {
        return this.checkInDate;
    }

    /**
     * Gets the check-out date.
     * @return the check-out date
     */
    public int getCheckOutDate()
    {
        return this.checkOutDate;
    }

    /**
     * Gets the room associated with this reservation.
     * @return the room
     */
    public Room getRoom()
    {
        return this.room;
    }

    /**
     * Gets the total price of this reservation.
     * @return the total price of the reservation
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Checks if the voucher is applicable in the booking
     * @param discountCode The code to be applied in the booking
     * @return true if applicable, false otherwise
     */
    public boolean enterVoucher(String discountCode) {
        if (discountCode.equals("I_WORK_HERE") && this.voucherIWorkHere == false) 
        {
            this.voucherDiscountMultiplier *= 0.9;
            return true;
        }
        else if (discountCode.equals("STAY4_GET1") && this.voucherStay4_Get1 == false)
        {
            if (this.checkOutDate - this.checkInDate >= 5)
            {
                this.minusOneDay = 1;
                return true;
            }
        }
        else if (discountCode.equals("PAYDAY") && this.voucherPayDay == false)
        {
            if ((this.checkInDate <= 15 && this.checkOutDate >= 16) || (this.checkOutDate == 31))
            {
                this.voucherDiscountMultiplier *= 0.93;
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the price of this reservation.
     * @param hotel the hotel where the reservation was made
     * @param room the room associated with the reservation
     */
    public void calculatePrice(Hotel hotel, Room room)
    {
        double priceCounting = 0;
        int consideredCheckInDate = this.checkInDate;

        if (minusOneDay == 1)
        {
            consideredCheckInDate++;
        }

        for (int i = consideredCheckInDate; i < this.checkOutDate; i++)
        {
            priceCounting += hotel.getRoomPrice(room) * hotel.getDatePriceMultiplier(i);
        }

        priceCounting *= this.voucherDiscountMultiplier;

        this.totalPrice = priceCounting;
    }

    /**
     * Generates the price breakdown of this reservation.
     * @param hotel the hotel where the reservation was made
     * @param room the room associated with the reservation
     * @return a list containing the breakdown per night of the booking
     */
    public ArrayList<String> getBreakdown(Hotel hotel, Room room)
    {
        ArrayList<String> breakdown = new ArrayList<String>();
        String element;
        int consideredCheckInDate = this.checkInDate;

        if (minusOneDay == 1)
        {
            consideredCheckInDate++;
        }

        for (int i = consideredCheckInDate; i < this.checkOutDate; i++)
        {
            element = + i + " - " + (i+1) + " : "+ Double.toString(hotel.getRoomPrice(room) * hotel.getDatePriceMultiplier(i));
            breakdown.add(element);
        }

        return breakdown;
    }

    

}