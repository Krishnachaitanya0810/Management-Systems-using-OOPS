
import java.util.*;

class details {

    String name;
    String crop;
    int yield;
    int price;

    details(String name, String crop, int yield, int price) {
        this.name = name;
        this.crop = crop;
        this.yield = yield;
        this.price = price;
    }
}

public class FarmerMS {

    public static LinkedList<details> l = new LinkedList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void addFarmer() {
        System.out.println("Enter name:");
        String name = sc.next();
        System.out.println("Enter crop:");
        String crop = sc.next();
        System.out.println("Enter yield:");
        int yield = sc.nextInt();
        System.out.println("Enter price:");
        int price = sc.nextInt();
        l.add(new details(name, crop, yield, price));
    }

    public static void display() {
        for (details i : l) {
            System.out.println("Name: " + i.name);
            System.out.println("Crop: " + i.crop);
            System.out.println("Yield: " + i.yield);
            System.out.println("Price: " + i.price);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Farmer\n2. Display\n3. Exit");
            String c = sc.next();
            if (c.equals("1")) {
                addFarmer();
            } else if (c.equals("2")) {
                display();
            } else if (c.equals("3")) {
                System.out.println("Thank You");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
