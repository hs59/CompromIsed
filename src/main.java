// Comments
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import com.google.gson.*;
import org.json.simple.JSONObject;
import java.util.Scanner;

public class main {
    public static void main(String [] args) throws IOException {
        // Test SingleSite.java
        System.out.println("What's the name of the site you would like to check?\n" +
                "For example, Adobe.com's name would just be Adobe.\n\n" +
                "Enter it now: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        SingleSite test = new SingleSite();
        test.Connection(name);

        // Test SystemBreaches.java
        //SystemBreaches test2 = new SystemBreaches();
        //test2.SysBreaches();

        // Test SystemDataClasses.java
        //SystemDataClasses test3 = new SystemDataClasses();
        //test3.DataClasses();

        //String title = HELLO.getObj().get("Domain").getAsString();
        //System.out.println(title);




    }
}
