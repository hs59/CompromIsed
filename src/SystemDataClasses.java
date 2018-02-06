import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**  From API Docs:
        "A 'data class' is an attribute of a record compromised in a breach.
        For example, many breaches expose data classes such as "Email addresses"
        and "Passwords". The values returned by this service are ordered alphabetically
        in a string array and will expand over time as new breaches expose previously
        unseen classes of data."
 */

public class SystemDataClasses {
    public static void DataClasses() {
        try {
            // New URL object with the URL set
            URL url = new URL("https://haveibeenpwned.com/api/v2/dataclasses");
            // New HttpURLConnection object, opening the url
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Setting the User Agent - otherwise, Error 403 (See docs)
            conn.setRequestProperty("User-Agent","Pwnage-Checker");
            // Set as a GET method
            conn.setRequestMethod("GET");
            // Variable to hold the reponse code
            int responseCode = conn.getResponseCode();

            // Test reponseCode - continue if good
            if(responseCode != 200) {
                throw new RuntimeException("Failed: HTTP error code : "
                        + conn.getResponseCode());
            }
            // BufferedReader object w/ new InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            // Variable to hold the output from server
            String output;
            // Output..
            System.out.println("Output from Server.. \n");
            // Print..
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            // Disconect
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
