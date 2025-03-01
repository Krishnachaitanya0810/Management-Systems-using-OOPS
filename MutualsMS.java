
import java.util.*;

class Account {

    String username;
    String pswd;
    LinkedList<String> followers = new LinkedList<>();
    LinkedList<String> following = new LinkedList<>();
    LinkedList<String> suggestion = new LinkedList<>();

    Account(String username) {
        this.username = username;
    }
}

public class MutualsMS {

    public static LinkedList<Account> l = new LinkedList<>();
    public static Scanner sc = new Scanner(System.in);
    static String user = "";

    public static void menu() {
        while (true) {
            System.out.println("1. Login\n2. Register\n3. Exit");
            String c = sc.next();
            if (c.equals("1")) {
                boolean b = login();
                if (b) {
                    break;
                }
            } else if (c.equals("2")) {
                boolean b = register();
                if (b) {
                    break;
                }
            } else if (c.equals("3")) {
                System.out.println("Thank You");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static boolean login() {
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String pswd = sc.next();
        for (Account account : l) {
            if (account.username.equals(username) && (account.pswd != null ? account.pswd.equals(pswd) : false)) {
                System.out.println("Login successful");
                user = username;
                return activity();
            }
        }
        System.out.println("Invalid credentials");
        return false;
    }

    public static void logout() {
        user = "";
        System.out.println("You have logged out");
    }

    public static boolean register() {
        System.out.print("Create username: ");
        String username = sc.next();
        Account s = new Account(username);
        System.out.print("Create Password: ");
        String pswd = sc.next();
        for (Account account : l) {
            if (account.username.equals(username)) {
                System.out.println("Already registered");
                return false;
            }
        }
        l.add(s);
        s.pswd = pswd;
        System.out.println("Registered successfully");
        user = username;
        return activity();
    }

    public static void followersm() {
        System.out.println("Followers");
        for (Account account : l) {
            if (account.username.equals(user)) {
                for (String i : account.followers) {
                    System.out.println(i);
                }
            }
        }
    }

    public static void followingm() {
        System.out.println("Following");
        for (Account account : l) {
            if (account.username.equals(user)) {
                for (String i : account.following) {
                    System.out.println(i);
                }
            }
        }
    }

    public static void suggestions() {
        for (Account account : l) {
            if (account.username.equals(user)) {
                for (Account account2 : l) {
                    for (String i : account.following) {
                        if (account2.followers.contains(i) && !account.suggestion.contains(account2) && !account2.following.contains(i) && !account2.followers.contains(user)) {
                            account.suggestion.add(account2.username);
                        } else if (account2.followers.contains(i) && !account.suggestion.contains(account2) && account2.username != account.username && account2.following.contains(i) && !account2.followers.contains(user)) {
                            account.suggestion.add(account2.username);
                        }
                    }
                }
                for (String j : account.suggestion) {
                    System.out.println(j);
                }
                return;
            }
        }
    }

    public static void tofollow() {
        System.out.println("Enter username to follow: ");
        String username = sc.next();
        for (Account account : l) {
            if (account.username.equals(user)) {
                for (Account account2 : l) {
                    if (account2.username.equals(username)) {
                        account.following.add(username);
                        account2.followers.add(user);
                        return;
                    }
                }
                System.out.println("No user found");
                return;
            }
        }
    }

    public static boolean activity() {
        while (true) {
            System.out.println("1. Followers\n2. Following\n3. Suggestions\n4. To follow\n5. logout\n6. exit");
            System.out.print("Enter choice: ");
            String c = sc.next();
            if (c.equals("1")) {
                followersm();
            } else if (c.equals("2")) {
                followingm();
            } else if (c.equals("3")) {
                suggestions();
            } else if (c.equals("4")) {
                tofollow();
            } else if (c.equals("5")) {
                logout();
                return false;
            } else if (c.equals("6")) {
                System.out.println("Thank you");
                return true;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
