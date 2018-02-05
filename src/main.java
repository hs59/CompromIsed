// Comments
import java.util.Scanner;

public class main {
    public static void main(String [] args) {
        // Test SingleSite.java
        System.out.println("What's the name of the site you would like to check?\n" +
                "For example, Adobe.com's name would just be Adobe.\n\n" +
                "Enter it now: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        SingleSite test = new SingleSite();
        test.singleSite(name);

        // Test SystemBreaches.java
        //SystemBreaches test2 = new SystemBreaches();
        //test2.SysBreaches();

        // Test SystemDataClasses.java
        //SystemDataClasses test3 = new SystemDataClasses();
        //test3.DataClasses();


    }
}
