package com;

/**
 * The HRSystemMain class is the entry point of the hotel reservation system application.
 */
public class HRSystemMain
{
    /**
     * The main method initializes the HRSystem and HRSystemController to start the application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new HRSystemController(new HRSystem());
    }
}