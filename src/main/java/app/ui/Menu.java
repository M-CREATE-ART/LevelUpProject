package app.ui;

import app.controller.BookingController;
import app.controller.FlightController;
import app.database.Airport;
import app.entities.Booking;
import app.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void enterMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=================================\n");
        sb.append("flight booking service\n".toUpperCase());
        sb.append("=================================\n");
        sb.append("1. Online board\n");
        sb.append("2. Show the flight info\n");
        sb.append("3. Search and book a flight\n");
        sb.append("4. Cancel the booking\n");
        sb.append("5. My flights\n");
        sb.append("6. Exit\n");
        sb.append("=================================\n");
        sb.append("Warning: Please exit with command (6) when you want to leave app in order to  save your data in database!\n");

        System.out.println(sb.toString());

        FlightController flightController = new FlightController();
        BookingController bookingController = new BookingController();
        flightController.getfromDB();


        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!command.equals("6")) {

            System.out.println(sb.toString());
            System.out.print("Please enter your command by index: ");
            command = scanner.nextLine();
            switch (command) {

                case "1":
                    System.out.println(flightController.getCurrentDayFlights());
                    System.out.println("Please press enter to go back to main menu. \n");
                    command = scanner.nextLine();

                    break;

                case "2":
                    try {
                        System.out.println("Please enter ID of flight. \n");
                        int flightID = Integer.parseInt(scanner.nextLine());
                        System.out.println(flightController.showFlightInfo(flightID));
                        System.out.println("Please press enter to go back to main menu. \n");
                        command = scanner.nextLine();

                    } catch (Exception e) {
                        System.out.println("You entered invalid input. Please press enter to go back to main menu. \n");
                        command = scanner.nextLine();

                    }

                    break;

                case "3":
                    try {
                        System.out.println("Please enter destination:");
                        String destination = scanner.nextLine();

                        System.out.println("Please enter date in the following format: DAY/MONTH/YEAR");
                        String date = scanner.nextLine();

                        System.out.println("Please enter count of tickets you want to buy");
                        int ticketCount = Integer.parseInt(scanner.nextLine());

                        System.out.println(flightController.getFilteredFlights(destination, date, ticketCount));

                        System.out.println("Please enter ID of flight you want to book or enter to go back to mein menu. \n");
                        int flightID = Integer.parseInt(scanner.nextLine());

                        List<Person> passengers = new ArrayList<>();

                        for (int i = 0; i < ticketCount; i++) {
                            System.out.println("Please enter passenger's name ");
                            String name = scanner.next();
                            System.out.println("Please enter passenger's surname ");
                            String surname = scanner.next();
                            Person passenger = new Person(name, surname);
                            passengers.add(passenger);
                        }
                        Booking booking = new Booking(5, flightController.getFlightsByID(flightID), passengers);
                        bookingController.;

                        command = scanner.nextLine();

                    } catch (Exception e) {
                        System.out.println("You entered invalid input. Please press enter to go back to main menu. \n");
                        command = scanner.nextLine();

                    }

                    break;

                case "4":
                    try {
                        System.out.println("Please enter ID of booking you want to cancel. \n");
                        int bookingId = Integer.parseInt(scanner.nextLine());
                        System.out.println(bookingController.cancelBooking(bookingId));

                        System.out.println("Your booking cancelled successfully. Please press enter to go back to main menu. \n");
                        command = scanner.nextLine();

                    } catch (Exception e) {
                        System.out.println("You entered invalid input. Please press enter to go back to main menu. \n");
                        command = scanner.nextLine();

                    }

                    break;

                case "5":
                    try{
                        System.out.println("Please enter passenger's name ");
                        String name = scanner.next();
                        System.out.println("Please enter passenger's surname ");
                        String surname = scanner.next();
                        System.out.println("Your flights:");
                        System.out.println(bookingController.getAllBooking());
                        System.out.println(bookingController.getMyFlights(name, surname));
                    }catch(Exception e) {
                        System.out.println("Data not found! Please press enter to go back to main menu. \n");
                        command = scanner.nextLine();
                    }
                    break;

                case "6":
                    bookingController.saveBooking();
                    flightController.saveFlight();

            }
        }

    }
}
