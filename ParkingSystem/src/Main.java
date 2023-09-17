import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

// ParkingSpot class start

class OnlineParkingSpot {

    private int spotNo;
    private boolean isAvailable;

    public OnlineParkingSpot(int spotNo) {
        this.spotNo = spotNo;
        this.isAvailable = true;
    }

    public int getSpotNumber() {
        return spotNo;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// ParkingSpot class ends
// ParkingBooking class start

class ParkingBooking {
    private static int nextBookingId = 1;
    private int bookingId;
    private User user;
    private OnlineParkingSpot parkingSpot;

    public ParkingBooking(User user, OnlineParkingSpot parkingSpot) {
        this.bookingId = nextBookingId++;
        this.user = user;
        this.parkingSpot = parkingSpot;
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OnlineParkingSpot getParkingSpot() {//
        return parkingSpot;
    }
}
// ParkingBooking class ends

// User class Start
class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
// User class ends

// ParkingSystem class start

class ParkingSystem {
    private List<OnlineParkingSpot> parkingSpots;
    private List<ParkingBooking> bookings;
    private List<User> users;
    private Map<Integer, ParkingBooking> bookingMap;

    public ParkingSystem() {
        this.parkingSpots = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookingMap = new HashMap<>();
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addParkingSpot(OnlineParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }

    public List<OnlineParkingSpot> searchAvailableSpots() {
        List<OnlineParkingSpot> availableSpots = new ArrayList<>();
        for (OnlineParkingSpot spot : parkingSpots) {
            if (spot.isAvailable()) {
                availableSpots.add(spot);
            }
        }
        return availableSpots;
    }

    public ParkingBooking bookParkingSpot(User user, OnlineParkingSpot parkingSpot, boolean available) {
        if (!parkingSpot.isAvailable()) {
            return null;
        }

        ParkingBooking booking = new ParkingBooking(user, parkingSpot);
        bookings.add(booking);
        parkingSpot.setAvailable(available = false);
        bookingMap.put(booking.getBookingId(), booking);
        return booking;
    }

    public List<ParkingBooking> getUserBookings(User user) {
        List<ParkingBooking> userBookings = new ArrayList<>();
        for (ParkingBooking booking : bookings) {
            if (booking.getUser().equals(user)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public boolean isUserRegistered(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public ParkingBooking getBookingById(int bookingId) {
        return bookingMap.get(bookingId);
    }
}

// ParkingSystem class ends
// ParkingApplication class start

class OnlineParkingApplication {
    public static void main(String[] args) {
         String ANSI_RESET = "\u001B[0m";
         String ANSI_BOLD = "\u001B[1m";
         String ANSI_UNDERLINE = "\u001B[4m";
        String ANSI_YELLOW = "\u001B[33m";
         String ANSI_RED = "\u001B[31m";
         String ANSI_PURPLE = "\u001B[35m";
        String ANSI_BLUE = "\u001B[34m"; // Blue text color
        ParkingSystem parkingSystem = new ParkingSystem();

        //
        parkingSystem.addParkingSpot(new OnlineParkingSpot(1));
        parkingSystem.addParkingSpot(new OnlineParkingSpot(2));
        parkingSystem.addParkingSpot(new OnlineParkingSpot(3));
        parkingSystem.addParkingSpot(new OnlineParkingSpot(4));

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String username, password;
        boolean loggedIn = false;
        User user = null;

        do {
            if (!loggedIn) {
                System.out.println(ANSI_BLUE + ANSI_BOLD + "!! Welcome to SecurePark !!" + ANSI_RESET);
                System.out.println("2. Login:");
                System.out.println("3. Exit.....");
                System.out.println("**************************************");
                System.out.println("Enter Your Choice : ");
                choice = scanner.nextInt();
                scanner.nextLine();// consume the new line character after reading the choice

                switch (choice) {
                    case 1:
                        // Register purpose
                        System.out.println("Enter Your Full Name: ");
                        username = scanner.nextLine();
                        if (parkingSystem.isUserRegistered(username)) {
                            System.out.println("Username is already in use. Please select a different username.");

                        } else {
                            System.out.println("Please Input Your Password: ");
                            password = scanner.nextLine();
                            user = new User(username, password);
                            parkingSystem.registerUser(user);
                            System.out.println("User Registration Successful. Welcome!");
                            loggedIn = true;
                        }
                        break;
                    case 2:
                        // For Login
                        System.out.println("Enter Your Full Name: ");
                        username = scanner.nextLine();
                        System.out.println("Please Input Your Password: ");
                        password = scanner.nextLine();
                        user = parkingSystem.getUser(username);
                        if (user != null && user.getPassword().equals(password)) {
                            System.out.println("Login Successful. Welcome!");
                        } else {
                            System.out.println("Invalid login credentials. Please check your username and password and try again.");

                        }
                        break;
                    case 3:
                        System.out.println("Thank you for using our parking service. Have a great day!");
                        break;
                    default:
                        System.out.println("Invalid input. Please make a valid selection and try again.");

                }
            } else {
                System.out.println("1. Check for available parking spaces");
                System.out.println("2. Reserve a parking spot");
                System.out.println("3. View your parking reservations");
                System.out.println("4. Log Out");
                System.out.println("Please Select an Option : ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Search the parking spot
                        List<OnlineParkingSpot> availableSpots = parkingSystem.searchAvailableSpots();
                        if (!availableSpots.isEmpty()) {
                            System.out.println(ANSI_RED + "Available Parking Spaces Found" + ANSI_RESET);
                        } else {
                            System.out.println("No available parking spaces at the moment");
                        }
                        break;
                    case 2:
                        // Booking spot
                        System.out.println("Please Enter the Spot Number You Wish to Reserve: ");
                        int spotNumber = scanner.nextInt();
                        scanner.nextLine();
                        OnlineParkingSpot spotToBook = null;
                        for (OnlineParkingSpot spot : parkingSystem.searchAvailableSpots()) {
                            if (spot.getSpotNumber() == spotNumber) {
                                spotToBook = spot;
                                break;
                            }
                        }
                        if (spotToBook != null) {
                            ParkingBooking booking = parkingSystem.bookParkingSpot(user, spotToBook, loggedIn);
                            if (booking != null) {
                                System.out.println(ANSI_YELLOW + "Reservation confirmed. Your Booking ID is: " + booking.getBookingId() + ANSI_RESET);
                            } else {
                                System.out.println("Sorry, this spot is already reserved. Please select a different spot.");
                            }
                        } else {
                            System.out.println("Invalid spot number. Please select a valid and available spot.");
                        }
                        break;
                    case 3:
                        // --> User view booking
                        List<ParkingBooking> userBookings = parkingSystem.getUserBookings(user);
                        if (!userBookings.isEmpty()) {
                            System.out.println(ANSI_PURPLE + "Your Parking Reservations" + ANSI_RESET);
                            for (ParkingBooking booking : userBookings) {
                                System.out.println("Booking ID : " + booking.getBookingId() + "   ,  Spot : "
                                        + booking.getParkingSpot());
                            }
                        } else {
                            System.out.println("You have no parking reservations for " + user.getUserName() + ".");
                        }
                        break;
                    case 4:
                        // Logout
                        System.out.println("You have been successfully logged out.");
                        loggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please choose a valid option and try again.");
                }
            }
            System.out.println();

        } while (choice != 3);
        scanner.close();
    }
}

