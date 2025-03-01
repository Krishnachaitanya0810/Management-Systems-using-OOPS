
import java.util.*;

public class FileMS {

    private Map<String, List<Object>> directory;

    public FileMS() {
        directory = new HashMap<>();
        directory.put("C", new ArrayList<>());
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new file name: ");
        String f = scanner.nextLine();
        choose(f);
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name to delete: ");
        String f = scanner.nextLine();
        if (!dfu(directory, f)) {
            System.out.println("No file found");
        }
    }

    private boolean dfu(Map<String, List<Object>> d, String f) {
        for (String key : d.keySet()) {
            List<Object> files = d.get(key);
            Iterator<Object> iterator = files.iterator();
            while (iterator.hasNext()) {
                Object item = iterator.next();
                if (item instanceof String && item.equals(f)) {
                    iterator.remove();
                    return true;
                }
                if (item instanceof Map) {
                    if (dfu((Map<String, List<Object>>) item, f)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void edit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name to edit: ");
        String f = scanner.nextLine();
        System.out.print("Rename to: ");
        String r = scanner.nextLine();
        if (!efu(directory, f, r)) {
            System.out.println("File not found");
        }
    }

    private boolean efu(Map<String, List<Object>> d, String f, String r) {
        for (String key : d.keySet()) {
            List<Object> files = d.get(key);
            for (int i = 0; i < files.size(); i++) {
                if (files.get(i) instanceof String && files.get(i).equals(f)) {
                    files.set(i, r);
                    return true;
                }
                if (files.get(i) instanceof Map) {
                    if (efu((Map<String, List<Object>>) files.get(i), f, r)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void display(Map<String, List<Object>> d, String indent) {
        for (String key : d.keySet()) {
            System.out.println(indent + "<dir> " + key);
            for (Object item : d.get(key)) {
                if (item instanceof Map) {
                    display((Map<String, List<Object>>) item, indent + "  ");
                } else {
                    System.out.println(indent + "<file> " + item);
                }
            }
        }
    }

    private void cfu(Map<String, List<Object>> d, String di, String f) {
        for (String key : d.keySet()) {
            if (key.equals(di)) {
                d.get(di).add(f);
                return;
            }
            for (Object item : d.get(key)) {
                if (item instanceof Map) {
                    cfu((Map<String, List<Object>>) item, di, f);
                }
            }
        }
    }

    public void choose(String f) {
        display(directory, "");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n1. New directory\n2. Root directory\n3. Choose directory\nEnter choice: ");
        String o = scanner.nextLine();

        if (o.equals("1")) {
            System.out.print("Enter name of new directory: ");
            String x = scanner.nextLine();
            Map<String, List<Object>> newDir = new HashMap<>();
            newDir.put(x, new ArrayList<>(Collections.singletonList(f)));
            directory.get("C").add(newDir);
        } else if (o.equals("2")) {
            directory.get("C").add(f);
        } else if (o.equals("3")) {
            System.out.print("Enter name of the directory: ");
            String x = scanner.nextLine();
            cfu(directory, x, f);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n1. Create file\n2. Edit file\n3. Delete file\n4. Display\n5. Exit\nEnter choice: ");
            String c = scanner.nextLine();
            switch (c) {
                case "1":
                    create();
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    display(directory, "");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        FileMS fileManager = new FileMS();
        fileManager.menu();
    }
}
