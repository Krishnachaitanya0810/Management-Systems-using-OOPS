import java.util.*;

class HistoryMS {
    static Scanner sc=new Scanner(System.in);
    static LinkedList<String> a = new LinkedList<>();
    static int pageno=0;
    public static void nextp(){
        if(pageno==(a.size()-1) || a.size()<=1){
            System.out.println("No next page");
        }
        else{
            pageno++;
            open();
        }
    }
    public static void backp(){
        if(a.size()<=1 || pageno==0){
            System.out.println("No previous page");
        }
        else{
            pageno--;
            open();
        }
    }
    public static void search(){
        String s=sc.nextLine();
        a.add(s);
        pageno=a.size()-1;
        open();
    }
    public static void open(){
        System.out.println("Opened: "+a.get(pageno));
    }
    public static void main(String args[]){
        select();
    }
    public static void select(){
        OUTER:
        while (true) {
            System.out.println(" 1.Back Page \n 2.Next Page \n 3.Search \n 4.Exit");
            String inp=sc.next();
            switch (inp) {
                case "BackPage" -> backp();
                case "NextPage" -> nextp();
                case "Search" -> {
                    System.out.println("Enter Search: ");
                    search();
                }
                case "Exit" -> {
                    System.out.println("You have EXITED the system");
                    break OUTER;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
