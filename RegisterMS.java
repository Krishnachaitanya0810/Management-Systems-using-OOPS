import java.util.*;

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}

class UserManager {
    LinkedList<User> users;

    public UserManager() {
        users = new LinkedList<>();
    }

    public boolean registerUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password));
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.validatePassword(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

public class RegisterMS {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            UserManager userManager = new UserManager();
            boolean running = true;
            
            System.out.println("Welcome to the Login and Register System\n");
            
            while (running) {
                System.out.println("Select an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter a username: ");
                        String regUsername = scanner.nextLine();
                        
                        if (userManager.userExists(regUsername)) {
                            System.out.println("Username already exists. Please try a different one.\n");
                            break;
                        }
                        
                        System.out.print("Enter a password: ");
                        String regPassword = scanner.nextLine();
                        
                        if (userManager.registerUser(regUsername, regPassword)) {
                            System.out.println("Registration successful! You can now log in.\n");
                        } else {
                            System.out.println("Registration failed. Please try again.\n");
                        }
                    }
                        
                    case 2 -> {
                        System.out.print("Enter your username: ");
                        String loginUsername = scanner.nextLine();
                        
                        System.out.print("Enter your password: ");
                        String loginPassword = scanner.nextLine();
                        
                        if (userManager.loginUser(loginUsername, loginPassword)) {
                            System.out.println("Login successful! Welcome, " + loginUsername + "!\n");
                        } else {
                            System.out.println("Invalid username or password. Please try again.\n");
                        }
                    }
                        
                    case 3 -> {
                        System.out.println("Exiting the system. Goodbye!");
                        running = false;
                    }
                        
                    default -> System.out.println("Invalid choice. Please try again.\n");
                }
            }
        }
    }
}
